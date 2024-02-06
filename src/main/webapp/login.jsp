<%-- Document : login Created on : 16 de dez. de 2023, 16:27:51 Author : Pedro --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="styles/style_login.css">
</head>

<%
    String acao = request.getParameter("acao");
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
                    <li class="links"><a href="index.html">Home</a></li>
                </ul>
            </div>
            <img id="logo" src="imagens/Rockstar_London-Logo.wine.png" alt="Logo Rockstar London">
        </nav>
    </header>
    <main>
        <div id="formulario">
            <form action="LoginSrv" method="POST" autocomplete="off">
                <p>
                    <input type="hidden" name="acao" value="<%=acao %>">
                </p>
                <p>
                    <label for="nome">login:</label>
                    <input type="text" id="nome" name="nome" placeholder="Informe seu nome" required>
                </p>
                <p>
                    <label for="senha">senha:</label>
                    <input type="password" id="senha" name="senha" placeholder="Informe a seu senha" required>
                </p>
                <p>
                    <a href="index.html" id="alterar-senha">alterar senha</a>
                </p>
                <div class="botoes">
                    <input type="submit" value="Login" id="btnLogin">
                    <input type="reset" value="limpar" id="btnVoltar">
                </div>
            </form>
        </div>
    </main>
</body>

</html>