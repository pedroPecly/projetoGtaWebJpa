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
import model.dao.GamesDaoJpa;
import model.dao.PerfilDaoJpa;

public class GamesSrv extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
                try {
                    String acao = request.getParameter("acao");
                    String nome = request.getParameter("nome");
                    String senha = request.getParameter("senha");
                    String idGame = request.getParameter("id");

                    int id = verificarPerfil(nome, senha).getId();
                    Games g = null;
                    GamesDaoJpa dao = new GamesDaoJpa();
                    RequestDispatcher rd;

                    switch (acao) {
                        case "adicionarJogo":
                            String nomeJogo = request.getParameter("nomeJogo");
                            String zeradoParam = request.getParameter("zerado");
                            boolean zerado = "on".equals(zeradoParam);
                            if (zeradoParam == null) {
                                zerado = false;
                            }
                            g = new Games(nomeJogo, zerado, id);
                            dao.incluir(g);
                            rd = request.getRequestDispatcher("listagem.jsp?lista="+ listagemJogos(id) + "&acao=listagemJogos"+ "&nome=" + nome + "&senha=" + senha);
                            rd.forward(request, response);
                            break;
                            
                        case "listagemJogos":
                            rd = request.getRequestDispatcher("listagem.jsp?lista="+ listagemJogos(id) + "acao=" + acao + "&nome=" + nome + "&senha=" + senha);
                            rd.forward(request, response);
                            break;

                        case "exclusao":
                            
                            dao.excluir(dao.pesquisarPorId(Integer.parseInt(idGame)));
                            rd = request.getRequestDispatcher("listagem.jsp?lista="+ listagemJogos(id) + "&acao=listagemJogos&nome=" + nome + "&senha=" + senha);
                            rd.forward(request, response);
                            break;
                        
                    }
                } catch (Exception ex) {
                    Logger.getLogger(GamesSrv.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    private String listagemJogos(int id) {
        GamesDaoJpa dao = new GamesDaoJpa();
        List<Games> lista = null;
        try {
            lista = dao.listar();
        } catch (Exception ex) {
            Logger.getLogger(GamesSrv.class.getName()).log(Level.SEVERE, null, ex);
        }

        String listaHTML = "";

        for (int i = 0; i < lista.size(); i++) {
            Games games = null;
            if(id == lista.get(i).getIdPerfil()){
                games = lista.get(i);
                listaHTML = listaHTML
                        + "<tr>"
                        + "<td>" + games.getNome() + "</td>"
                        + "<td>" + games.getZerado() + "</td>"
                        + "<td><form action=GamesSrv?acao=pre-edicao method='POST'>"
                        + "<input type='hidden' name='id' value=" + games.getId() 
                        + "><input type='submit' value=editar id='btnEditar'>" + "</form></td>"
                        + "<td><form action=GamesSrv?acao=exclusao method='POST'>"
                        + "<input type='hidden' name='id' value=" + games.getId() 
                        + "><input type='submit' value=excluir id='btnExcluir'>" + "</form></td>"
                        + "</tr>";
            }
        }
            
        return listaHTML;
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
