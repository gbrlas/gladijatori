package hr.gladijatori.modeli.liga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Dogadjaj")
public class Dogadjaj {

	private long id;
	
	/**
	 * Broj proteklih sekundi od početka utakmice.
	 */
	private long vrijemeDogadjaja;
	
	private TipDogadjaja tip;
	
	private Utakmica utakmica;
		
	private Igrac igrac;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column
	public long getVrijemeDogadjaja() {
		return vrijemeDogadjaja;
	}

	public void setVrijemeDogadjaja(long vrijemeDogadjaja) {
		this.vrijemeDogadjaja = vrijemeDogadjaja;
	}
	
	@Enumerated(EnumType.STRING)
	public TipDogadjaja getTip() {
		return tip;
	}

	public void setTip(TipDogadjaja tip) {
		this.tip = tip;
	}
	
	@ManyToOne
	@JoinColumn(nullable = false)
	public Utakmica getUtakmica() {
		return utakmica;
	}

	public void setUtakmica(Utakmica utakmica) {
		this.utakmica = utakmica;
	}
	
	
	@ManyToOne
	public Igrac getIgrac() {
		return igrac;
	}
	
	public void setIgrac(Igrac igrac) {
		this.igrac = igrac;
	}
	
	
	
	
}
