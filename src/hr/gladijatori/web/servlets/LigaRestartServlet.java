package hr.gladijatori.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.liga.Kolo;
import hr.gladijatori.modeli.liga.Liga;
import hr.gladijatori.modeli.liga.Utakmica;

@WebServlet("/servleti/admin/restartLige")
public class LigaRestartServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DAOProvider.getDAO().izvediNativeUpdateUpit("DELETE FROM VirtEkipa_Ima_Igrace");
		DAOProvider.getDAO().izvediUpdateUpit("DELETE FROM Dogadjaj d");
		DAOProvider.getDAO().izvediUpdateUpit("DELETE FROM DogadjajVrijednost d");
		
		
		Liga liga = DAOProvider.getDAO().dohvatiLigu(1);
		
		for (Klub klub : liga.getKlubovi()) {
			klub.setDaliGolova(0);
			klub.setPrimiliGolova(0);
			klub.setOstvareniBodovi(0);
		}
		for (Kolo kolo : liga.getKola()) {
			for (Utakmica ut : kolo.getUtakmice()) {
				ut.setRezultat("");
				ut.setMvp(null);
				ut.setZavrsena(false);
				
			}
		}
		
		resp.sendRedirect(req.getContextPath());

	}

}
