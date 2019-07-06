<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>

<hr>

<div class="container-fluid">

    <div class="container text-left">
        <form method="post">
            <div class="form-group">
                <label> Nome da partida </label>
                <input type="text" class="form-control" name="matchName" placeholder="Entre com o nome da partida" required>
                <label> Entre com a quantidade de turnos </label>
                <input type="number" class="form-control" name="turns" placeholder="Entre com a quantidade de turnos" required>
                <label> Quantidade de jogadores </label>
                <input type="number" class="form-control" name="players" placeholder="Entre com a quantidade de jogadores" required>
                <label> Palavras (Escreva as palavras separadas por v�rgula) </label>
                <textarea class="form-control" id="palavras" name="words" rows="3" required>Entre com as palavras separadas por v�rgula</textarea>
            </div>
            <button type="submit" class="btn btn-primary"> Enviar </button>
        </form>
    </div>

</div>

<hr>

<%@include file="jspf/rodape.jspf" %>