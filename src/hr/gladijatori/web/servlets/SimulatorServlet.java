package hr.gladijatori.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.liga.Utakmica;
import hr.gladijatori.simulator.Simulator;

@WebServlet("/servleti/admin/simulator")
public class SimulatorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// System.out.println("NIJE");

		if ((Long) DAOProvider.getDAO().izvediUpit("SELECT COUNT(*) FROM DogadjajVrijednost dV").get(0) == 0) {
			req.setAttribute("poruka", "Nemoguće simulirati bez učitane početne konfiguracije!");
		} else {
			String[] utakmice = null;
			String sel = req.getParameter(("selected"));
			if (sel.isEmpty()) {
				req.setAttribute("poruka", "Nije odabrana nijedna utakmica za simulaciju!");
			} else {
				utakmice = sel.split(",");
			}

			if (utakmice != null) {
				Simulator sim = new Simulator();
				for (String ut : utakmice) {
					Utakmica utakmica = DAOProvider.getDAO().dohvatiUtakmicu(Long.parseLong(ut));
					if (utakmica == null)
						continue;
					if (!utakmica.getDogadjaji().isEmpty()) {
						req.setAttribute("poruka", "Neke utakmica nije bilo moguće simulirati zbog toga što su već počele!");
						continue;
					}
					sim.simuliraj(utakmica);
					Klub domacin = DAOProvider.getDAO().dohvatiKlub(utakmica.getDomacin().getId());
					Klub gost = DAOProvider.getDAO().dohvatiKlub(utakmica.getGost().getId());
					domacin.setDaliGolova(domacin.getDaliGolova() + sim.getDomaci());
					domacin.setPrimiliGolova(domacin.getPrimiliGolova() + sim.getGost());
					gost.setDaliGolova(gost.getDaliGolova() + sim.getGost());
					gost.setPrimiliGolova(gost.getPrimiliGolova() + sim.getDomaci());
					utakmica.setRezultat(sim.getDomaci() + ":" + sim.getGost());
				}
			}

		}

		//
		//
		//

		req.getRequestDispatcher("/servleti/admin/odabirzasim").forward(req, resp);

	}
}
