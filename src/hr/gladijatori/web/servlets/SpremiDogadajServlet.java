package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Dogadjaj;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.TipDogadjaja;
import hr.gladijatori.modeli.liga.Utakmica;

@WebServlet("/servleti/sluzb/spremiDog")
public class SpremiDogadajServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong((String) req.getParameter("id"));
		Utakmica utakmica = DAOProvider.getDAO().dohvatiUtakmicu(id);
		Long minuta = Long.parseLong((String) req.getParameter("vrijemedogmin"));
		Long sekunda = Long.parseLong((String) req.getParameter("vrijemedogsec"));
		Long igracId = Long.parseLong((String) req.getParameter("igraciDog"));
		String nazivDog = (String) req.getParameter("tipDog");
		Igrac igrac= DAOProvider.getDAO().dohvatiIgraca(igracId);
		Long vrijeme = minuta * 60 + sekunda;
		if (vrijeme < 1 || vrijeme > 3599) {
			return;
		}
		TipDogadjaja tip = TipDogadjaja.fromString(nazivDog);
		Dogadjaj dogadaj = new Dogadjaj();
		dogadaj.setVrijemeDogadjaja(vrijeme);
		dogadaj.setIgrac(igrac);
		dogadaj.setTip(tip);
		
		
		if (tip.toString().startsWith("Gol")) {
			int domaci = 0;
			int gost = 0;
			if (utakmica.getRezultat() == null || utakmica.getRezultat().isEmpty()) {
			} else {
				domaci = Integer.parseInt(utakmica.getRezultat().split(":")[0]);
				gost = Integer.parseInt(utakmica.getRezultat().split(":")[1]);
			}

			if (igrac.getKlub().getIme().equals(utakmica.getDomacin().getIme())) {
				domaci++;
			} else {
				gost++;
			}
			
			utakmica.setRezultat(domaci + ":" + gost);
			
		}

		DAOProvider.getDAO().dodajDogadjaj(dogadaj, utakmica);

	}
}
