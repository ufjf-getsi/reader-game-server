<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="jspf/cabecalho.jspf" %>

<main role="main">

  <div class="jumbotron">
    <div class="container">
      <h1 class="display-3">Talk2Me, módulo de configuração</h1>
      <p>Este módulo permite realizar o cadastro de salas, alunos e palavras para uma partida.</p>
      <p><a class="btn btn-primary btn-lg" href="/reader-game-server/configurar.html" role="button">Crie já uma sala!</a></p>
    </div>
  </div>

  <div class="container">
    <!-- Example row of columns -->
    <div class="row">
      <div class="col-md-6">
        <h2>Configurar salas</h2>
        <p>Aqui é possível realizar o cadastro de salas e suas respectivas informações.</p>
        <p><a class="btn btn-secondary" href="/reader-game-server/configurar.html" role="button">Configurar sala</a></p>
      </div>
      <div class="col-md-6">
        <h2>Salas</h2>
        <p>Deseja saber o estado de uma partida? Veja todas as salas criadas.</p>
        <p><a class="btn btn-secondary" href="/reader-game-server/listar-salas.html" role="button">Lista de todas as salas</a></p>
      </div>
    </div>

    <hr>

  </div> <!-- /container -->

</main>

<%@include file="jspf/rodape.jspf" %>