package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOException;
import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.korisnik.TipKorisnika;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Pozicija;
import hr.gladijatori.modeli.virtliga.VirtEkipa;
import hr.gladijatori.modeli.virtliga.VirtLiga;
import hr.gladijatori.web.test.XLSread;

@WebServlet("/servleti/admin/ucitajNatjecatelje")
public class UcitajNatjecateljeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		XLSread reader = null;
		try {
			reader = new XLSread(req.getServletContext().getRealPath("/files/Virtualna-liga-DI-1.xls"));
		} catch (Exception ex) {
			req.setAttribute("poruka", "U direktoriju /files/ mora postojati datoteka Virtualna-liga-DI-1.xls");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}

		List<Natjecatelj> natjecatelji = reader.getNatjecatelji();
		Iterator<Natjecatelj> it = natjecatelji.iterator();
		while (it.hasNext()) {
			if (it.next().getIme().isEmpty())
				it.remove();
		}
		List<VirtEkipa> ve = reader.getVirtekipe();
		Iterator<VirtEkipa> itVe = ve.iterator();
		while (itVe.hasNext()) {
			if (itVe.next().getNaziv().isEmpty())
				itVe.remove();
		}

		try {
			dodajLigu(natjecatelji, ve);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("poruka", "Natjecatelji već učitani!");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}

		resp.sendRedirect(req.getContextPath());
		return;

	}

	private static VirtLiga dodajLigu(List<Natjecatelj> natjecatelji, List<VirtEkipa> ekipe)
			throws DAOException, UnsupportedEncodingException {
		Random rand = new Random();
		VirtLiga liga = DAOProvider.getDAO().dohvatiLigu("Virtualna liga");

		
		if (liga.getEkipe() == null || liga.getEkipe().isEmpty()) {
			// liga.setEkipe(ekipe);
		} else {
			ekipe = liga.getEkipe();
		}
		
		for (VirtEkipa ve : ekipe) ve.setLiga(liga);

		if (liga.getNatjecatelji() == null || liga.getNatjecatelji().isEmpty()) {
			for (Natjecatelj n : natjecatelji) {

				MessageDigest digest = null;
				String hashsifra = null;
				try {
					digest = MessageDigest.getInstance("SHA-1");
					digest.update(n.getPassword().getBytes("utf8"));
					byte[] digestBytes = digest.digest();
					hashsifra = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				;
				n.setPassword(hashsifra);
				n.setLiga(liga);
				n.setPodrzani(DAOProvider.getDAO().dohvatiKlub(n.getPodrzani().getIme()));
				DAOProvider.getDAO().dodajKorisnika(n);

			}

		} else {
			natjecatelji = liga.getNatjecatelji();
		}

		List<Igrac> igraci = (List<Igrac>) (Object) DAOProvider.getDAO().izvediUpit("SELECT ig FROM Igrac ig");
		int size = igraci.size();

		for (VirtEkipa e : ekipe) {

			List<Igrac> ekipa = new ArrayList<>();

			for (Pozicija poz : Pozicija.values()) {
				while (true) {
					Igrac odabran = igraci.get(rand.nextInt(size));
					if (odabran.getPozicija().equals(poz)) {
						ekipa.add(odabran);
						break;
					}
				}
			}

			e.setIgraci(ekipa);
			DAOProvider.getDAO().dodajEkipu(e, liga);

		}

		return liga;
	}

}
