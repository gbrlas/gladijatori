package hr.gladijatori.web.servlets;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Dogadjaj;
import hr.gladijatori.modeli.liga.Utakmica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servleti/sluzb/zavrsiUtakmicu")
public class ZavrsiUtakmicuServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long id = Long.parseLong((String)req.getParameter("id"));
		Utakmica utakmica = DAOProvider.getDAO().dohvatiUtakmicu(id);
		List<Dogadjaj> dog = utakmica.getDogadjaji();
		if(dog.size()>0){
			utakmica.setZavrsena(true);
			req.getRequestDispatcher("/servleti/prikaziUtakmicu?id="+id).forward(req, resp);
		}
		else {
			req.setAttribute("poruka", "Utakmica se nemoze zavrsiti jer se nije dogodio niti jedan dogadaj.");
			req.getRequestDispatcher("/servleti/prikaziUtakmicu?id="+id).forward(req, resp);
		}
		
		
	}

}
