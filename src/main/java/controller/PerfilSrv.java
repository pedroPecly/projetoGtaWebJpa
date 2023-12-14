package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Perfil;
import model.dao.PerfilDaoJpa;

public class PerfilSrv extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String acao = request.getParameter("acao");
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            String date = request.getParameter("date");
            PerfilDaoJpa dao = new PerfilDaoJpa();
            RequestDispatcher rd;
            Perfil p = null;

            switch (acao) {
                case "inclusao":
                    p = new Perfil(nome, telefone, cpf, email, LocalDate.parse(date));
                    dao.incluir(p);
                    response.sendRedirect("index.html");
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PerfilSrv.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    }

}
