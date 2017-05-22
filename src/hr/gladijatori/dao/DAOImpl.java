package hr.gladijatori.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import hr.gladijatori.modeli.korisnik.Korisnik;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.liga.Dogadjaj;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.liga.Kolo;
import hr.gladijatori.modeli.liga.Liga;
import hr.gladijatori.modeli.liga.Pozicija;
import hr.gladijatori.modeli.liga.TipDogadjaja;
import hr.gladijatori.modeli.liga.Utakmica;
import hr.gladijatori.modeli.virtliga.DogadjajVrijednost;
import hr.gladijatori.modeli.virtliga.VirtEkipa;
import hr.gladijatori.modeli.virtliga.VirtLiga;

public class DAOImpl implements DAO {

	@Override
	public Kolo dohvatiKolo(long id) {
		Kolo kolo = JPAEMProvider.getEntityManager().find(Kolo.class, id);
		return kolo;
	}

	@Override
	public Kolo dodajKolo(Kolo kolo) {
		JPAEMProvider.getEntityManager().persist(kolo);
		return kolo;
	}

	@Override
	public Utakmica dohvatiUtakmicu(long id) {
		Utakmica utakmica = JPAEMProvider.getEntityManager().find(Utakmica.class, id);
		return utakmica;
	}

	@Override
	public Utakmica dodajUtakmicu(Utakmica utakmica) {
		JPAEMProvider.getEntityManager().persist(utakmica);
		return utakmica;
	}

	@Override
	public void dodajDogadjaj(Dogadjaj dogadjaj, Utakmica utakmica) {
		dogadjaj.setUtakmica(utakmica);
		JPAEMProvider.getEntityManager().persist(dogadjaj);
	}

	@Override
	public Dogadjaj dohvatiDogadjaj(long id) {
		Dogadjaj dog = JPAEMProvider.getEntityManager().find(Dogadjaj.class, id);
		return dog;
	}

	@Override
	public Klub dohvatiKlub(long id) {
		Klub klub = JPAEMProvider.getEntityManager().find(Klub.class, id);
		return klub;
	}
	
	@Override
	public Klub dohvatiKlub(String ime) {
		try {
			Klub klub = (Klub) JPAEMProvider
					.getEntityManager()
					.createQuery("select b from Klub as b where b.ime=:n")
					.setParameter("n", ime).getSingleResult();
			return klub;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void dodajKlub(Klub klub, Liga liga) {
		klub.setLiga(liga);
		JPAEMProvider.getEntityManager().persist(klub);
	}

	@Override
	public Igrac dohvatiIgraca(long id) {
		Igrac igrac = JPAEMProvider.getEntityManager().find(Igrac.class, id);
		return igrac;
	}
	
	@Override
	public List<Igrac> dohvatiIgraca(String ime, String prezime) {
		try {
			@SuppressWarnings("unchecked")
			List<Igrac> igraci = (List<Igrac>) JPAEMProvider
					.getEntityManager()
					.createQuery("select b from Igrac as b where b.ime=:i and b.prezime=:p")
					.setParameter("i", ime).setParameter("p", prezime).getResultList();
			return igraci;
		} catch (NoResultException e) {
			return null;
		}
	}

	
	@Override
	public List<Klub> dohvatiKlubove() {
		try {
			@SuppressWarnings("unchecked")
			List<Klub> igraci = (List<Klub>) JPAEMProvider
					.getEntityManager()
					.createQuery("select b from Klub as b")
					.getResultList();
			return igraci;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@Override
	public List<Igrac> dohvatiIgraca(Pozicija pozicija) {
		try {
			@SuppressWarnings("unchecked")
			List<Igrac> igraci = (List<Igrac>) JPAEMProvider
					.getEntityManager()
					.createQuery("select b from Igrac as b where b.pozicija=:p")
					.setParameter("p", pozicija).getResultList();
			return igraci;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void dodajIgracaUEkipu(Igrac igrac, VirtEkipa ekipa) {
		ekipa.getIgraci().add(igrac);
		JPAEMProvider.getEntityManager().merge(ekipa);
	}

	@Override
	public void dodajIgraca(Igrac igrac) {
		JPAEMProvider.getEntityManager().persist(igrac);
	}

	@Override
	public void dodajLigu(Liga liga) {
		JPAEMProvider.getEntityManager().persist(liga);
	}

	@Override
	public Liga dohvatiLigu(long id) {
		Liga liga = JPAEMProvider.getEntityManager().find(Liga.class, id);
		return liga;
	}

	@Override
	public void dodajVirtLigu(VirtLiga virtLiga) {
		JPAEMProvider.getEntityManager().persist(virtLiga);
	}

	@Override
	public VirtLiga dohvatiVirtLigu(long id) {
		VirtLiga liga = JPAEMProvider.getEntityManager().find(VirtLiga.class, id);
		return liga;
	}
	
	@Override
	public VirtEkipa dohvatiEkipu(long id) {
		VirtEkipa ekipa = JPAEMProvider.getEntityManager().find(VirtEkipa.class, id);
		return ekipa;
	}

	@Override
	public void dodajEkipu(VirtEkipa ekipa, VirtLiga virtLiga) {
		ekipa.setLiga(virtLiga);
		JPAEMProvider.getEntityManager().persist(ekipa);
	}

	@Override
	public Korisnik dohvatiKorisnika(String username) {
		try {
			Korisnik korisnik = (Korisnik) JPAEMProvider
					.getEntityManager()
					.createQuery("select b from Korisnik as b where b.username=:n")
					.setParameter("n", username).getSingleResult();
			return korisnik;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void dodajKorisnika(Korisnik korisnik) {
		JPAEMProvider.getEntityManager().persist(korisnik);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> izvediUpit(String upit, Object... parametri) {
		Query query = JPAEMProvider.getEntityManager().createQuery(upit);
		for (int i=0; i<parametri.length; i++) {
			query = query.setParameter(i+1, parametri[i]);
		}
		return query.getResultList();
	}
	
	public void izvediUpdateUpit(String upit, Object... parametri) {
		Query query = JPAEMProvider.getEntityManager().createQuery(upit);
		for (int i=0; i<parametri.length; i++) {
			query = query.setParameter(i+1, parametri[i]);
		}
		query.executeUpdate();
	}
	
	public void izvediNativeUpdateUpit(String upit, Object... parametri) {
		Query query = JPAEMProvider.getEntityManager().createNativeQuery(upit);
		for (int i=0; i<parametri.length; i++) {
			query = query.setParameter(i+1, parametri[i]);
		}
		query.executeUpdate();
	}


	@Override
	public Natjecatelj dohvatiNatjecatelja(String korime) {
		try {
			Natjecatelj nat = (Natjecatelj) JPAEMProvider
					.getEntityManager()
					.createQuery("select b from Natjecatelj as b where b.username=:u")
					.setParameter("u", korime).getSingleResult();
			return nat;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public VirtLiga dohvatiLigu(String ime) {
		try {
			VirtLiga liga = (VirtLiga) JPAEMProvider
					.getEntityManager()
					.createQuery("select b from VirtLiga as b where b.ime=:n")
					.setParameter("n", ime).getSingleResult();
			return liga;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public double dohvatiVrijednostDogadjaja(TipDogadjaja tip) {
		DogadjajVrijednost dv = JPAEMProvider.getEntityManager().find(DogadjajVrijednost.class, tip);
		return dv == null ? 0 : dv.getVrijednost();
	}

	@Override
	public DogadjajVrijednost postaviVrijednostDogadjaja(TipDogadjaja tip, double vrijednost) {
		DogadjajVrijednost vr = new DogadjajVrijednost();
		vr.setTip(tip);
		vr.setVrijednost(vrijednost);
		JPAEMProvider.getEntityManager().persist(vr);
		return vr;
	}





}
