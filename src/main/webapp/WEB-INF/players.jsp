<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>

<hr>

<div class="container-fluid">

    <div class="container text-left">
        <form method="post">
            <div class="form-group">
                <c:forEach var = "i" begin = "1" end = "${numAlunos}">
                    <label> Nome do jogador ${i} </label>
                    <input type="text" class="form-control" name="players" placeholder="Entre com o nome do jogador ${i}" required>
                </c:forEach>
            </div>
            <input type ="hidden" name="playersNumber" value="${numAlunos}">
            <input type ="hidden" name="idGame" value="${id}"> 
            <button type="submit" class="btn btn-primary"> Enviar </button>
        </form>
    </div>

</div>

<hr>

<%@include file="jspf/rodape.jspf" %>