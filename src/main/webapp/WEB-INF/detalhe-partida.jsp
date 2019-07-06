<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>

<main role="main">

    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">Partida: ${titulo}</h1>
            <p>A partida ${titulo} possui ainda ${rodadas} rodadas.</p>
            <p>O próximo jogador é ${jogadoratual.name}.</p>
            <p>Código da partida: ${codigopartida}</p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <table class="table table-dark">
                    <thead>
                        <tr>
                            <th>Jogador</th>
                            <th>Pontos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="player" items="${jogadores}">
                            <tr>
                                <td>${player.name}</td>
                                <td>0</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-md-6">
                <table class="table table-dark">
                    <thead>
                        <tr>
                            <th>Palavra</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="palavra" items="${palavras}">
                            <tr>
                                <td>${palavra}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <hr>

    </div> <!-- /container -->

</main>
<%@include file="jspf/rodape.jspf" %>