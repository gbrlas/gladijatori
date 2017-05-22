package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Liga;
import hr.gladijatori.modeli.liga.Utakmica;

@WebServlet("/servleti/rezRukLiga")
public class RezultatiRukLigaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//dobavljanje podataka za rezultate svih utakmica
		
		Liga liga = DAOProvider.getDAO().dohvatiLigu(1);
		
		if (liga != null) {
			req.setAttribute("liga", liga);
		}
		req.getRequestDispatcher("/WEB-INF/pages/RezultatiRukLige.jsp").forward(req, resp);
	}

}
