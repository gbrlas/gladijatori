package hr.gladijatori.modeli.virtliga;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import hr.gladijatori.modeli.liga.TipDogadjaja;

@Entity
public class DogadjajVrijednost {

	private TipDogadjaja tip;
	private double vrijednost;
	
	@Id @Column(nullable=false, unique=true)
	public TipDogadjaja getTip() {
		return tip;
	}
	public void setTip(TipDogadjaja tip) {
		this.tip = tip;
	}
	
	@Column
	public double getVrijednost() {
		return vrijednost;
	}
	public void setVrijednost(double vrijednost) {
		this.vrijednost = vrijednost;
	}
	
	
	
}
