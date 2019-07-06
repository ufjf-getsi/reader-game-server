<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>

<main role="main">

    <div class="jumbotron">
        <div class="container">
            <h3 class="display-3">Todas as partidas ativas:</h3>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table class="table table-dark">
                    <thead>
                        <tr>
                            <th>Partida</th>
                            <th>Informações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="game" items="${games}">
                            <tr>
                                <td>${game.tittle}</td>
                                <td><a href="/reader-game-server/ver-detalhes.html?codigo=${game.identifier}">Detalhes</a></td>
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