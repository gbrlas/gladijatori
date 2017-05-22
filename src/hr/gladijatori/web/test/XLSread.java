package hr.gladijatori.web.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import hr.gladijatori.dao.DAOProvider;
import hr.gladijatori.modeli.korisnik.Natjecatelj;
import hr.gladijatori.modeli.korisnik.TipKorisnika;
import hr.gladijatori.modeli.liga.Igrac;
import hr.gladijatori.modeli.liga.Klub;
import hr.gladijatori.modeli.liga.Pozicija;
import hr.gladijatori.modeli.virtliga.VirtEkipa;

public class XLSread {
	private List<Natjecatelj> natjecatelji;
	private List<Igrac> igraci;
	private List<VirtEkipa> virtekipe;
	private Map<String, Klub> kluboviMapa;
	
	
	public XLSread() throws IOException {
		this("Virtualna-liga-DI-1.xls");
	}
	
	public List<Natjecatelj> getNatjecatelji() {
		return natjecatelji;
	}

	public void setNatjecatelji(List<Natjecatelj> natjecatelji) {
		this.natjecatelji = natjecatelji;
	}

	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
	}

	public List<VirtEkipa> getVirtekipe() {
		return virtekipe;
	}

	public void setVirtekipe(List<VirtEkipa> virtekipe) {
		this.virtekipe = virtekipe;
	}

	public Map<String, Klub> getKluboviMapa() {
		return kluboviMapa;
	}

	public void setKluboviMapa(Map<String, Klub> kluboviMapa) {
		this.kluboviMapa = kluboviMapa;
	}

	public XLSread(String xlsFilePath) throws IOException {
		natjecatelji = new ArrayList<>();
		igraci = new ArrayList<>();
		virtekipe = new ArrayList<>();
		kluboviMapa = new HashMap<>();
		FileInputStream file = new FileInputStream(new File(xlsFilePath));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		ucitajKluboveIIgrace(workbook);
		ucitajNatjecatelje(workbook);
		workbook.close();
	}
	
	private void ucitajNatjecatelje(HSSFWorkbook workbook) throws IOException {
		HSSFSheet sheet = workbook.getSheetAt(1);
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		while (rowIterator.hasNext() && !isRowEmpty(row)) {
			row = rowIterator.next();
			String ime = row.getCell(0).getStringCellValue();
			String prezime = row.getCell(1).getStringCellValue();
			String userName = row.getCell(2).getStringCellValue();
			String nazivEkipe = row.getCell(3).getStringCellValue();
			String mail = row.getCell(4).getStringCellValue();
			String drzava = row.getCell(5).getStringCellValue();
			Klub podrzani = kluboviMapa.get(row.getCell(6).getStringCellValue());
			if (podrzani == null || podrzani.getIme() == null || podrzani.getIme().isEmpty()) continue;
			VirtEkipa virtEkipa = new VirtEkipa();
			virtEkipa.setNaziv(nazivEkipe);
			Natjecatelj natjecatelj = new Natjecatelj();
			natjecatelj.setDrzava(drzava);
			natjecatelj.setE_mail(mail);
			natjecatelj.setEkipa(virtEkipa);
			natjecatelj.setIme(ime);
			natjecatelj.setPrezime(prezime);
			natjecatelj.setTip(TipKorisnika.NAT);
			natjecatelj.setPodrzani(podrzani);
			natjecatelj.setPassword(userName);
			natjecatelj.setUsername(userName);
			virtEkipa.setNatjecatelj(natjecatelj);
			natjecatelji.add(natjecatelj);
			virtekipe.add(virtEkipa);
		}
		
	}

	public void ucitajKluboveIIgrace(HSSFWorkbook workbook) throws IOException {
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		while (rowIterator.hasNext() && !isRowEmpty(row)) {
			row = rowIterator.next();
			String imeKluba = row.getCell(0).getStringCellValue();
			Klub klub = kluboviMapa.get(imeKluba);
			if (klub==null) {
				klub = new Klub();
				klub.setIme(imeKluba);
				klub.setIgraci(new ArrayList<>());
								kluboviMapa.put(imeKluba, klub);
			}
			int brojDresa = new Double(row.getCell(1).getNumericCellValue()).intValue();
			Pozicija pozicija = getPozicija(row.getCell(2).getStringCellValue().trim());
			String ime = row.getCell(3).getStringCellValue();
			String prezime = row.getCell(4).getStringCellValue();
			int godine = new Double(row.getCell(5).getNumericCellValue()).intValue();
			long vrijednost = new Double(row.getCell(6).getNumericCellValue()).longValue();
			Igrac igrac = new Igrac();
			igrac.setBrojDresa(brojDresa);
			igrac.setPozicija(pozicija);
			igrac.setIme(ime);
			igrac.setPrezime(prezime);
			igrac.setGodine(godine);
			igrac.setKlub(klub);
			igrac.setVrijednost(vrijednost);
			igrac.setDogadjaji(new ArrayList<>());
			igraci.add(igrac);
			klub.getIgraci().add(igrac);
		}
	}
	
	public static boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell.getStringCellValue().length()>1) {
	        	return false;
	        }
	    }
	    return true;
	}
	
	public static Pozicija getPozicija(String pozicija) {
		switch (pozicija) {
		case "vratar":
			return Pozicija.GOLMAN;
		case "lijevi vanjski":
			return Pozicija.L_VANJSKI;
		case "desni vanjski":
			return Pozicija.D_VANJSKI;
		case "srednji vanjski":
			return Pozicija.S_VANJSKI;
		case "kružni napadač":
			return Pozicija.PIVOT;
		case "lijevo krilo":
			return Pozicija.L_KRILO;
		case "desno krilo":
			return Pozicija.D_KRILO;
		default:
			System.out.println(pozicija);
			throw new IllegalArgumentException();
		}
	}
	
}
