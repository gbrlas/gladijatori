package hr.gladijatori.web.servlets;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.korisnik.Korisnik;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.korisnik.TipKorisnika;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servleti/admin/dodavanjeNovogKorisnika")
public class DodavanjeNovogKorAdminServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String ime = req.getParameter("ime");
		ime = new String (ime.getBytes ("iso-8859-1"), "UTF-8");
		String prezime = req.getParameter("prezime");
		prezime = new String (prezime.getBytes ("iso-8859-1"), "UTF-8");
		String email = req.getParameter("email");
		email = new String (email.getBytes ("iso-8859-1"), "UTF-8");
		String korime = req.getParameter("korimereg");
		korime = new String (korime.getBytes ("iso-8859-1"), "UTF-8");
		String sifra = req.getParameter("sifrareg");
		MessageDigest digest = null;
		String hashsifra = null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.update(sifra.getBytes("utf8"));
			byte[] digestBytes = digest.digest();
			hashsifra = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		String tip = req.getParameter("selKorisnik");
		tip = new String (tip.getBytes ("iso-8859-1"), "UTF-8");
		Korisnik kor = new Korisnik();
		kor.setIme(ime);
		kor.setPrezime(prezime);
		kor.setE_mail(email);
		kor.setPassword(hashsifra);
		if(tip.equals("Službena osoba")){
			kor.setTip(TipKorisnika.SLUZB);
		}
		else{
			kor.setTip(TipKorisnika.TEHN);
		}
		kor.setUsername(korime);
		DAOProvider.getDAO().dodajKorisnika(kor);
		req.setAttribute("poruka", "Korisnik je uspješno dodan");
		req.getRequestDispatcher("/WEB-INF/pages/DodajAdmin.jsp").forward(req, resp);
		
	}

}
