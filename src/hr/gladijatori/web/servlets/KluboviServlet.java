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

@WebServlet("/servleti/klubovi")
public class KluboviServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Klub> klubovi = DAOProvider.getDAO().dohvatiKlubove();
		req.setAttribute("klubovi", klubovi);
		req.getRequestDispatcher("/WEB-INF/pages/Klubovi.jsp").forward(req, resp);
	}

}
