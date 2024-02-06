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
        String id = request.getParameter("id");
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
                            <th>Posicao</th>
                            <th>Nome</th>
                            <th>senha</th>
                            <th>Email</th>
                            <th>cpf</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%=listaHTML %>
                    </tbody>
                </table>
            </div>
        </main>
    </body>
</html>
