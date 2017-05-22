package hr.gladijatori.modeli.liga;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Liga")
public class Liga {
	private long id;
	private List<Klub> klubovi;
	private List<Kolo> kola;
	
	@OneToMany(mappedBy = "liga", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Klub> getKlubovi() {
		return klubovi;
	}
	
	public void setKlubovi(List<Klub> klubovi) {
		this.klubovi = klubovi;
	}
	
	@OneToMany(mappedBy = "liga", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Kolo> getKola() {
		return kola;
	}
	
	public void setKola(List<Kolo> kola) {
		this.kola = kola;
	}

	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
