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

@WebServlet("/servleti/virt/sudioniciVirtLige")
public class SudVirtLigaServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<Natjecatelj> natjecatelji = DAOProvider.getDAO().dohvatiLigu("Virtualna liga").getNatjecatelji();
		
		req.setAttribute("sudionici", natjecatelji);
		req.getRequestDispatcher("/WEB-INF/pages/SudVirtLige.jsp").forward(req, resp);
	}

}
