<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>
<div class="container text-center">

    <form method="post">
        <div class="form-group">
            <label> Quantidade de v�rtices </label>
            <input type="text" class="form-control" name="qtddVertices" placeholder="Entre com a quantidade de v�rtices">
        </div>
        <button type="submit" class="btn btn-primary"> Enviar </button>
    </form>

</div>

<%@include file="jspf/rodape.jspf" %>