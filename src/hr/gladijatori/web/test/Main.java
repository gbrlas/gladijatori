package hr.gladijatori.web.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.logging.Logger;

import hr.gladijatori.dao.DAOException;
import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.dao.JPAEMFProvider;
import hr.gladijatori.dao.JPAEMProvider;
import hr.gladijatori.modeli.korisnik.Administrator;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.korisnik.TipKorisnika;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.liga.Kolo;
import hr.gladijatori.modeli.liga.Liga;
import hr.gladijatori.modeli.liga.Pozicija;
import hr.gladijatori.modeli.liga.Utakmica;
import hr.gladijatori.modeli.virtliga.VirtEkipa;
import hr.gladijatori.modeli.virtliga.VirtLiga;

/**
 * Za pokretanje potrebna dva argumenta:
 * <ul>
 * <li>Odluka o tome što se radi s bazom (ucitaj, brisi)</li>
 * <li>Putanja do datoteke s ekipama</li>
 * </ul>
 * @author Vinko
 *
 */

public class Main {
	
	
	public static void main(String[] args) throws IOException {


		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("baza.podataka");
			JPAEMFProvider.setEmf(emf);
		} catch (Exception e) {
			System.err.println("\nNije pokrenuta baza!");
			System.exit(1);
		}
	

		if (args[0].equals("brisi")) {
			String[] tables = new String[] {
					"VIRTEKIPA_POCETNIBODOVI",
					"VIRTEKIPA_IMA_IGRACE",
					"DOGADJAJVRIJEDNOST",
					"DOGADJAJ",
					"IGRAC",
					"UTAKMICA",
					"KOLO",
					"KORISNIK",
					"VIRTEKIPA",
					"VIRTLIGA",	
					"KLUB",
					"LIGA"

			};
			EntityManager em = JPAEMProvider.getEntityManager();
			String sql = "DELETE FROM ";
			
			for (String table : tables) {
				DAOProvider.getDAO().izvediNativeUpdateUpit(sql + table);

			}
			
			
			em.getTransaction().commit();
			em.close();
			System.out.println("\nBaza uspješno očišćena!");
		} else if (args[0].equals("ucitaj")) {

			if (args.length != 2) {
				System.err.println("Potrebna 2 argumenta: ucitaj ; Putanja do datoteke s ekipama.");
				System.exit(1);
			}
			
			try {

				XLSread reader = new XLSread(args[1]);
				List<Klub> klubovi = new ArrayList<>(reader.getKluboviMapa().values());
				Liga liga = new Liga();
				liga.setId(1);

				EntityManager em = JPAEMProvider.getEntityManager();
				// em.getTransaction().begin();
				DAOProvider.getDAO().dodajLigu(liga);
				em.getTransaction().commit();

				int j = 1;
				for (Klub klub : klubovi) {
					klub.setLiga(liga);
					dodajKlub(emf, klub, j++);
				}

				dodajLigu(emf, new ArrayList<>(), new ArrayList<>());

				String koloUtakmica = "1	1-12	2-11	3-10	4-9		5-8		6-7\n"
						+ "2	12-7	8-6		9-5		10-4	11-3	1-2\n"
						+ "3	2-12	3-1		4-11	5-10	6-9		7-8\n"
						+ "4	12-8	9-7		10-6	11-5	1-4		2-3\n"
						+ "5	3-12	4-2		5-1		6-11	7-10	8-9\n"
						+ "6	12-9	10-8	11-7	1-6		2-5		3-4\n"
						+ "7	4-12	5-3		6-2		7-1		8-11	9-10\n"
						+ "8	12-10	11-9	1-8		2-7		3-6		4-5\n"
						+ "9	5-12	6-4		7-3		8-2		9-1		10-11\n"
						+ "10	12-11	1-10	2-9		3-8		4-7		5-6\n"
						+ "11	6-12	7-5		8-4		9-3		10-2	11-1";

				Calendar datum = Calendar.getInstance();
				datum.set(2016, 2, 1);

				em.getTransaction().begin();
				for (String koloRed : koloUtakmica.split("\n")) {
					datum.add(Calendar.DAY_OF_MONTH, 1);
					String raspored[] = koloRed.split("\\s+");

					Kolo kolo = new Kolo();
					kolo.setLiga(liga);
					kolo.setBrojKola(Integer.parseInt(raspored[0]));

					DAOProvider.getDAO().dodajKolo(kolo);

					for (int i = 1; i < raspored.length; i++) {
						Utakmica ut = new Utakmica();
						String[] sud = raspored[i].split("-");
						ut.setDatumOdigravanja(datum.getTime());
						ut.setKolo(kolo);
						ut.setDomacin(klubovi.get(Integer.parseInt(sud[0]) - 1));
						ut.setGost(klubovi.get(Integer.parseInt(sud[1]) - 1));
						DAOProvider.getDAO().dodajUtakmicu(ut);
					}

				}
				em.getTransaction().commit();

				String ime = "Vinko";
				String prezime = "Kolobara";
				String email = "stavasbriga@hihi.bles";
				String korime = "admin";
				String sifra = "admin";
				MessageDigest digest = null;
				String hashsifra = null;
				try {
					digest = MessageDigest.getInstance("SHA-1");
					digest.update(sifra.getBytes("utf8"));
					byte[] digestBytes = digest.digest();
					hashsifra = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;

				Administrator admin = new Administrator();
				admin.setE_mail(email);
				admin.setPassword(hashsifra);
				admin.setPrezime(prezime);
				admin.setUsername(korime);
				admin.setIme(ime);
				admin.setTip(TipKorisnika.ADMIN);

				em.getTransaction().begin();
				DAOProvider.getDAO().dodajKorisnika(admin);
				em.getTransaction().commit();
				
				System.out.println("Podatci uspješno učitani u bazu!");
			} catch (IOException e) {
				System.err.println("\nNe postoji datoteka na putanji: " + args[1]);
				System.exit(1);
			} catch (DAOException ex) {
				System.err.println("\nPogreška pri zapisu u bazu!");
				System.exit(1);
			}
		} else {
			System.err.println("\nPogrešan argument!");
			System.exit(1);
		}

	}

	private static VirtLiga dodajLigu(EntityManagerFactory emf, List<Natjecatelj> natjecatelji, List<VirtEkipa> ekipe)
			throws UnsupportedEncodingException {
		Random rand = new Random();
		VirtLiga liga = new VirtLiga();
		liga.setEkipe(ekipe);
		liga.setNatjecatelji(natjecatelji);
		liga.setIme("Virtualna liga");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Igrac> igraci = (List<Igrac>) (Object) DAOProvider.getDAO().izvediUpit("SELECT ig FROM Igrac ig");
		int size = igraci.size();

		for (Natjecatelj n : natjecatelji) {
			if (n.getPodrzani() == null)
				continue;

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
			}
			;
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

	public static Object dodajKlub(EntityManagerFactory emf, Klub klub, int ogr) {
		klub.setOgranak("Ogranak" + ogr);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(klub);
		em.getTransaction().commit();
		em.close();
		return klub;

	}

}
