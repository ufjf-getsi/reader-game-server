<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>
<div class="container text-center">
    <h1> Sua rodada ${player.name} </h1>
    <div style="grid">
        <img src="imagem?nomeimagem=${nomeimagem}" alt="${nomeimagem}">
        <img src="svg?nomeimagem=${nomefigura}" alt="${nomefigura}">
    </div>
    <form method="post">
        <input type="hidden" name="playerIdentifierInGame" value="${player.identifier_in_game}">
        <input type ="hidden" name="playerIdentifier" value="${player.identifier}">
        <input type ="hidden" name="idGame" value="${gameId}"> 
        <button type="submit" class="btn btn-primary"> Avançar </button>
    </form>

</div>

<%@include file="jspf/rodape.jspf" %>