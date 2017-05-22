package hr.gladijatori.web.servlets;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.liga.Pozicija;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servleti/registracijaParam")
public class ParamRegServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<Klub> klubovi = DAOProvider.getDAO().dohvatiKlubove();
		
		req.setAttribute("klubovi", klubovi);
		req.getRequestDispatcher("/WEB-INF/pages/Registracija.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
