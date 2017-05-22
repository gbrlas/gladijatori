package hr.gladijatori.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Dogadjaj;
import hr.gladijatori.modeli.liga.Utakmica;

@WebServlet("/servleti/sluzb/brisiDogadjaj")
public class BrisiDogadjajServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sID = req.getParameter("utakmica");
		Long idUt = null;
		try {
			idUt = Long.valueOf(sID);
			sID = req.getParameter("dog");
			Long idDog = Long.valueOf(sID);
			Utakmica ut = DAOProvider.getDAO().dohvatiUtakmicu(idUt);
			Dogadjaj dog = DAOProvider.getDAO().dohvatiDogadjaj(idDog);
			
			if (dog.getUtakmica().getId() == idUt && !ut.isZavrsena()) {			
				
				DAOProvider.getDAO().izvediUpdateUpit("DELETE FROM Dogadjaj d WHERE d.utakmica = ? AND d.id = ?", ut, idDog);
				
				if (dog.getTip().toString().startsWith("Gol")) {
					int domaci = 0;
					int gost = 0;
					if (ut.getRezultat() == null || ut.getRezultat().isEmpty()) {
					} else {
						domaci = Integer.parseInt(ut.getRezultat().split(":")[0]);
						gost = Integer.parseInt(ut.getRezultat().split(":")[1]);
					}

					if (dog.getIgrac().getKlub().getIme().equals(ut.getDomacin().getIme())) {
						domaci--;
					} else {
						gost--;
					}
					
					ut.setRezultat(domaci + ":" + gost);
					
				}
				
			}
			
			
		} catch (Exception ignorable) {
		}
		

		req.getRequestDispatcher("/servleti/prikaziUtakmicu?id="+idUt).forward(req, resp);
		
	}
}
