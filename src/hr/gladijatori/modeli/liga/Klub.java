package hr.gladijatori.modeli.liga;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Klub")
public class Klub {
	private long id;
	private String ime;
	private List<Igrac> igraci;
	private String ogranak;
	private double vrijednostKluba;
	private int daliGolova;
	private int primiliGolova;
	private int ostvareniBodovi;
	private Liga liga;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(length = 100, nullable = false)
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	@OneToMany(mappedBy = "klub", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
	}

	@Column(length = 100)
	public String getOgranak() {
		return ogranak;
	}

	public void setOgranak(String ogranak) {
		this.ogranak = ogranak;
	}

	@Transient
	public double getVrijednostKluba() {
		return vrijednostKluba;
	}

	@PostLoad
	public void setVrijednostKluba() {
		for (Igrac ig : igraci) {
			vrijednostKluba += ig.getVrijednost();
		}
	}

	@Column
	public int getOstvareniBodovi() {
		return ostvareniBodovi;
	}

	public void setOstvareniBodovi(int ostvareniBodovi) {
		this.ostvareniBodovi = ostvareniBodovi;
	}

	@ManyToOne
	public Liga getLiga() {
		return liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}
	
	@Column
	public int getDaliGolova() {
		return daliGolova;
	}
	
	@Column
	public int getPrimiliGolova() {
		return primiliGolova;
	}
	
	public void setDaliGolova(int daliGolova) {
		this.daliGolova = daliGolova;
	}
	
	public void setPrimiliGolova(int primiliGolova) {
		this.primiliGolova = primiliGolova;
	}

}
