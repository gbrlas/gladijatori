package hr.gladijatori.modeli.korisnik;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("TK")
@Table(name="Korisnik")
public class TehnickaKomisija extends Korisnik {

}
