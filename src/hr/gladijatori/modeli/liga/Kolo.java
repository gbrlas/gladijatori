package hr.gladijatori.modeli.liga;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kolo")
public class Kolo {
	private long id;
	private List<Utakmica> utakmice;
	private Liga liga;
	private int brojKola;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy = "kolo", fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Utakmica> getUtakmice() {
		return utakmice;
	}
	
	public void setUtakmice(List<Utakmica> utakmice) {
		this.utakmice = utakmice;
	}

	@ManyToOne
	public Liga getLiga() {
		return liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}
	
	@Column
	public int getBrojKola() {
		return brojKola;
	}
	
	public void setBrojKola(int brojKola) {
		this.brojKola = brojKola;
	}

}
