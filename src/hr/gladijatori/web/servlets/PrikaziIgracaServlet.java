package hr.gladijatori.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Igrac;

@WebServlet("/servleti/prikaziIgraca")
public class PrikaziIgracaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sID = req.getParameter("id");
		Long id = null;
		try {
			id = Long.valueOf(sID);
		} catch(Exception ignorable) {
		}
		if(id!=null) {
			Igrac igrac = DAOProvider.getDAO().dohvatiIgraca(id);
			if(igrac!=null) {
				req.setAttribute("igrac", igrac);
			}
		}
		req.getRequestDispatcher("/WEB-INF/pages/PrikazIgraca.jsp").forward(req, resp);
	}
}
