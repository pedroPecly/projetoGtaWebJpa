<%-- Document : telaUsuario Created on : 18 de dez. de 2023, 20:08:10 Author : Pedro --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="styles/style_telaUsuario.css">
</head>

<%
    String nome = request.getParameter("nome");
    String senha = request.getParameter("senha");
%>

<body>
    <header>
        <nav class="cabecalho">
            <div class="menu">
                <ul>
                    <li><p>Bem vindo <%=nome %></p></li>
                    <li><a href="index.html">Sair</a></li>
                </ul>
            </div>
            <img id="logo" src="imagens/Rockstar_London-Logo.wine.png" alt="Logo Rockstar London">
        </nav>
    </header>
    <main class="conteudo">
        <img class="imggta6" src="imagens/684636.webp" alt="Imagem GTA 6">
        <div class="lateral">
            <h4>Grand Theft Auto VI</h4>
            <h2>Acesse seu perfil:</h2>
            <form action="LoginSrv" method="POST">
                <input type="hidden" name="acao" value="edicao">
                <input type="hidden" name="nome" value="<%=nome%>">
                <input type="hidden" name="senha" value="<%=senha%>">
                <button type="submit" class="btn">Editar perfil</button>
            </form>
            <form action="PerfilSrv" method="POST">
                <input type="hidden" name="acao" value="listagem">
                <input type="hidden" name="nome" value="<%=nome%>">
                <input type="hidden" name="senha" value="<%=senha%>">
                <button type="submit" class="btn">Lista de espera</button>
            </form>
        </div>
    </main>
    <footer>
        <div class="rodape">
            <ul>
                <li><a href="#">Termos de Uso</a></li>
                <li><a href="#">Política de Privacidade</a></li>
                <li><a href="#">Contato</a></li>
            </ul>
            <p>&copy; 2024 Trabalho de POO. Todos os direitos reservados.</p>
        </div>
    </footer>
</body>

</html>