package hr.gladijatori.modeli.liga;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Utakmica")
public class Utakmica {
	
	private long id;
	private Klub domacin;
	private Klub gost;
	private Date datumOdigravanja;
	private List<Dogadjaj> dogadjaji;
	private Igrac mvp;
	private Kolo kolo;
	private String rezultat;
	private boolean zavrsena;

	@OneToOne
	public Klub getDomacin() {
		return domacin;
	}

	public void setDomacin(Klub domacin) {
		this.domacin = domacin;
	}
	
	@OneToOne
	public Klub getGost() {
		return gost;
	}

	public void setGost(Klub gost) {
		this.gost = gost;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getDatumOdigravanja() {
		return datumOdigravanja;
	}

	public void setDatumOdigravanja(Date datumOdigravanja) {
		this.datumOdigravanja = datumOdigravanja;
	}

	@OneToMany(mappedBy = "utakmica", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Dogadjaj> getDogadjaji() {
		return dogadjaji;
	}
	
	public void setDogadjaji(List<Dogadjaj> dogadjaji) {
		this.dogadjaji = dogadjaji;
	}
	
	@OneToOne
	public Igrac getMvp() {
		return mvp;
	}

	public void setMvp(Igrac mvp) {
		this.mvp = mvp;
	}
	
	@ManyToOne
	@JoinColumn(nullable = false)
	public Kolo getKolo() {
		return kolo;
	}

	public void setKolo(Kolo kolo) {
		this.kolo = kolo;
	}

	@Column
	public boolean isZavrsena() {
		return zavrsena;
	}
	
	public void setZavrsena(boolean zavrsena) {
		this.zavrsena = zavrsena;
	}
	
	@Column
	public String getRezultat() {
		return rezultat;
	}
	
	public void setRezultat(String rezultat) {
		this.rezultat = rezultat;
	}
	
}
