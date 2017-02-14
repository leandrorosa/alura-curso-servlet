package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Cookies;

@WebFilter(urlPatterns = "/*")
public class FiltroDeAuditoria implements Filter {

	@Override
	public void destroy() {


	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final String requestURI = req.getRequestURI();
		
		final Cookies cookies = new Cookies(req.getCookies());		
		final Cookie usuario = cookies.getUsuarioLogado();
		
		
		if(usuario == null) {
			System.out.println("Usuário <deslogado> acessando a URI:"+requestURI);
		} else {
			usuario.setMaxAge(60*10);
			final HttpServletResponse resp = (HttpServletResponse) response;
			resp.addCookie(usuario);
			System.out.println("Usuário "+usuario.getValue()+" acessando a URI:"+requestURI);
		}
		
		chain.doFilter(request, response);
	}
	
	

	@Override
	public void init(final FilterConfig arg0) throws ServletException {


	}

}
