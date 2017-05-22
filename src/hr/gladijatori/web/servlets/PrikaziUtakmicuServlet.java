package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Dogadjaj;
import hr.gladijatori.modeli.liga.TipDogadjaja;
import hr.gladijatori.modeli.liga.Utakmica;

@WebServlet("/servleti/prikaziUtakmicu")
public class PrikaziUtakmicuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sID = req.getParameter("id");
		Long id = null;
		try {
			id = Long.valueOf(sID);
		} catch (Exception ignorable) {
		}
		if (id != null) {
			Utakmica utakmica = DAOProvider.getDAO().dohvatiUtakmicu(id);
			List<Dogadjaj> dogadjaji = (List<Dogadjaj>) (Object) DAOProvider.getDAO().izvediUpit(
					"SELECT d FROM Dogadjaj AS d WHERE d.utakmica = ? ORDER BY d.vrijemeDogadjaja", utakmica);
			if (utakmica != null) {
				req.setAttribute("utakmica", utakmica);
			}
			if (dogadjaji != null && !dogadjaji.isEmpty()) {
				
				if (utakmica.isZavrsena()) {
					
					Map<Long, Double> bodovi = new HashMap<>();

					for (Dogadjaj dogadjaj : dogadjaji) {
						if (bodovi.putIfAbsent(dogadjaj.getIgrac().getId(),
								DAOProvider.getDAO().dohvatiVrijednostDogadjaja(dogadjaj.getTip())) == null) {
							bodovi.merge(dogadjaj.getIgrac().getId(),
									DAOProvider.getDAO().dohvatiVrijednostDogadjaja(dogadjaj.getTip()), Double::sum);
						}
					}
					double min = Collections.min(bodovi.values());
					double max = Collections.max(bodovi.values());
					
					if (max == min) min = max-1;
					

					for (Long key : bodovi.keySet()) {
						bodovi.put(key, (9 * (bodovi.get(key) - min)) / (max - min) + 1);
					}
					req.setAttribute("bodovi", bodovi);
	
					Iterator<Dogadjaj> it = dogadjaji.iterator();

					while (it.hasNext()) {
						Dogadjaj d = it.next();
						if (d.getTip() == TipDogadjaja.BLOK || d.getTip() == TipDogadjaja.IZGUBLJENA_LOPTA
								|| d.getTip() == TipDogadjaja.UKRADENA_LOPTA || d.getTip() == TipDogadjaja.MVP
								|| d.getTip() == TipDogadjaja.PROMASAJ || d.getTip() == TipDogadjaja.PROMASAJ_7
								|| d.getTip() == TipDogadjaja.PRIMLJENI_7 || d.getTip() == TipDogadjaja.PRIMLJENI)
							it.remove();
					}
				}


				req.setAttribute("dogadjaji", dogadjaji);

			}
			req.setAttribute("tipdog", TipDogadjaja.values());


		}
		req.getRequestDispatcher("/WEB-INF/pages/PrikazUtakmice.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
