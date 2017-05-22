package hr.gladijatori.dao;

import java.util.List;

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

public interface DAO {

	public Kolo dohvatiKolo(long id);
	public Kolo dodajKolo(Kolo kolo);
	
	public Utakmica dohvatiUtakmicu(long id);
	public Utakmica dodajUtakmicu(Utakmica utakmica);
	public void dodajDogadjaj(Dogadjaj dogadjaj, Utakmica utakmica);
	public Dogadjaj dohvatiDogadjaj(long id);
	
	public Klub dohvatiKlub(long id);
	public Klub dohvatiKlub(String ime);
	public void dodajKlub(Klub klub, Liga liga);
	
	public Igrac dohvatiIgraca(long id);
	public List<Igrac> dohvatiIgraca(String ime, String prezime);
	public List<Igrac> dohvatiIgraca(Pozicija pozicija);
	public void dodajIgracaUEkipu(Igrac igrac, VirtEkipa ekipa);
	public void dodajIgraca(Igrac igrac);
	
	public void dodajLigu(Liga liga);
	public Liga dohvatiLigu(long id);
	
	public void dodajVirtLigu(VirtLiga virtLiga);
	public VirtLiga dohvatiVirtLigu(long id);
	
	public VirtEkipa dohvatiEkipu(long id);
	public void dodajEkipu(VirtEkipa ekipa, VirtLiga virtLiga);
	
	public Korisnik dohvatiKorisnika(String username);
	public void dodajKorisnika(Korisnik korisnik);
	
	public Natjecatelj dohvatiNatjecatelja(String korime);
	public VirtLiga dohvatiLigu(String ime);
	
	/**
	 * Ova metoda izvodi neki SQL upit i dohvaća rezultate kao listu objekata.
	 * <p>Notacija SQL naredbe izgleda ovako:
	 * <br><i>"select b from Igrac where b.klub = ? and b.godine > ?"</i><br>
	 * Drugi argument u metodi bi bili objekti koji sadržavaju argumente koje će zamijeniti upitnike u sql naredbi.
	 * <p>Dakle ovako bi se pozvala metoda:<br>
	 * <code>List&ltIgrac&gt igraci = (List&ltIgrac&gt) izvediUpit(<br>"select b from Igrac where b.klub = ? and b.godine > ?",<br> klub,<br> 43);</code>
	 * @param upit string reprezentacija sql naredbe
	 * @param parametri parametri sql naredbe pravilnim redoslijedom
	 * @return listu rezultata
	 */
	public List<Object> izvediUpit(String upit, Object... parametri);
	public void izvediUpdateUpit(String upit, Object... parametri);

	public List<Klub> dohvatiKlubove();
	
	public double dohvatiVrijednostDogadjaja(TipDogadjaja tip);
	public DogadjajVrijednost postaviVrijednostDogadjaja(TipDogadjaja tip, double vrijednost);
	public void izvediNativeUpdateUpit(String upit, Object... parametri);
	

}
