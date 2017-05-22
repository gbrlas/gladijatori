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
import hr.gladijatori.modeli.liga.Klub;


@WebServlet("/servleti/poredakRukLige")
public class PorRukLigeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Dobavljanje podataka iz baze za poredak i o klubovima
		//te sljanje tih parametara na Pocetna.jsp (odgovorna je 
	    //za poredak rukometne lige.
		
		List<Klub> klubovi = DAOProvider.getDAO().dohvatiKlubove();
		
		klubovi.sort(new Comparator<Klub>() {

			@Override
			public int compare(Klub o1, Klub o2) {
				int ret1 = Integer.compare(o2.getOstvareniBodovi(), o1.getOstvareniBodovi());
				if (ret1 == 0) {
					ret1 = Integer.compare(o2.getDaliGolova() - o2.getPrimiliGolova(), o1.getDaliGolova() - o1.getPrimiliGolova());
				}
				if (ret1 == 0) {
					ret1 = Integer.compare(o2.getDaliGolova(), o1.getDaliGolova());
				}
				return ret1;
			}
		});
		
		req.setAttribute("klubovi", klubovi);		
		req.getRequestDispatcher("/WEB-INF/pages/PoredakRukometneLige.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
