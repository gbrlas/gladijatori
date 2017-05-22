package hr.gladijatori.web.init;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.gladijatori.dao.JPAEMProvider;
import hr.gladijatori.modeli.korisnik.Korisnik;
import hr.gladijatori.modeli.korisnik.TipKorisnika;

@WebFilter("/servleti/*")
public class JPAFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			String dozvole = req.getRequestURI().substring(req.getContextPath().length()).replace("/servleti/", "");

			Korisnik korisnik = (Korisnik) req.getSession().getAttribute("korisnik");
			if (korisnik == null) {
				if (dozvole.startsWith("admin") || dozvole.startsWith("virt") || dozvole.startsWith("nat") || 
					dozvole.startsWith("sluzb") || dozvole.startsWith("tehn")) {
					resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Ne posjedujete dozvole za pristup stranici!");
					return;
				}
			} else if (dozvole.startsWith("admin")){
				if (!korisnik.getTip().equals(TipKorisnika.ADMIN)) {
					resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Ne posjedujete dozvole za pristup stranici!");
					return;
				}
			} else if (dozvole.startsWith("nat")) {
				if (!korisnik.getTip().equals(TipKorisnika.NAT)) {
					resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Ne posjedujete dozvole za pristup stranici!");
					return;
				}
			} else if (dozvole.startsWith("sluzb")) {
				if (!korisnik.getTip().equals(TipKorisnika.SLUZB)) {
					resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Ne posjedujete dozvole za pristup stranici!");
					return;
				}
			} else if (dozvole.startsWith("tehn")) {
				if (!korisnik.getTip().equals(TipKorisnika.TEHN)) {
					resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Ne posjedujete dozvole za pristup stranici!");
					return;
				}
			}
			
			chain.doFilter(request, response);
		} finally {
			JPAEMProvider.close();
		}
	}

	@Override
	public void destroy() {
	}

}