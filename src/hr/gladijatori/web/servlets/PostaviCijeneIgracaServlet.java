package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Pozicija;

@WebServlet("/servleti/sluzb/postaviCijeneIgraca")
public class PostaviCijeneIgracaServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double maxIgrac = DAOProvider.getDAO().dohvatiLigu("Virtualna liga").getProracun() / 4;
		double prosjek = maxIgrac*4/7;
		
		List<Igrac> igraci = (List<Igrac>)(Object)DAOProvider.getDAO().izvediUpit("SELECT i FROM Igrac i");
		double sum[] = new double[Pozicija.values().length];
		int broj[] = new int[Pozicija.values().length];
				
		for (Igrac igrac : igraci) {
			double igracV =  Double.parseDouble(req.getParameter("" + igrac.getId()));
			
			if (igracV > maxIgrac) {
				req.setAttribute("poruka", "Nijedan igrač ne smije vrijediti više od : " + maxIgrac);
				req.getRequestDispatcher("/servleti/postaviCijeneParam").forward(req, resp);;
				return;
			}
			sum[igrac.getPozicija().ordinal()] += igracV;
			broj[igrac.getPozicija().ordinal()]++;
		}
		
		for (int i=0; i<sum.length; i++) {
			if (sum[i] / broj[i] > prosjek) {
				req.setAttribute("poruka", "Prosjek vrijednosti igrača na poziciji : " + Pozicija.values()[i] + " smije biti najviše : " + prosjek);
				req.getRequestDispatcher("/servleti/postaviCijeneParam").forward(req, resp);;
				return;
			}
		}
		
		resp.sendRedirect(req.getContextPath());
	
	}

}
