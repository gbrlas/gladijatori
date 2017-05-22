package hr.gladijatori.simulator;

public class DogadjajiVjerojatnost {

	//u sekundama
	public static final int MATCH_DURATION = 3600;
			
	public static final double IZRAVNO_ISKLJUCENJE = 0.02;
	
	/**
	 * Vjerojatnosti gol/šut
	 */
	public static final double PENAL_PROB = 0.93;
	public static final double U9_PROB = 0.85;
	public static final double I9_PROB = 0.60;
	
	/**
	 * Vjerojatnost otkud šuta
	 */
	public static final double SUT_I9_PROB = 0.25;
	public static final double SUT_U9_PROB = 0.65;
	public static final double SUT_7M_PROB = 0.1;
	
	
	public static final double BLOK_PROB = 0.07;
	
	public static final double NUM_ATTACKS = 52;
	
	/**
	 * Vjerojatnosti tko će dati gol ako je bio gol
	 */
	public static final double KRILO_GOL_PROB = 0.1373/0.7344;
	public static final double PIVOT_GOL_PROB = 0.1939/0.7344;
	public static final double VANJSKI_GOL_PROB = 0.3005/0.7344;
	public static final double PENAL_GOL_PROB = 0.1027/0.7344;
	
	public static final double OBRANA_PROB = 0.3419;
	public static final double OBRANA_U9_PROB = 4.0/9;
	public static final double OBRANA_I9_PROB = 9.0/14;
	
	public static final double GAUSS_FACTOR = 2;
			
	public static final int GOALS_PER_MATCH = 55;
	public static final int SENT_OFF_PER_MATCH = 10;
	
	public static final int ATTACK_DURATION = 25;
	public static final int NUM_OF_STEALS = 18;
	
	
	
}
