package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.korisnik.TipKorisnika;
import hr.gladijatori.modeli.virtliga.VirtEkipa;
import hr.gladijatori.modeli.virtliga.VirtLiga;

@WebServlet("/servleti/registracija")
public class RegistracijaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//Razne provjere za regstraciju i provodenje registracije
		String ime = req.getParameter("ime");
		ime = new String (ime.getBytes ("iso-8859-1"), "UTF-8");
		String prezime = req.getParameter("prezime");
		prezime = new String (prezime.getBytes ("iso-8859-1"), "UTF-8");
		String email = req.getParameter("email");
		email = new String (email.getBytes ("iso-8859-1"), "UTF-8");
		String korime = req.getParameter("korimereg");
		korime = new String (korime.getBytes ("iso-8859-1"), "UTF-8");
		String sifra = req.getParameter("sifrareg");
		String drzava = req.getParameter("drzava");
		drzava = new String (drzava.getBytes ("iso-8859-1"), "UTF-8");
		String nazivVirEkipe = req.getParameter("nazvirekip");
		nazivVirEkipe = new String (nazivVirEkipe.getBytes ("iso-8859-1"), "UTF-8");
		String podrzaniKlub = req.getParameter("podrzKlub");
		podrzaniKlub = new String (podrzaniKlub.getBytes ("iso-8859-1"), "UTF-8");

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
		
		Natjecatelj nat = DAOProvider.getDAO().dohvatiNatjecatelja(korime);
		if(nat == null){
			//Nepostoji zapis sa tim username-om sto je ok
			Natjecatelj natjecatelj = new Natjecatelj();
			natjecatelj.setIme(ime);
			natjecatelj.setPrezime(prezime);
			natjecatelj.setE_mail(email);
			natjecatelj.setDrzava(drzava);
			natjecatelj.setPassword(hashsifra);
			natjecatelj.setTip(TipKorisnika.NAT);
			natjecatelj.setUsername(korime);
			natjecatelj.setPodrzani(DAOProvider.getDAO().dohvatiKlub(podrzaniKlub));

			VirtLiga vl = DAOProvider.getDAO().dohvatiLigu("Virtualna liga");
			natjecatelj.setLiga(vl);
			DAOProvider.getDAO().dodajKorisnika(natjecatelj);
			
			VirtEkipa ve = new VirtEkipa();
			ve.setNatjecatelj(natjecatelj);
			ve.setProracun(0);
			ve.setNaziv(nazivVirEkipe);
			ve.setIgraci(new ArrayList<>());
			DAOProvider.getDAO().dodajEkipu(ve, vl);
			natjecatelj.setEkipa(ve);
			
			req.getRequestDispatcher("/servleti/prijavaParam").forward(req, resp);
		}
		else{
			req.setAttribute("poruka", "Postoji korisnik s tim korisničkim imenom!");
			req.getRequestDispatcher("/servleti/registracijaParam").forward(req, resp);
		}
	}

	
}
