package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Igrac;

@WebServlet("/servleti/sluzb/postaviCijeneParam")
public class PostaviCijeneIgracaParamServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if ((Long) DAOProvider.getDAO().izvediUpit("SELECT COUNT(*) FROM DogadjajVrijednost dV").get(0) == 0) {
			
			req.setAttribute("poruka", "Nije definirana/učitana početna konfiguracija!");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
		} else if ((Long) DAOProvider.getDAO().izvediUpit("SELECT COUNT(*) FROM Dogadjaj d").get(0) != 0) {
			req.setAttribute("poruka", "Nije moguće mijenjati vrijednosti igrača nakon početka lige!");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);		}

		else {
			List<Igrac> igraci = (List<Igrac>)(Object)DAOProvider.getDAO().izvediUpit("SELECT i FROM Igrac i");
			req.setAttribute("igraci", igraci);
			req.getRequestDispatcher("/WEB-INF/pages/PostaviCijeneIgraca.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
