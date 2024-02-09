<%-- 
    Document   : listagem
    Created on : 22 de dez. de 2023, 14:46:34
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/style_listagem.css">
    </head>

    <%
        String listaHTML = request.getParameter("lista");
        //String id = request.getParameter("id");
        String acao = request.getParameter("acao");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
    %>
    <body>
        <header>
            <nav class="cabecalho">
                <div class="menu">
                    <ul>
                        <li class="links"><a href="telaUsuario.jsp?nome=<%= nome%>&senha=<%= senha%>">Voltar</a></li>
                    </ul>
                </div>
                <img id="logo" src="imagens/Rockstar_London-Logo.wine.png" alt="Logo Rockstar London">
            </nav>
        </header>

        <main>
            <div id="lista">
                <table>
                    <thead>
                        <tr>
                            <% if (acao != null && !acao.equals("listagemJogos")) { %>
                                <th>posicao</th>
                            <% } %>
                            <th>Nome</th>
                            <% if (acao != null && acao.equals("listagemJogos")) { %>
                                <th>Zerado</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%=listaHTML %>
                    </tbody>

                </table>
                <% if (acao != null && acao.equals("listagemJogos")) { %>
                    <form action="formularioGames.jsp" method="POST">
                        <p>
                            <input type="hidden" name="acao" value="adicionarJogo">
                        </p>
                        
                        <p>
                            <input type="hidden" name="nome" value="<%=nome%>">
                        </p>
                        <p>
                            <input type="hidden" name="senha" value="<%=senha%>">
                        </p>
                        <p>
                            <input type="submit" value="Adicionar" id="btnEditar">
                        </p>
                    </form>
                <% } %>
            </div>
        </main>
    </body>
</html>
