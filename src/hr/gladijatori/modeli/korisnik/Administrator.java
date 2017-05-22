package hr.gladijatori.modeli.korisnik;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Korisnik")
@DiscriminatorValue("A")
public class Administrator extends Korisnik {

}