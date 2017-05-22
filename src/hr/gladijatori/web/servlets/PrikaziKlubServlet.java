package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.liga.Utakmica;

@WebServlet("/servleti/prikaziKlub")
public class PrikaziKlubServlet extends HttpServlet {
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
			Klub klub = DAOProvider.getDAO().dohvatiKlub(id);
			@SuppressWarnings("unchecked")
			List<Utakmica> utakmice = (List<Utakmica>)(Object)DAOProvider.getDAO().izvediUpit("SELECT u FROM Utakmica as u WHERE u.domacin = ? OR u.gost = ?", klub, klub);
			if(klub!=null) {
				req.setAttribute("klub", klub);
			}
			if(utakmice != null) {
				req.setAttribute("utakmice", utakmice);
			}
		}
		req.getRequestDispatcher("/WEB-INF/pages/PrikazKluba.jsp").forward(req, resp);
	}

}
