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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String requestURI = req.getRequestURI();
		
		Cookies cookies = new Cookies(req.getCookies());		
		final Cookie usuario = cookies.getUsuarioLogado();
		
		
		if(usuario == null) {
			System.out.println("Usuário <deslogado> acessando a URI:"+requestURI);
		} else {
			usuario.setMaxAge(600000);
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.addCookie(usuario);
			System.out.println("Usuário "+usuario.getValue()+" acessando a URI:"+requestURI);
		}
		
		chain.doFilter(request, response);
	}
	
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {


	}

}
