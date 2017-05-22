package hr.gladijatori.web.servlets;

import java.io.IOException;

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

@WebServlet("/servleti/tehn/mvp")
public class MVPServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Long utId = 0L;
		Long igId = 0L;
		
		try {
			utId = Long.parseLong(req.getParameter("utakmica"));
			igId = Long.parseLong(req.getParameter("igrac"));
			Utakmica utakmica = DAOProvider.getDAO().dohvatiUtakmicu(utId);
			Igrac igrac = DAOProvider.getDAO().dohvatiIgraca(igId);
			
			if (utakmica.isZavrsena() && utakmica.getMvp() == null) {
				utakmica.setMvp(igrac);
				Dogadjaj d = new Dogadjaj();
				d.setTip(TipDogadjaja.MVP);
				d.setIgrac(igrac);
				d.setUtakmica(utakmica);
				igrac.getDogadjaji().add(d);
			}
		} catch (Exception e) {
			
		}
		
		
				
		req.getRequestDispatcher("/servleti/prikaziUtakmicu?id=" + utId).forward(req, resp);
	}

}
