package hr.gladijatori.modeli.liga;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.virtliga.VirtEkipa;

@Entity
@Table(name="Igrac")
public class Igrac {
	private int brojDresa;
	private long id;
	private String ime;
	private String prezime;
	private Pozicija pozicija;
	private Klub klub;
	private List<VirtEkipa> ekipe;
	private int godine;
	private double vrijednost;
	private double bodovi;
	private List<Dogadjaj> dogadjaji;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public int getBrojDresa() {
		return brojDresa;
	}
	
	public void setBrojDresa(int brojDresa) {
		this.brojDresa = brojDresa;
	}

	@Column(length = 100, nullable = false)
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Column(length = 100, nullable = false)
	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Enumerated(EnumType.STRING)
	public Pozicija getPozicija() {
		return pozicija;
	}

	public void setPozicija(Pozicija pozicija) {
		this.pozicija = pozicija;
	}

	@ManyToOne
	public Klub getKlub() {
		return klub;
	}

	public void setKlub(Klub klub) {
		this.klub = klub;
	}

	@Column
	public int getGodine() {
		return godine;
	}

	public void setGodine(int godine) {
		this.godine = godine;
	}

	@Column(nullable=false)
	public double getVrijednost() {
		return vrijednost;
	}

	public void setVrijednost(double vrijednost) {
		this.vrijednost = vrijednost;
	}

	@Transient
	public double getBodovi() {
		return bodovi;
	}
	
	@PostLoad
	private void setBodovi() {
		bodovi = 0;
		if (dogadjaji != null) 
		for (Dogadjaj dogadjaj : dogadjaji) {
			bodovi += DAOProvider.getDAO().dohvatiVrijednostDogadjaja(dogadjaj.getTip());
		}
	}
	
	@ManyToMany(mappedBy="igraci", cascade=CascadeType.PERSIST)
	public List<VirtEkipa> getVirtEkipe() {
		return ekipe;
	}

	public void setVirtEkipe(List<VirtEkipa> ekipa) {
		this.ekipe = ekipa;
	}
	
	@OneToMany(mappedBy = "igrac", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Dogadjaj> getDogadjaji() {
		return dogadjaji;
	}
	
	public void setDogadjaji(List<Dogadjaj> dogadjaji) {
		this.dogadjaji = dogadjaji;
	}

}
