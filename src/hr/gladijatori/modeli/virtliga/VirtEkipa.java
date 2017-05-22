package hr.gladijatori.modeli.virtliga;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.liga.Igrac;

@Entity
@Table(name="VirtEkipa")
public class VirtEkipa {
	private double proracun;
	private String naziv;
	private long id;
	private Natjecatelj natjecatelj;
	private List<Igrac> igraci;
	private VirtLiga liga;
	private double ostvareniBodovi;
	private ArrayList<Double> pocetniBodovi = new ArrayList<>();


	@Column
	public double getProracun() {
		return proracun;
	}

	public void setProracun(double proracun) {
		this.proracun = proracun;
	}

	@Column(length=100, nullable = false)
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@OneToOne(mappedBy="ekipa", cascade = CascadeType.ALL)
	public Natjecatelj getNatjecatelj() {
		return natjecatelj;
	}

	public void setNatjecatelj(Natjecatelj natjecatelj) {
		this.natjecatelj = natjecatelj;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="VIRTEKIPA_IMA_IGRACE",
			joinColumns = @JoinColumn(name = "virt_id"),
			inverseJoinColumns = @JoinColumn(name = "igrac_id"))

	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public VirtLiga getLiga() {
		return liga;
	}

	public void setLiga(VirtLiga liga) {
		this.liga = liga;
	}
	
	@ElementCollection
	public List<Double> getPocetniBodovi() {
		return pocetniBodovi;
	}
	
	public void setPocetniBodovi(List<Double> bod) {
		this.pocetniBodovi = new ArrayList<Double>(bod);
	}
	
	@Transient
	public double getOstvareniBodovi() {
		return ostvareniBodovi;
	}

	@PostLoad
	public void setOstvareniBodovi() {
		int i=0;
		double sum = 0;
		if (pocetniBodovi.isEmpty()) {
			pocetniBodovi = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		}
		for (Igrac ig : igraci) {
			sum += ig.getBodovi() - pocetniBodovi.get(i++);
		}
		ostvareniBodovi = sum;
	}

}
