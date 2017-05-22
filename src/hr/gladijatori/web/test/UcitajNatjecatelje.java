package hr.gladijatori.web.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.dao.JPAEMFProvider;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Pozicija;
import hr.gladijatori.modeli.virtliga.VirtEkipa;
import hr.gladijatori.modeli.virtliga.VirtLiga;

public class UcitajNatjecatelje {

	public static void main(String[] args) throws IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("baza.podataka");
		JPAEMFProvider.setEmf(emf);
		XLSread reader = new XLSread();

		List<Natjecatelj> natjecatelji = reader.getNatjecatelji();
		Iterator<Natjecatelj> it = natjecatelji.iterator();
		while(it.hasNext()) {
			if (it.next().getIme().isEmpty()) it.remove();
		}	
		List<VirtEkipa> ve = reader.getVirtekipe();
		Iterator<VirtEkipa> itVe = ve.iterator();
		while(itVe.hasNext()) {
			if (itVe.next().getNaziv().isEmpty()) itVe.remove();
		}

		dodajLigu(emf, natjecatelji, ve);
		

		
	}
	
	@SuppressWarnings("unchecked")
	private static VirtLiga dodajLigu(EntityManagerFactory emf, List<Natjecatelj> natjecatelji, List<VirtEkipa> ekipe) throws UnsupportedEncodingException {
		Random rand = new Random();
		VirtLiga liga = new VirtLiga();
		liga.setEkipe(ekipe);
		liga.setNatjecatelji(natjecatelji);
		liga.setIme("Virtualna liga");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Igrac> igraci = (List<Igrac>)(Object)DAOProvider.getDAO().izvediUpit("SELECT ig FROM Igrac ig");
		int size = igraci.size();
		
		for (Natjecatelj n : natjecatelji) {
			if (n.getPodrzani() == null) continue;

			n.setLiga(liga);
			n.setPodrzani(DAOProvider.getDAO().dohvatiKlub(n.getPodrzani().getIme()));
			MessageDigest digest = null;
			String hashsifra = null;
			try {
				digest = MessageDigest.getInstance("SHA-1");
				digest.update(n.getPassword().getBytes("utf8"));
				byte[] digestBytes = digest.digest();
				hashsifra = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			n.setPassword(hashsifra);
		}
		for (VirtEkipa e : ekipe) {
			List<Igrac> ekipa = new ArrayList<>();
			
			for (Pozicija poz : Pozicija.values()) {
				while (true) {
					Igrac odabran = igraci.get(rand.nextInt(size));
					if (odabran.getPozicija().equals(poz)) {
						ekipa.add(odabran);
						break;
					}
				}
			}
			
			e.setIgraci(ekipa);
			e.setLiga(liga);
		}
		

		em.persist(liga);
		em.getTransaction().commit();
		em.close();
		return liga;
	}
	
}
