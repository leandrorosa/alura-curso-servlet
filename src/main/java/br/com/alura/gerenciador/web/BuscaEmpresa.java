package br.com.alura.gerenciador.web;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

public class BuscaEmpresa implements Tarefa {   

    public BuscaEmpresa() {
        System.out.println("Instanciando um servlet do tipo BuscaEmpresa "+ this);
    }

    @Override
    public String executa(final HttpServletRequest request, final HttpServletResponse reponse) {
        final EmpresaDAO empresaDAO = new EmpresaDAO();
        final String filtro = request.getParameter("filtro");       
        final Collection<Empresa> empresas = empresaDAO.buscaPorSimilaridade(filtro);
        request.setAttribute("empresas", empresas); 
        return "/WEB-INF/paginas/buscaEmpresa.jsp";
    }
	
}
