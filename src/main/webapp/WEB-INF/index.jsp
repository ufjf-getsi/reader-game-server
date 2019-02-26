<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>

<div class="container-fluid">

    <div class="container text-left">
        <form method="post">
            <div class="form-group">
                <label> Nome da partida </label>
                <input type="text" class="form-control" name="matchName" placeholder="Entre com o nome da partida" value="Teste">
                <label> Entre com a quantidade de turnos </label>
                <input type="number" class="form-control" name="turns" placeholder="Entre com a quantidade de turnos" value="3">
                <label> Quantidade de jogadores </label>
                <input type="number" class="form-control" name="players" placeholder="Entre com a quantidade de jogadores" value="10">
            </div>
            <button type="submit" class="btn btn-primary"> Enviar </button>
        </form>
    </div>

</div>

<%@include file="jspf/rodape.jspf" %>