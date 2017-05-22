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
import hr.gladijatori.modeli.virtliga.VirtEkipa;

@WebServlet("/servleti/nat/mojaEkipa")
public class MojaEkipaServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Natjecatelj nat = (Natjecatelj) req.getSession().getAttribute("korisnik");
		
		List<Igrac> igraci = nat.getEkipa().getIgraci();
		req.setAttribute("igraci", igraci);
		
		req.getRequestDispatcher("/WEB-INF/pages/MojaEkipa.jsp").forward(req, resp);;
	}
}
