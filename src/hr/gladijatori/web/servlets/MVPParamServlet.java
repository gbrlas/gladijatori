package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Kolo;
import hr.gladijatori.modeli.liga.Liga;
import hr.gladijatori.modeli.liga.Utakmica;

@WebServlet("/servleti/tehn/mvpParam")
public class MVPParamServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Liga liga = DAOProvider.getDAO().dohvatiLigu(1);
		
		List<Kolo> kola = new ArrayList<>(liga.getKola());
		
		Iterator<Kolo> it = kola.iterator();
		
		while(it.hasNext()) {
			Kolo kolo = it.next();
			
			Iterator<Utakmica> utIt = kolo.getUtakmice().iterator();
			
			while(utIt.hasNext()) {
				Utakmica ut = utIt.next();
				if (!ut.isZavrsena() || ut.getMvp() != null) {
					utIt.remove();
				}
			}
			
			if (kolo.getUtakmice().isEmpty()) it.remove();
		}
		
		req.setAttribute("kola", kola);
		req.getRequestDispatcher("/WEB-INF/pages/UtakmiceBezMVP.jsp").forward(req, resp);
		
	}
}
