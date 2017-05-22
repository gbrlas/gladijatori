package hr.gladijatori.web.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellFormatType;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Pozicija;

@WebServlet("/servleti/sluzb/pockonfxlsx")
public class PocKonfXLSXServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Double proracun = null, zgoditak7m = null, zgoditak9 = null, zgoditakIzvan9 = null, obranavratara = null,
				bloksuta = null, ukradenalopta = null, obrana7 = null, mvp = null, promasajSuta = null,
				promasaj7 = null, izgubljenalopta = null, primljenGol = null, primljenGol7 = null, iskljucenje2 = null,
				trajnoIsk = null, izravnoIsk = null;
		Integer n = null;
		try {
			proracun = Double.parseDouble((String) req.getParameter("proracun"));
			zgoditak9 = Double.parseDouble((String) req.getParameter("zgoditakDevet"));
			zgoditak7m = Double.parseDouble((String) req.getParameter("zgoditakSedam"));
			zgoditakIzvan9 = Double.parseDouble((String) req.getParameter("zgoditakIzvan9"));
			obranavratara = Double.parseDouble((String) req.getParameter("obranavratara"));
			bloksuta = Double.parseDouble((String) req.getParameter("bloksuta"));
			ukradenalopta = Double.parseDouble((String) req.getParameter("ukradenalopta"));
			obrana7 = Double.parseDouble((String) req.getParameter("obranavratara7"));
			mvp = Double.parseDouble((String) req.getParameter("mvp"));
			promasajSuta = Double.parseDouble((String) req.getParameter("promasajSuta"));
			promasaj7 = Double.parseDouble((String) req.getParameter("promasajSuta7"));
			izgubljenalopta = Double.parseDouble((String) req.getParameter("izgubljenalopta"));
			primljenGol = Double.parseDouble((String) req.getParameter("primljenzgoditak"));
			primljenGol7 = Double.parseDouble((String) req.getParameter("primljenzgoditak7"));
			iskljucenje2 = Double.parseDouble((String) req.getParameter("iskljucenje2"));
			trajnoIsk = Double.parseDouble((String) req.getParameter("trajnoiskljucenje"));
			izravnoIsk = Double.parseDouble((String) req.getParameter("izravnoiskljucenje"));

			List<Igrac> igraci = (List<Igrac>) (Object) DAOProvider.getDAO().izvediUpit("SELECT i FROM Igrac i");

			double sum[] = new double[Pozicija.values().length];
			int broj[] = new int[Pozicija.values().length];

			for (Igrac igrac : igraci) {
				if (igrac.getVrijednost() * 4 > proracun) {
					req.setAttribute("poruka", "Proračun ne smije biti manji od: " + igrac.getVrijednost() * 4);
					req.getRequestDispatcher("/WEB-INF/pages/DefPocKonf.jsp").forward(req, resp);
					return;
				}
				sum[igrac.getPozicija().ordinal()] += igrac.getVrijednost();
				broj[igrac.getPozicija().ordinal()]++;
			}

			for (int i = 0; i < sum.length; i++) {
				if (sum[i] / broj[i] > proracun / 7) {
					req.setAttribute("poruka", "Proračun ne smije biti manji od: " + 7*sum[i] / broj[i]);
					req.getRequestDispatcher("/WEB-INF/pages/DefPocKonf.jsp").forward(req, resp);
					return;
				}
			}

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("1");
			HSSFRow rowhead = sheet.createRow(0);
			rowhead.createCell(0).setCellValue("Vrsta dogaðaja");
			rowhead.createCell(1).setCellValue("Vrijednost");

			HSSFRow row1 = sheet.createRow(1);
			row1.createCell(0).setCellValue("Proraèun:");
			row1.createCell(1).setCellValue(proracun);
			row1.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row2 = sheet.createRow(2);
			row2.createCell(0).setCellValue("Zgoditak unutar 9 m:");
			row2.createCell(1).setCellValue(zgoditak9);
			row2.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row3 = sheet.createRow(3);
			row3.createCell(0).setCellValue("Zgoditak sa 7 m:");
			row3.createCell(1).setCellValue(zgoditak7m);
			row3.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row4 = sheet.createRow(4);
			row4.createCell(0).setCellValue("Zgoditak izvan 9 m:");
			row4.createCell(1).setCellValue(zgoditakIzvan9);
			row4.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row5 = sheet.createRow(5);
			row5.createCell(0).setCellValue("Obrana vratara:");
			row5.createCell(1).setCellValue(obranavratara);
			row5.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row6 = sheet.createRow(6);
			row6.createCell(0).setCellValue("Blok šuta:");
			row6.createCell(1).setCellValue(bloksuta);
			row6.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row7 = sheet.createRow(7);
			row7.createCell(0).setCellValue("Ukradena lopta: ");
			row7.createCell(1).setCellValue(ukradenalopta);
			row7.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row8 = sheet.createRow(8);
			row8.createCell(0).setCellValue("Obrana 7 m:");
			row8.createCell(1).setCellValue(obrana7);
			row8.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row9 = sheet.createRow(9);
			row9.createCell(0).setCellValue("Najbolji igraè utakmice:");
			row9.createCell(1).setCellValue(mvp);
			row9.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row10 = sheet.createRow(10);
			row10.createCell(0).setCellValue("Promašaj šuta: ");
			row10.createCell(1).setCellValue(promasajSuta);
			row10.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row11 = sheet.createRow(11);
			row11.createCell(0).setCellValue("Promašaj sa 7 m:");
			row11.createCell(1).setCellValue(promasaj7);
			row11.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row12 = sheet.createRow(12);
			row12.createCell(0).setCellValue("Izgubljena lopta:");
			row12.createCell(1).setCellValue(izgubljenalopta);
			row12.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row13 = sheet.createRow(13);
			row13.createCell(0).setCellValue("Primljen zgoditak:");
			row13.createCell(1).setCellValue(primljenGol);
			row13.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row14 = sheet.createRow(14);
			row14.createCell(0).setCellValue("Primljen zgoditak sa 7 m:");
			row14.createCell(1).setCellValue(primljenGol7);
			row14.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row15 = sheet.createRow(15);
			row15.createCell(0).setCellValue("Iskljuèenje 2 min:");
			row15.createCell(1).setCellValue(iskljucenje2);
			row15.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row16 = sheet.createRow(16);
			row16.createCell(0).setCellValue("Trajno iskljuèenje:");
			row16.createCell(1).setCellValue(trajnoIsk);
			row16.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			HSSFRow row17 = sheet.createRow(17);
			row17.createCell(0).setCellValue("Izravno iskljuèenje:");
			row17.createCell(1).setCellValue(izravnoIsk);
			row17.getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			/*
			 * resp.setContentType("application/octet-stream");
			 * resp.setHeader("Content-Disposition",
			 * "attachment; filename=glasanje.xls");
			 * wb.write(resp.getOutputStream());
			 */
			ServletContext context = getServletContext();
			String FILE_PATH = context.getRealPath("/WEB-INF/files/pocKonfig.xls");
			try {
				FileOutputStream fos = new FileOutputStream(FILE_PATH);
				wb.write(fos);
				wb.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			req.setAttribute("porukaUspjeh", "Poèetna konfiguracija je uspješno definirana");
			req.getRequestDispatcher("/WEB-INF/pages/DefPocKonf.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			req.setAttribute("poruka", "Podatci nisu uneseni ispravno!");
			req.getRequestDispatcher("/WEB-INF/pages/DefPocKonf.jsp").forward(req, resp);
		}
	}

}
