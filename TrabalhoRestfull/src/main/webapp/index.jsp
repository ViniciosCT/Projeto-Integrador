<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ page isELIgnored="false"%>--%>
<html>

    <head>
        <title>Busca de Artigos</title>
    </head>

    <body>

        <h1>Sistema de busca para artigos</h1>

        <div>

            <h2>Carregar Artigo:</h2>
            <form action="ServletLucine" method="post">
                <input type="hidden" name="acao" value="salvar">
                <textarea name="artigo">Entre com o texto do artigo aqui...</textarea>
                <input type="submit">
            </form>


        </div>

        <br>
        <br>

        <div>

            <h2>Pesquisa por artigos</h2>

            <form action="ServletLucine"  method="post">
                <input type="hidden" name="acao" value="pesquisar">
                Pesquisa: <input type="text" name="pesquisa">
                <input type="submit">
            </form>

            <ul>
                    <c:forEach var="resposta" items="${saida}">

                        <li><a href=${resposta}>${resposta}</a></li>

                    </c:forEach>
            </ul>

        </div>
    </body>
</html>