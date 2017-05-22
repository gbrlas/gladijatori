package hr.gladijatori.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Dogadjaj;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.liga.Pozicija;
import hr.gladijatori.modeli.liga.TipDogadjaja;
import hr.gladijatori.modeli.liga.Utakmica;

public class Simulator {

	private static final int INC_DOG_STEAL = 20;
	private static final int INC_DOG_ISKLJ = 8;
	private static final int MIN_SEK = 20;
	private static final int MAX_SEK = 30;
	private static final int BROJ_ISKLJ_ZA_TRAJNO = 3;
	private static final int DVIJE_MIN = 120;
	private static final int KRAJ_UTAKMICE = 3599;
	private static final int BOD_POBJEDA = 3;
	private static final int BOD_NERIJESENO = 1;

	private int domaci = 0;
	private int gost = 0;
	private Random rand;

	public Simulator() {
		rand = new Random();
	}

	public int ukupnoGolova() {
		return domaci + gost;
	}

	public void simuliraj(Utakmica utakmica) {
		List<Igrac> iskljuceni = new ArrayList<>();
		Map<Igrac, Integer[]> broj_vrijemeIskljucenja = new HashMap<>();
		domaci = 0;
		gost = 0;
		double ukupnaVr = utakmica.getDomacin().getVrijednostKluba() + utakmica.getGost().getVrijednostKluba();
		double domVr = utakmica.getDomacin().getVrijednostKluba() / ukupnaVr;
		double gostVr = utakmica.getGost().getVrijednostKluba() / ukupnaVr;
		int curr_time = 0;

		double fact = domVr - gostVr;

		int brojNapadaD = (int) Math.ceil(
				(rand.nextGaussian() + fact * DogadjajiVjerojatnost.GAUSS_FACTOR) * DogadjajiVjerojatnost.GAUSS_FACTOR
						+ DogadjajiVjerojatnost.NUM_ATTACKS);
		int brojNapadaG = (int) Math.ceil(
				(rand.nextGaussian() - fact * DogadjajiVjerojatnost.GAUSS_FACTOR) * DogadjajiVjerojatnost.GAUSS_FACTOR
						+ DogadjajiVjerojatnost.NUM_ATTACKS);

		int ukupanBrojNapada = brojNapadaD + brojNapadaG;

		double dom_napad_prob = 1.0 * brojNapadaD / ukupanBrojNapada;
		double gost_napad_prob = 1 - dom_napad_prob;

		double gol_prob = 1.0 * DogadjajiVjerojatnost.GOALS_PER_MATCH / ukupanBrojNapada;

		int totalEvents = ukupanBrojNapada + DogadjajiVjerojatnost.NUM_OF_STEALS
				+ DogadjajiVjerojatnost.SENT_OFF_PER_MATCH;
		double steal_prob = 1.0 * DogadjajiVjerojatnost.NUM_OF_STEALS / (totalEvents + INC_DOG_STEAL);
		double sent_off_prob = 1.0 * DogadjajiVjerojatnost.SENT_OFF_PER_MATCH / (totalEvents + INC_DOG_ISKLJ);
		double shot_prob = 1 - steal_prob - sent_off_prob;

		while (true) {
			curr_time += rand.nextInt(MAX_SEK - MIN_SEK) + MIN_SEK;
			Iterator<Igrac> it = iskljuceni.iterator();

			while (it.hasNext()) {
				Igrac sljedeci = it.next();
				Integer[] vrijednosti = broj_vrijemeIskljucenja.get(sljedeci);

				if (vrijednosti[0] >= BROJ_ISKLJ_ZA_TRAJNO) {
					continue;
				}
				if (vrijednosti[1] + DVIJE_MIN <= curr_time) {
					it.remove();
				}
			}

			if (curr_time > KRAJ_UTAKMICE)
				break;

			int event = whatEventHappens(shot_prob, steal_prob, sent_off_prob);
			if (event == 0) {
				sutPoGolu(utakmica, curr_time, dom_napad_prob, gost_napad_prob, gol_prob, iskljuceni);
			} else if (event == 1) {
				ukradena_lopta(utakmica, curr_time, iskljuceni);
			} else {
				iskljucenje(utakmica, curr_time, iskljuceni, broj_vrijemeIskljucenja);
			}

		}

		if (domaci > gost) {
			utakmica.getDomacin().setOstvareniBodovi(utakmica.getDomacin().getOstvareniBodovi() + BOD_POBJEDA);
		} else if (gost > domaci) {
			utakmica.getGost().setOstvareniBodovi(utakmica.getGost().getOstvareniBodovi() + BOD_POBJEDA);
		} else {
			utakmica.getDomacin().setOstvareniBodovi(utakmica.getDomacin().getOstvareniBodovi() + BOD_NERIJESENO);
			utakmica.getGost().setOstvareniBodovi(utakmica.getGost().getOstvareniBodovi() + BOD_NERIJESENO);
		}

		utakmica.setZavrsena(true);

	}

	private Dogadjaj iskljucenje(Utakmica utakmica, int curr_time, List<Igrac> iskljuceni,
			Map<Igrac, Integer[]> broj_vrijemeIskljucenja) {

		List<Igrac> sviIgraci = new ArrayList<>(utakmica.getDomacin().getIgraci());
		sviIgraci.addAll(utakmica.getGost().getIgraci());
		Collections.shuffle(sviIgraci);
		Dogadjaj dogadjaj = new Dogadjaj();
		dogadjaj.setVrijemeDogadjaja(curr_time);
		dogadjaj.setTip(TipDogadjaja.ISKLJUCENJE_2);
		dogadjaj.setUtakmica(utakmica);

		for (Igrac igrac : sviIgraci) {
			if (igrac.getPozicija() != Pozicija.GOLMAN && !iskljuceni.contains(igrac)) {
				if (rand.nextDouble() <= DogadjajiVjerojatnost.IZRAVNO_ISKLJUCENJE) {
					broj_vrijemeIskljucenja.put(igrac, new Integer[] { 3, curr_time });
					dogadjaj.setTip(TipDogadjaja.IZRAVNO_ISKLJUCENJE);
				} else if (broj_vrijemeIskljucenja.containsKey(igrac)) {
					broj_vrijemeIskljucenja.get(igrac)[0]++;
					if (broj_vrijemeIskljucenja.get(igrac)[0] > BROJ_ISKLJ_ZA_TRAJNO) {
						continue;
					} else if (broj_vrijemeIskljucenja.get(igrac)[0] == BROJ_ISKLJ_ZA_TRAJNO) {
						Dogadjaj trajno = new Dogadjaj();
						trajno.setVrijemeDogadjaja(curr_time);
						trajno.setUtakmica(utakmica);
						trajno.setTip(TipDogadjaja.TRAJNO_ISKLJUCENJE);
						trajno.setIgrac(igrac);
						DAOProvider.getDAO().dodajDogadjaj(trajno, utakmica);
					}
					broj_vrijemeIskljucenja.get(igrac)[1] = curr_time;
				} else {
					broj_vrijemeIskljucenja.put(igrac, new Integer[] { 1, curr_time });
				}
				iskljuceni.add(igrac);
				dogadjaj.setIgrac(igrac);
				break;
			}
		}
		DAOProvider.getDAO().dodajDogadjaj(dogadjaj, utakmica);

		return dogadjaj;
	}

	private Dogadjaj ukradena_lopta(Utakmica utakmica, int curr_time, List<Igrac> iskljuceni) {
		Dogadjaj dogadjaj = new Dogadjaj();
		Dogadjaj greska = new Dogadjaj();
		dogadjaj.setVrijemeDogadjaja(curr_time);
		dogadjaj.setTip(TipDogadjaja.UKRADENA_LOPTA);
		dogadjaj.setUtakmica(utakmica);
		greska.setVrijemeDogadjaja(curr_time);
		greska.setTip(TipDogadjaja.IZGUBLJENA_LOPTA);
		greska.setUtakmica(utakmica);
		List<Igrac> domaci = utakmica.getDomacin().getIgraci();
		Collections.shuffle(domaci);
		List<Igrac> gosti = utakmica.getGost().getIgraci();
		Collections.shuffle(gosti);
		for (Igrac igrac : domaci) {
			if (igrac.getPozicija() != Pozicija.GOLMAN && !iskljuceni.contains(igrac)) {
				if (rand.nextDouble() <= 0.5) {
					dogadjaj.setIgrac(igrac);
				} else {
					greska.setIgrac(igrac);
				}
			}
		}
		for (Igrac igrac : gosti) {
			if (igrac.getPozicija() != Pozicija.GOLMAN && !iskljuceni.contains(igrac)) {
				if (rand.nextDouble() > 0.5) {
					dogadjaj.setIgrac(igrac);
				} else {
					greska.setIgrac(igrac);
				}
			}
		}

		DAOProvider.getDAO().dodajDogadjaj(dogadjaj, utakmica);
		DAOProvider.getDAO().dodajDogadjaj(greska, utakmica);

		return dogadjaj;
	}

	private int whatEventHappens(double shot_prob, double steal_prob, double sent_off_prob) {
		double p = rand.nextDouble();
		double prob = 0;
		prob += shot_prob;
		if (p <= prob)
			return 0;
		prob += steal_prob;
		if (p <= prob)
			return 1;
		return 2;
	}

	private Dogadjaj sutPoGolu(Utakmica utakmica, long time, double dom_napad_prob, double gost_napad_prob,
			double gol_prob, List<Igrac> iskljuceni) {

		double p = rand.nextDouble();
		Dogadjaj dogadjaj = new Dogadjaj();
		dogadjaj.setVrijemeDogadjaja(time);
		dogadjaj.setUtakmica(utakmica);
		boolean flag = false;
		Igrac suter, golman;
		double suter_prob = 0, golman_prob = 0;

		if (p <= dom_napad_prob) {
			flag = true;
			suter = tkoSuta(utakmica.getDomacin(), iskljuceni);
			while (iskljuceni.contains(suter))
				suter = tkoSuta(utakmica.getDomacin(), iskljuceni);
			golman = tkoBrani(utakmica.getGost());
		} else {
			suter = tkoSuta(utakmica.getGost(), iskljuceni);
			while (iskljuceni.contains(suter))
				suter = tkoSuta(utakmica.getGost(), iskljuceni);
			golman = tkoBrani(utakmica.getDomacin());
		}

		double sut_val = suter.getVrijednost() / (suter.getVrijednost() + golman.getVrijednost());
		double gol_val = 1 - sut_val;
		int otkudSut = otkudSut();
		suter_prob += (sut_val - gol_val) / 10;
		golman_prob += (gol_val - sut_val) / 10;

		// Ako je šut unutar 9
		if (otkudSut == 0) {
			suter_prob += DogadjajiVjerojatnost.U9_PROB;
			golman_prob += DogadjajiVjerojatnost.OBRANA_U9_PROB;
		} else if (otkudSut == 1) { // Šut izvan 9
			suter_prob += DogadjajiVjerojatnost.I9_PROB;
			golman_prob += DogadjajiVjerojatnost.OBRANA_I9_PROB;
		} else { // Šut sa 7m
			suter_prob += DogadjajiVjerojatnost.PENAL_PROB;
			golman_prob += DogadjajiVjerojatnost.OBRANA_PROB;
		}

		p = rand.nextDouble();
		if (p <= suter_prob) {
			p = rand.nextDouble();
			if (p <= DogadjajiVjerojatnost.BLOK_PROB) {
				dogadjaj.setTip(TipDogadjaja.BLOK);
				Klub klub;
				if (flag) {
					klub = utakmica.getDomacin();
				} else {
					klub = utakmica.getGost();
				}
				dogadjaj.setIgrac(nadjiBlok(klub, iskljuceni));
			} else if (p <= golman_prob) {
				dogadjaj.setTip(TipDogadjaja.OBRANA);
				dogadjaj.setIgrac(golman);
			} else {
				if (flag) {
					domaci++;
				} else {
					gost++;
				}
				dogadjaj.setIgrac(suter);
				Dogadjaj primio = new Dogadjaj();
				primio.setUtakmica(utakmica);
				primio.setVrijemeDogadjaja(time);
				primio.setIgrac(golman);
				switch (otkudSut) {
				case 0:
					dogadjaj.setTip(TipDogadjaja.GOL_U9);
					primio.setTip(TipDogadjaja.PRIMLJENI);
					break;
				case 1:
					dogadjaj.setTip(TipDogadjaja.GOL_I9);
					primio.setTip(TipDogadjaja.PRIMLJENI);
					break;
				default:
					dogadjaj.setTip(TipDogadjaja.GOL_7);
					primio.setTip(TipDogadjaja.PRIMLJENI_7);

				}

				DAOProvider.getDAO().dodajDogadjaj(primio, utakmica);
			}
		} else {
			dogadjaj.setIgrac(suter);
			if (otkudSut == 2) {
				dogadjaj.setTip(TipDogadjaja.PROMASAJ_7);
			} else {
				dogadjaj.setTip(TipDogadjaja.PROMASAJ);
			}
		}

		DAOProvider.getDAO().dodajDogadjaj(dogadjaj, utakmica);
		return dogadjaj;

	}

	private Igrac nadjiBlok(Klub klub, List<Igrac> iskljuceni) {
		List<Igrac> igraci = new ArrayList<>(klub.getIgraci());
		Collections.shuffle(igraci);

		for (Igrac igrac : igraci) {
			if (igrac.getPozicija() == Pozicija.GOLMAN && iskljuceni.contains(igrac))
				continue;
			return igrac;
		}

		return null;
	}

	private int otkudSut() {
		double p = rand.nextDouble();
		double prob = DogadjajiVjerojatnost.SUT_U9_PROB;
		if (p <= prob)
			return 0;
		prob += DogadjajiVjerojatnost.SUT_I9_PROB;
		if (p <= prob)
			return 1;
		return 2;
	}

	private Igrac tkoBrani(Klub klub) {
		List<Igrac> igraci = klub.getIgraci();
		Collections.shuffle(igraci);
		for (Igrac igrac : igraci) {
			if (igrac.getPozicija() == Pozicija.GOLMAN)
				return igrac;
		}
		return null;
	}

	private Igrac tkoSuta(Klub klub, List<Igrac> iskljuceni) {
		double p = rand.nextDouble();
		double prob = 0;

		List<Igrac> igraci = new ArrayList<>(klub.getIgraci());
		Collections.shuffle(igraci);

		for (Igrac igrac : igraci) {
			if (igrac.getPozicija() == Pozicija.GOLMAN && iskljuceni.contains(igrac)) {
				continue;

			}
			double noviProb = 0;

			switch (igrac.getPozicija()) {
			case L_KRILO:
			case D_KRILO:
				noviProb = DogadjajiVjerojatnost.KRILO_GOL_PROB;
				break;
			case L_VANJSKI:
			case D_VANJSKI:
			case S_VANJSKI:
				noviProb = DogadjajiVjerojatnost.VANJSKI_GOL_PROB;
				break;
			case PIVOT:
				noviProb = DogadjajiVjerojatnost.PIVOT_GOL_PROB;
				break;
			default:
				noviProb = 0;
			}

			prob += noviProb / 4;
			if (p <= prob) {
				return igrac;
			}
		}

		for (Igrac igrac : igraci) {
			if (igrac.getPozicija() == Pozicija.GOLMAN && iskljuceni.contains(igrac))
				continue;
			return igrac;
		}

		return null;
	}

	public int getDomaci() {
		return domaci;
	}

	public int getGost() {
		return gost;
	}

}
