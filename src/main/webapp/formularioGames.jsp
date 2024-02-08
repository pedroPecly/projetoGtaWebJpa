<%-- Document : formulario Created on : 14 de dez. de 2023, 13:08:49 Author : Pedro --%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link rel="stylesheet" href="styles/style_formulario.css">
</head>

<%
    String acao = request.getParameter("acao");
    String nome     = request.getParameter("nome");
    String senha = request.getParameter("senha");
    String nomeJogo = request.getParameter("nomeJogo");
    bolean zerado = request.getParameter("zerado");
    
    if (acao.equals("adicionarJogo")) {
        nomeJogo = "";
        zerado = false;
    }
%>

<body>
    <% String mensagemErro = (String) request.getAttribute("mensagemErro"); %>
    <% if (mensagemErro != null) { %>
        <script>
            alert("<%= mensagemErro %>");
        </script>
    <% } %>
    <header>
        <nav class="cabecalho">
            <div class="menu">
                <ul>
                    <% if (acao != null && acao.equals("edicao")) { %>
                        <li class="links"><a href="telaUsuario.jsp?nome=<%= nome%>&senha=<%= senha%>">Voltar</a></li>
                    <% } else { %>
                        <li class="links"><a href="index.html">Voltar</a></li>
                    <% } %>
                </ul>
            </div>
            <img id="logo" src="imagens/Rockstar_London-Logo.wine.png" alt="Logo Rockstar London">
        </nav>
    </header>
    <main>
        <div id="formulario">
            <form action="PerfilSrv" method="POST" autocomplete="off">
                <p>
                    <input type="hidden" name="id" value="<%= id %>">
                </p>
                <p>
                    <input type="hidden" name="idPerfil" value="<%= idPerfil %>">
                </p>
                <p>
                    <input type="hidden" name="acao" value="<%= acao %>">
                </p>
                <p>
                    <label for="nome">Nome do Jogo:</label>
                    <input type="text" id="nome" name="nome" placeholder="Informe o nome do jogo" value="<%= nome %>" required>
                </p>
                <p>
                    <label for="jogoZerado">Jogo Zerado:</label>
                    <input type="checkbox" id="jogoZerado" name="jogoZerado" <%= (jogoZerado) ? "checked" : "" %>>
                </p>
                <div class="botoes">
                    <input type="submit" value="Enviar" id="btnEnviar">
                    <input type="reset" value="Limpar" id="btnVoltar">
                </div>
            </form>
        </div>
        
    </main>
</body>

</html>