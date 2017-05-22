package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.liga.Klub;

@WebServlet("/servleti/virt/poredakPoKlubovima")
public class PoredakPoKlubovimaServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Klub> klubovi = (List<Klub>)(Object)DAOProvider.getDAO().izvediUpit("SELECT klub FROM Klub klub");

		List<Natjecatelj> natjecatelji = DAOProvider.getDAO().dohvatiLigu("Virtualna liga").getNatjecatelji();
		
		natjecatelji.sort(new Comparator<Natjecatelj>() {

			@Override
			public int compare(Natjecatelj o1, Natjecatelj o2) {
				return -Double.compare(o1.getEkipa().getOstvareniBodovi(), o2.getEkipa().getOstvareniBodovi());
			}
		});
		Klub bezveze = new Klub();
		bezveze.setIme("-");
		klubovi.add(0, bezveze);
		req.setAttribute("klubovi", klubovi);
		req.setAttribute("natjecatelji", natjecatelji);
		req.getRequestDispatcher("/WEB-INF/pages/PoredakKlubovi.jsp").forward(req, resp);
		
	}
	
	

}
