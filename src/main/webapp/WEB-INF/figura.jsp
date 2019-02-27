<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>
<div class="container text-center">
    <h1> Sua rodada ${player.name} </h1>
    <div style="grid">
        
    <img src="imagem?nomeimagem=${nomeimagem}" alt="${nomeimagem}">
    <img src="svg?nomeimagem=${nomefigura}" alt="${nomefigura}">
    </div>
    
</div>

<%@include file="jspf/rodape.jspf" %>