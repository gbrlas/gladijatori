package hr.gladijatori.modeli.korisnik;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.virtliga.VirtEkipa;
import hr.gladijatori.modeli.virtliga.VirtLiga;

@Entity
@DiscriminatorValue("N")
@Table(name="Korisnik")
public class Natjecatelj extends Korisnik {
	private VirtLiga liga;
	private VirtEkipa ekipa;
	private Klub podrzani;
	private String drzava;

	

	@OneToOne
	public VirtLiga getLiga() {
		return liga;
	}
	
	public void setLiga(VirtLiga liga) {
		this.liga = liga;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public VirtEkipa getEkipa() {
		return ekipa;
	}

	
	public void setEkipa(VirtEkipa ekipa) {
		this.ekipa = ekipa;
	}

	@OneToOne
	public Klub getPodrzani() {
		return podrzani;
	}

	public void setPodrzani(Klub podrzani) {
		this.podrzani = podrzani;
	}

	@Column(length = 100, nullable = true)
	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}


}
