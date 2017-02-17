package br.com.alura.gerenciador.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

@WebServlet(urlPatterns = "/busca")
public class BuscaEmpresa extends HttpServlet{   

    public BuscaEmpresa() {
        System.out.println("Instanciando um servlet do tipo BuscaEmpresa "+ this);
    }
    
    @Override
    public void init() throws ServletException {        
        super.init();
        System.out.println("Inicializando a servlet "+this);
    }

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		final PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<body>");
		writer.println("Resultado da busca:<br/>");
		

		final EmpresaDAO empresaDAO = new EmpresaDAO();
		final String filtro = req.getParameter("filtro");		
        final Collection<Empresa> empresas = empresaDAO.buscaPorSimilaridade(filtro);
		writer.println("<ul>");
		for (final Empresa empresa : empresas) {
		    writer.println("<li>" + empresa.getId() + ": " + empresa.getNome() + "</li>");
		}
		writer.println("</ul>");
		
		writer.println("</body>");
		writer.println("</html>");
	}
	
	
    @Override
    public void destroy() {     
        super.destroy();
        System.out.println("Destruindo a servlet "+this);
    }
	
}
