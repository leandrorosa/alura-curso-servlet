package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/executa")
public class Controller extends HttpServlet {

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		final String tarefa = req.getParameter("tarefa");
		if (tarefa == null) {
			throw new IllegalArgumentException("Não foi especificada nenhuma tarefa");
		}
		final String nomeDaClasse = "br.com.alura.gerenciador.web." + tarefa;
		try {
			final Class<?> type = Class.forName(nomeDaClasse);
			final Tarefa instancia = (Tarefa) type.newInstance();
			final String pagina = instancia.executa(req, resp);
			req.getRequestDispatcher(pagina).forward(req, resp);
		} catch (final Exception e) {
			throw new ServletException(e);
		}
	}

}
