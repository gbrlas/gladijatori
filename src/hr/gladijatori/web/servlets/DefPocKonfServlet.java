package hr.gladijatori.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;

@WebServlet("/servleti/sluzb/definirajPocKonf")
public class DefPocKonfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		boolean definirana = (Long) DAOProvider.getDAO().izvediUpit("SELECT COUNT(*) FROM DogadjajVrijednost dV").get(0) > 0;

		req.setAttribute("def", definirana);
		req.getRequestDispatcher("/WEB-INF/pages/DefPocKonf.jsp").forward(req, resp);
	}

}
