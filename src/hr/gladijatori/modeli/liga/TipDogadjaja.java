package hr.gladijatori.modeli.liga;

public enum TipDogadjaja {
	GOL_7("Gol sa 7m"), 
	GOL_U9("Gol unutar 9m"), 
	GOL_I9("Gol izvan 9m"), 
	OBRANA("Obrana"), 
	BLOK("Blok"), 
	UKRADENA_LOPTA("Ukradena lopta"), 
	OBRANA_7("Obrana sa 7m"), 
	PROMASAJ("Promašaj"), 
	PROMASAJ_7("Promašaj sa 7m"), 
	IZGUBLJENA_LOPTA("Izgubljena lopta"), 
	PRIMLJENI("Primljeni gol"), 
	PRIMLJENI_7("Primljeni gol sa 7m"), 
	ISKLJUCENJE_2("Isključenje 2min"), 
	TRAJNO_ISKLJUCENJE("Trajno isključenje"), 
	IZRAVNO_ISKLJUCENJE("Izravno isključenje"), 
	MVP("MVP");


	private final String name;
	
	private TipDogadjaja(String s) {
		name = s;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	  public static TipDogadjaja fromString(String text) {
		    if (text != null) {
		      for (TipDogadjaja b : TipDogadjaja.values()) {
		        if (text.equalsIgnoreCase(b.toString())) {
		          return b;
		        }
		      }
		    }
		    return null;
		  }
}
