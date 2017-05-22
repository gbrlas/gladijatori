package hr.gladijatori.modeli.virtliga;
import java.util.List;

import javax.jws.Oneway;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hr.gladijatori.modeli.korisnik.Natjecatelj;
@Entity
@Table(name="VirtLiga")
public class VirtLiga {
	private long id;
	private List<Natjecatelj> natjecatelji;
	private List<VirtEkipa> ekipe;
	private double proracun;
	private String imeLige;

	@Oneway
	@OneToMany(mappedBy = "liga", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	@ElementCollection
	public List<Natjecatelj> getNatjecatelji() {
		return natjecatelji;
	}

	public void setNatjecatelji(List<Natjecatelj> natjecatelji) {
		this.natjecatelji = natjecatelji;
	}
	
	@Column(unique=true, nullable=false)
	public String getIme() {
		return imeLige;
	}
	
	public void setIme(String imeLige) {
		this.imeLige = imeLige;
	}
	
	@OneToMany(mappedBy = "liga", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<VirtEkipa> getEkipe() {
		return ekipe;
	}
	
	public void setEkipe(List<VirtEkipa> ekipe) {
		this.ekipe = ekipe;
	}

	@Column
	public double getProracun() {
		return proracun;
	}

	public void setProracun(double proracun) {
		this.proracun = proracun;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
