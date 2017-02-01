package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Usuario;
import br.com.alura.gerenciador.dao.UsuarioDAO;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String email = req.getParameter("email");
        final String senha = req.getParameter("senha");
        final Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
        final PrintWriter writer = resp.getWriter();
        if(usuario == null) {
            writer.println("<html><body>Usuário ou senha invalida</body></html");
        } else {
            writer.println("<html><body>Usuário "+email+" logado</body></html");
        }
     
    }

}
