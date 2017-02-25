package br.com.alura.gerenciador.web;
import java.io.IOException;
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
		final EmpresaDAO empresaDAO = new EmpresaDAO();
		final String filtro = req.getParameter("filtro");		
        final Collection<Empresa> empresas = empresaDAO.buscaPorSimilaridade(filtro);
        req.setAttribute("empresas", empresas);	
        req.getRequestDispatcher("/WEB-INF/paginas/buscaEmpresa.jsp").forward(req, resp);
	}
	
	
    @Override
    public void destroy() {     
        super.destroy();
        System.out.println("Destruindo a servlet "+this);
    }
	
}
