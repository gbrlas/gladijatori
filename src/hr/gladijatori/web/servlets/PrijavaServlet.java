package hr.gladijatori.web.servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.korisnik.Korisnik;



@WebServlet("/servleti/prijava")
public class PrijavaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Parametri prijave pogledat u bazu i sve
		String korime = req.getParameter("korimelog");
		String sifra = req.getParameter("sifralog");
		 MessageDigest digest;
		 String hashsifra=null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.update(sifra.getBytes("utf8"));
			byte[] digestBytes = digest.digest();
		    hashsifra = javax.xml.bind.DatatypeConverter.printHexBinary(digestBytes);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Korisnik kor = DAOProvider.getDAO().dohvatiKorisnika(korime);
		if(kor != null && kor.getPassword().equals(hashsifra)){
			req.getSession().setAttribute("korisnik", kor);
			req.getRequestDispatcher("/servleti/poredakRukLige").forward(req, resp);

		}
		else{
			req.setAttribute("poruka", "Uneseno pogrešno ime ili lozinka!");
			req.getRequestDispatcher("/WEB-INF/pages/Prijava.jsp").forward(req, resp);
		}


		
		
	}

}
