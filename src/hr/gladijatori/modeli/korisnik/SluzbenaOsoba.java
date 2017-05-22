package hr.gladijatori.modeli.korisnik;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("SO")
@Table(name="Korisnik")
public class SluzbenaOsoba extends Korisnik {

}
