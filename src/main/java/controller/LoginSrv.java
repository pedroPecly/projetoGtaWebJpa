package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Games;
import model.Perfil;
import model.dao.DaoFactoryGames;
import model.dao.GamesDaoJpa;
import model.dao.PerfilDaoJpa;

public class LoginSrv extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String acao = request.getParameter("acao");
            String nome = request.getParameter("nome");
            String senha = request.getParameter("senha");


            RequestDispatcher rd;
            Perfil p = null;

            switch (acao) {
                case "login":
                    if (verificarPerfil(nome, senha) != null) {
                        p = verificarPerfil(nome, senha);

                        rd = request.getRequestDispatcher("telaUsuario.jsp?nome=" + p.getNome() + "&senha=" + p.getsenha());
                        
                        rd.forward(request, response);
                    } else {
                        request.setAttribute("mensagemErro", "Nome de usuário ou senha inválidos!");
                        rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                    }
                    break;
                case "edicao":
                    p = verificarPerfil(nome, senha);
                    rd = request.getRequestDispatcher(
                                "formulario.jsp?acao=edicao&id=" + p.getId() +
                                "&nome=" + p.getNome() +
                                "&senha=" + p.getsenha() +
                                "&cpf=" + p.getCpf() +
                                "&email=" + p.getEmail()
                            );
                    rd.forward(request, response);
                    break;

                
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginSrv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Perfil verificarPerfil(String nome, String senha) {
        PerfilDaoJpa dao = new PerfilDaoJpa();
        List<Perfil> lista = null;

        try {
            lista = dao.listar();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getNome().equals(nome) && lista.get(i).getsenha().equals(senha)) {
                    return lista.get(i);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PerfilSrv.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
