package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Perfil;
import model.dao.DaoFactoryPerfil;
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
            String senha = request.getParameter("senha");
            String date = request.getParameter("date");

            PerfilDaoJpa dao = new PerfilDaoJpa();
            RequestDispatcher rd;
            Perfil p = null;

            switch (acao) {
                case "inclusao":
                    /*for (int i = 0; i < dao.listar().size(); i++) {
                        if (dao.listar().get(i).getNome().equals(nome) || dao.listar().get(i).getEmail().equals(email) || dao.listar().get(i).getCpf().equals(cpf)) {
                            request.setAttribute("mensagemErro", "credenciais invalidas");
                            rd = request.getRequestDispatcher("formulario.jsp");
                            rd.forward(request, response);
                        } else {
                            p = new Perfil(nome, senha, cpf, email, LocalDate.parse(date));
                            dao.incluir(p);
                            rd = request.getRequestDispatcher("telaUsuario.jsp?nome=" + p.getNome() + "&senha=" + p.getsenha());
                            rd.forward(request, response);
                            break;
                        }
                    }*/
                    p = new Perfil(nome, senha, cpf, email, LocalDate.parse(date));
                    dao.incluir(p);
                    rd = request.getRequestDispatcher("telaUsuario.jsp?nome=" + p.getNome() + "&senha=" + p.getsenha());
                    rd.forward(request, response);
                    break;

                case "pre-edicao":
                    p = dao.pesquisarPorId(Integer.parseInt(id));
                    rd = request.getRequestDispatcher(
                                "formulario.jsp?acao=edicao-admin&id=" + p.getId() +
                                "&nome=" + p.getNome() +
                                "&senha=" + p.getsenha() +
                                "&cpf=" + p.getCpf() +
                                "&email=" + p.getEmail()
                            );
                    rd.forward(request, response);
                    break;

                case "edicao":
                    p = new Perfil(nome, senha, cpf, email, LocalDate.parse(date));
                    p.setId(Integer.parseInt(id));

                    dao.editar(p);

                    rd = request.getRequestDispatcher("telaUsuario.jsp?nome=" + p.getNome() + "&senha=" + p.getsenha());
                    rd.forward(request, response);
                    break;

                case "edicao-admin":
                    p = new Perfil(nome, senha, cpf, email, LocalDate.parse(date));
                    p.setId(Integer.parseInt(id));

                    dao.editar(p);

                    rd = request.getRequestDispatcher("telaUsuario.jsp?nome=admin&senha=admin");
                    rd.forward(request, response);
                    break;

                case "exclusao":
                    dao.excluir(dao.pesquisarPorId(Integer.parseInt(id)));
                    rd = request.getRequestDispatcher(
                                "listagemAdmin.jsp?lista=" + listagemAdmin() + "&nome=admin&senha=admin");
                    rd.forward(request, response);
                    break;

                case "listagem":
                    if (nome.equals("admin") && senha.equals("admin")) {
                        rd = request.getRequestDispatcher(
                                "listagemAdmin.jsp?lista=" + listagemAdmin() + "&nome=admin&senha=admin");
                        rd.forward(request, response);
                    } else {
                        rd = request.getRequestDispatcher(
                                "listagem.jsp?lista=" + listagem() + "&nome=" + nome + "&senha=" + senha);
                        rd.forward(request, response);
                    }
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PerfilSrv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String listagem() {
        PerfilDaoJpa dao = new PerfilDaoJpa();
        List<Perfil> lista = null;
        try {
            lista = dao.listar();
        } catch (Exception ex) {
            Logger.getLogger(PerfilSrv.class.getName()).log(Level.SEVERE, null, ex);
        }
        String listaHTML = "";
        for (int i = 0; i < lista.size(); i++) {
            Perfil perfil = null;
            perfil = lista.get(i);
            listaHTML = listaHTML
                    + "<tr>"
                    + "<td>" + (i + 1)
                    + "<td>" + perfil.getNome() + "</td>"
                    + "</tr>";
        }
        return listaHTML;
    }

    private String listagemAdmin() {
        PerfilDaoJpa dao = new PerfilDaoJpa();
        List<Perfil> lista = null;
        try {
            lista = dao.listar();
        } catch (Exception ex) {
            Logger.getLogger(PerfilSrv.class.getName()).log(Level.SEVERE, null, ex);
        }
        String listaHTML = "";
        for (int i = 0; i < lista.size(); i++) {
            Perfil perfil = null;
            perfil = lista.get(i);
            listaHTML = listaHTML
                    + "<tr>"
                    + "<td>" + (i + 1)
                    + "<td>" + perfil.getNome() + "</td>"
                    + "<td>" + perfil.getsenha() + "</td>"
                    + "<td>" + perfil.getEmail() + "</td>"
                    + "<td>" + perfil.getCpf() + "</td>"
                    + "<td><form action=PerfilSrv?acao=pre-edicao method='POST'>"
                    + "<input type='hidden' name='id' value=" + perfil.getId() 
                    + "><input type='submit' value=editar id='btnEditar'>" + "</form></td>"
                    + "<td><form action=PerfilSrv?acao=exclusao method='POST'>"
                    + "<input type='hidden' name='id' value=" + perfil.getId() 
                    + "><input type='submit' value=excluir id='btnExcluir'>" + "</form></td>"
                    + "</tr>";
        }
        return listaHTML;
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
