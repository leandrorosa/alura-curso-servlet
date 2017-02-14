package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Cookies;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookies cookies = new Cookies(req.getCookies());
		Cookie usuarioLogado = cookies.getUsuarioLogado();
		if(usuarioLogado != null) {
			usuarioLogado.setMaxAge(0);
			resp.addCookie(usuarioLogado);
		}
		PrintWriter writer = resp.getWriter();
		writer.println("<html><body>Logout efetuado</body></html>");		
	}

}
