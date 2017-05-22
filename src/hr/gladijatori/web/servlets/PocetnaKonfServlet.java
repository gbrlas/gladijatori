package hr.gladijatori.web.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.TipDogadjaja;
import hr.gladijatori.modeli.virtliga.DogadjajVrijednost;

@WebServlet("/servleti/admin/pocetnakonf")
public class PocetnaKonfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if ((Long) DAOProvider.getDAO().izvediUpit("SELECT COUNT(*) FROM DogadjajVrijednost dV").get(0) == 0) {
			ServletContext context = getServletContext();
			HSSFWorkbook wb = null;
			try {
				String FILE_PATH = context.getRealPath("/WEB-INF/files/pocKonfig.xls");
				wb = new HSSFWorkbook(new FileInputStream(new File(FILE_PATH)));
			} catch (Exception e) {
				req.setAttribute("poruka", "Početna konfiguracija nije pronađena!");
				req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
				return;
			}

			HSSFSheet sheet = wb.getSheet("1");

			Map<Object, Double> ucitaneVrijednosti = new HashMap<>();

			HSSFRow row = sheet.getRow(1);
			double vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().dohvatiLigu("Virtualna liga").setProracun(vr);
			ucitaneVrijednosti.put("Proracun", vr);
			row = sheet.getRow(2);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.GOL_U9, vr);
			ucitaneVrijednosti.put(TipDogadjaja.GOL_U9, vr);
			row = sheet.getRow(3);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.GOL_7, vr);
			ucitaneVrijednosti.put(TipDogadjaja.GOL_7, vr);
			row = sheet.getRow(4);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.GOL_I9, vr);
			ucitaneVrijednosti.put(TipDogadjaja.GOL_I9, vr);
			row = sheet.getRow(5);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.OBRANA, vr);
			ucitaneVrijednosti.put(TipDogadjaja.OBRANA, vr);
			row = sheet.getRow(6);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.BLOK, vr);
			ucitaneVrijednosti.put(TipDogadjaja.BLOK, vr);
			row = sheet.getRow(7);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.UKRADENA_LOPTA, vr);
			ucitaneVrijednosti.put(TipDogadjaja.UKRADENA_LOPTA, vr);
			row = sheet.getRow(8);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.OBRANA_7, vr);
			ucitaneVrijednosti.put(TipDogadjaja.OBRANA_7, vr);
			row = sheet.getRow(9);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.MVP, vr);
			ucitaneVrijednosti.put(TipDogadjaja.MVP, vr);
			row = sheet.getRow(10);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.PROMASAJ, vr);
			ucitaneVrijednosti.put(TipDogadjaja.PROMASAJ, vr);
			row = sheet.getRow(11);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.PROMASAJ_7, vr);
			ucitaneVrijednosti.put(TipDogadjaja.PROMASAJ_7, vr);
			row = sheet.getRow(12);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.IZGUBLJENA_LOPTA, vr);
			ucitaneVrijednosti.put(TipDogadjaja.IZGUBLJENA_LOPTA, vr);
			row = sheet.getRow(13);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.PRIMLJENI, vr);
			ucitaneVrijednosti.put(TipDogadjaja.PRIMLJENI, vr);
			row = sheet.getRow(14);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.PRIMLJENI_7, vr);
			ucitaneVrijednosti.put(TipDogadjaja.PRIMLJENI_7, vr);
			row = sheet.getRow(15);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.ISKLJUCENJE_2, vr);
			ucitaneVrijednosti.put(TipDogadjaja.ISKLJUCENJE_2, vr);
			row = sheet.getRow(16);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.TRAJNO_ISKLJUCENJE, vr);
			ucitaneVrijednosti.put(TipDogadjaja.TRAJNO_ISKLJUCENJE, vr);
			row = sheet.getRow(17);
			vr = (row.getCell(1).getNumericCellValue());
			DAOProvider.getDAO().postaviVrijednostDogadjaja(TipDogadjaja.IZRAVNO_ISKLJUCENJE, vr);
			ucitaneVrijednosti.put(TipDogadjaja.IZRAVNO_ISKLJUCENJE, vr);
			wb.close();
			req.setAttribute("vrijednosti", ucitaneVrijednosti);
			req.getRequestDispatcher("/WEB-INF/pages/PocetnaKonf.jsp").forward(req, resp);

		} else {
			req.setAttribute("poruka", "Početna konfiguracija već učitana!");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp");
		}
	}

}
