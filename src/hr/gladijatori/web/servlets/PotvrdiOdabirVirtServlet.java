package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import hr.gladijatori.modeli.virtliga.VirtEkipa;
import hr.gladijatori.modeli.virtliga.VirtLiga;

@WebServlet("/servleti/nat/potvrdiOdabirVirt")
public class PotvrdiOdabirVirtServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6405548393606118854L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] igraci = ((String)req.getParameter("selected")).split(",");
		Natjecatelj nat = (Natjecatelj) req.getSession().getAttribute("korisnik");
		
		if (igraci.length != 7) {
			req.setAttribute("poruka", "Potrebno odabrati 7 igrača!");
			req.getRequestDispatcher("/servleti/nat/odaberiVirtEkipu").forward(req, resp);
			return;
		}
		
		List<Pozicija> pozicije = new ArrayList<>(Arrays.asList(Pozicija.values()));
		
		if (nat.getEkipa().getIgraci() == null || nat.getEkipa().getIgraci().isEmpty()) {
			VirtLiga vl = DAOProvider.getDAO().dohvatiLigu("Virtualna liga");
			
			double sum = 0;
		
			for (String igrac : igraci) {
				sum += DAOProvider.getDAO().dohvatiIgraca(Long.parseLong(igrac)).getVrijednost();
			}
			
			if (sum > vl.getProracun()) {
				req.setAttribute("poruka", "Vrijednost igrača je veća od proračuna!");
				req.getRequestDispatcher("/servleti/nat/odaberiVirtEkipu").forward(req, resp);
				return;
			}  else {
				VirtEkipa ve = nat.getEkipa();
				
				List<Igrac> ig = new ArrayList<>();
				for(String igrac : igraci) {
					Igrac i = DAOProvider.getDAO().dohvatiIgraca(Long.parseLong(igrac));
					if (pozicije.contains(i.getPozicija())) {
						pozicije.remove(i.getPozicija());
					} else {
						req.setAttribute("poruka", "Pogrešan odabir igrača!");
						req.getRequestDispatcher("/servleti/nat/odaberiVirtEkipu").forward(req, resp);
						return;
					}
				}
				ve.getPocetniBodovi().clear();
				if (pozicije.size() != 0) {
					req.setAttribute("poruka","Pogrešan odabir!");
					req.getRequestDispatcher("/servleti/nat/odaberiVirtEkipu").forward(req, resp);
					return;

				}
				for(String igrac : igraci) {
					Igrac i = DAOProvider.getDAO().dohvatiIgraca(Long.parseLong(igrac));
					ig.add(i);
					ve.getPocetniBodovi().add(i.getBodovi());
					DAOProvider.getDAO().dodajIgracaUEkipu(i, ve);
				}
				

				req.setAttribute("igraci", ig);
				req.getRequestDispatcher("/WEB-INF/pages/MojaEkipa.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("igraci", nat.getEkipa().getIgraci());
			req.getRequestDispatcher("/WEB-INF/pages/MojaEkipa.jsp").forward(req, resp);
		}
		
		
		

		
	}

}
