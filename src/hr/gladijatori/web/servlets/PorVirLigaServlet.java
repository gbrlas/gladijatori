package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.virtliga.VirtEkipa;

@WebServlet("/servleti/virt/poredakvirtualnaLiga")
public class PorVirLigaServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<VirtEkipa> ekipe = DAOProvider.getDAO().dohvatiLigu("Virtualna liga").getEkipe();
		ekipe.sort(new Comparator<VirtEkipa>() {

			@Override
			public int compare(VirtEkipa o1, VirtEkipa o2) {
				return -Double.compare(o1.getOstvareniBodovi(), o2.getOstvareniBodovi());
			}
		});
		
		req.setAttribute("ekipe", ekipe);
		req.getRequestDispatcher("/WEB-INF/pages/PoredakVirtLige.jsp").forward(req, resp);
	}

}
