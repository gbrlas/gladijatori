package hr.gladijatori.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.virtliga.VirtEkipa;

@WebServlet("/servleti/virt/prikaziVirtEkipu")
public class PrikaziVirtEkipu extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sID = req.getParameter("id");
		Long id = null;
		try {
			id = Long.valueOf(sID);
		} catch(Exception ignorable) {
		}
		if(id!=null) {
			VirtEkipa virtEkipa = DAOProvider.getDAO().dohvatiEkipu(id);
			if(virtEkipa != null) {
				req.setAttribute("virtEkipa", virtEkipa);
			}
		}
		req.getRequestDispatcher("/WEB-INF/pages/PrikazVirtEkipe.jsp").forward(req, resp);
	}
}
