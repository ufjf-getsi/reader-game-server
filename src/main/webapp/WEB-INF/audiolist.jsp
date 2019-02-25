<%@page import="java.io.File"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>
<div class="container text-center">
    <dl>
        <c:forEach var="audio" items="${audios}"  >
            <dt><a href="audio?nomeaudio=${audio.name}">${audio.name}</a></dt>
            <dd><audio controls src="audio?nomeaudio=${audio.name}"/></dd>
        </c:forEach>
    </dl>
</div>

<%@include file="jspf/rodape.jspf" %>