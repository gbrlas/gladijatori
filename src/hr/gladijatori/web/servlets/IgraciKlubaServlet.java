package hr.gladijatori.web.servlets;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Klub;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servleti/dohvatiIgrace")
public class IgraciKlubaServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long idKlub = Long.parseLong((String)req.getParameter("id"));
		Klub klub = DAOProvider.getDAO().dohvatiKlub(idKlub);
		List<Object> igracikluba = DAOProvider.getDAO().izvediUpit("select i from Igrac as i where i.klub = ?",klub);
		req.setAttribute("igracikluba", igracikluba);
		req.getRequestDispatcher("/WEB-INF/pages/IgraciKluba.jsp").forward(req, resp);
		
	}

}
