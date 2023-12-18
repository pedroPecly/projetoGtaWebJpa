<%-- Document : formulario Created on : 14 de dez. de 2023, 13:08:49 Author : Pedro --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link rel="stylesheet" href="styles/style_formulario.css">
</head>

<%
    String acao     = request.getParameter("acao");
    String id       = request.getParameter("id");
    String nome     = request.getParameter("nome");
    String senha = request.getParameter("senha");
    String cpf = request.getParameter("cpf");
    String email = request.getParameter("email");

    if (id == null) {
        nome = "";
        senha = "";
        cpf = "";
        email = "";
    }
%>

<body>
    <header>
        <nav class="cabecalho">
            <div class="menu">
                <ul>
                    <li class="links"><a href="index.html">Home</a></li>
                    <li class="links"><a href="sobre.html">Sobre</a></li>
                </ul>
            </div>
            <img id="logo" src="imagens/Rockstar_London-Logo.wine.png" alt="Logo Rockstar London">
        </nav>
    </header>
    <main>
        <div id="formulario">
            <form action="PerfilSrv" method="POST" autocomplete="off">
                <p>
                    <input type="hidden" name="acao" value="<%=acao %>">
                </p>
                <p>
                    <input type="hidden" name="id" value="<%=id %>">
                </p>
                <p>
                    <label for="nome">Nome:</label>
                    <input type="text" id="nome" name="nome" placeholder="Informe seu nome" value="<%=nome %>" required>
                </p>
                <p>
                    <label for="senha">senha:</label>
                    <input type="password" id="senha" name="senha" placeholder="Informe a seu senha" value="<%=senha %>" required>
                </p>
                <p>
                    <label for="cpf">CPF:</label>
                    <input type="text" id="cpf" name="cpf" placeholder="Informe seu CPF" value="<%=cpf %>" required>
                </p>
                <p>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Informe seu email" value="<%=email %>" required>
                </p>
                <p>
                    <label for="date">Data Nasc:</label>
                    <input type="date" name="date" id="date" required>
                </p>
                <div class="botoes">
                    <input type="submit" value="Enviar" id="btnEnviar">
                    <input type="reset" value="limpar" id="btnVoltar">
                </div>
            </form>
        </div>
    </main>
</body>

</html>