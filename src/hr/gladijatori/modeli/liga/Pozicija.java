package hr.gladijatori.modeli.liga;
public enum Pozicija {
	GOLMAN("Golman"),
	L_VANJSKI("Lijevi vanjski"), 
	D_VANJSKI("Desni vanjski"), 
	S_VANJSKI("Srednji vanjski"), 
	PIVOT("Pivot"), 
	L_KRILO("Lijevo krilo"), 
	D_KRILO("Desno krilo");
	
	private final String name;
	
	private Pozicija(String s) {
		name = s;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
