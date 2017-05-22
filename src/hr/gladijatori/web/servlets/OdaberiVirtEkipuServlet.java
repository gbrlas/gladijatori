package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Pozicija;

@WebServlet("/servleti/nat/odaberiVirtEkipu")
public class OdaberiVirtEkipuServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		if (((Natjecatelj)req.getSession().getAttribute("korisnik")).getEkipa().getIgraci() != null && !((Natjecatelj)req.getSession().getAttribute("korisnik")).getEkipa().getIgraci().isEmpty() ) {
			req.setAttribute("poruka", "Već imate virtualnu ekipu!");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}
		
		if (DAOProvider.getDAO().dohvatiLigu("Virtualna liga").getProracun() <= 0) {
			req.setAttribute("poruka", "Nije učitana početna konfiguracija!");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}
		
		List<Igrac> golmani = DAOProvider.getDAO().dohvatiIgraca(Pozicija.GOLMAN);
		List<Igrac> desna_krila = DAOProvider.getDAO().dohvatiIgraca(Pozicija.D_KRILO);
		List<Igrac> lijeva_krila = DAOProvider.getDAO().dohvatiIgraca(Pozicija.L_KRILO);
		List<Igrac> srednji_vanjski = DAOProvider.getDAO().dohvatiIgraca(Pozicija.S_VANJSKI);
		List<Igrac> lijevi_vanjski = DAOProvider.getDAO().dohvatiIgraca(Pozicija.L_VANJSKI);
		List<Igrac> desni_vanjski = DAOProvider.getDAO().dohvatiIgraca(Pozicija.D_VANJSKI);
		List<Igrac> pivoti = DAOProvider.getDAO().dohvatiIgraca(Pozicija.PIVOT);
		
		
		req.setAttribute("proracun", DAOProvider.getDAO().dohvatiLigu("Virtualna liga").getProracun());
		req.setAttribute("golmani", golmani);
		req.setAttribute("dkrila", desna_krila);
		req.setAttribute("lkrila", lijeva_krila);
		req.setAttribute("svanjski", srednji_vanjski);
		req.setAttribute("lvanjski", lijevi_vanjski);
		req.setAttribute("dvanjski", desni_vanjski);
		req.setAttribute("pivoti", pivoti);
				
		req.getRequestDispatcher("/WEB-INF/pages/OdabirVirtEkipe.jsp").forward(req, resp);

	}

	
	
}
