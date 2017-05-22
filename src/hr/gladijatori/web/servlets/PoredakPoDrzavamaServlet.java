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

@WebServlet("/servleti/virt/poredakPoDrzavama")
public class PoredakPoDrzavamaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<String> drzave = (List<String>) (Object) DAOProvider.getDAO()
				.izvediUpit("SELECT DISTINCT(nat.drzava) FROM Korisnik nat");

		List<Natjecatelj> natjecatelji = DAOProvider.getDAO().dohvatiLigu("Virtualna liga").getNatjecatelji();

		natjecatelji.sort(new Comparator<Natjecatelj>() {

			@Override
			public int compare(Natjecatelj o1, Natjecatelj o2) {
				return -Double.compare(o1.getEkipa().getOstvareniBodovi(), o2.getEkipa().getOstvareniBodovi());
			}
		});
		
		if (drzave.get(0) == null) drzave.set(0, "-");
		req.setAttribute("drzave", drzave);
		req.setAttribute("natjecatelji", natjecatelji);

		req.getRequestDispatcher("/WEB-INF/pages/PoredakDrzave.jsp").forward(req, resp);

	}

}
