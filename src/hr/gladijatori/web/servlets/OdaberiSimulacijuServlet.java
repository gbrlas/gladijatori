package hr.gladijatori.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Liga;

@WebServlet("/servleti/admin/odabirzasim")
public class OdaberiSimulacijuServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Liga liga = DAOProvider.getDAO().dohvatiLigu(1);
		
		req.setAttribute("liga", liga);
		req.getRequestDispatcher("/WEB-INF/pages/OdabirSimulacije.jsp").forward(req, resp);
	}
}
