package Mundo;

import Grafo.Grafo;
import Grafo.GrafoGenerator;
import Grafo.GrafoGeneratorTeia;
import Grafo.VerticeItem;
import Persistence.GameDAO;
import Persistence.PlayerDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.openide.util.Exceptions;

public class GameGenerator {

    public Integer saveInitialData(String name, Integer players, Integer turnsLeft, String words) {
        try {
            String split = ",";
            String finalWords[] = words.split(split);
            OrdemGenerator ordem = new OrdemGenerator(players);
            Game game = new Game(name, ordem.getOrdem().get(0), players, (turnsLeft * players), ordem.getOrdem(players));
            Integer game_id = GameDAO.getInstance().saveGame(game);
            GameDAO.getInstance().saveWords(finalWords, game_id);
            return game_id;
        } catch (ClassNotFoundException | SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public Game savePlayersData(Integer playersNumber, String []playersName, Integer gameIdentifier) throws SQLException, ClassNotFoundException {
        Game game;
        game = GameDAO.getInstance().searchGame(gameIdentifier);
        geraMapa(playersNumber, playersName, game);
        List<Player> jogadores = game.getJogadores();
        PlayerDAO.getInstance().savePlayers(jogadores, gameIdentifier);
        
        /* Verificar com o Lucas sobre exclusão
        GrafoGenerator gerador = new GrafoGeneratorTeia();
        fase.setMapa(gerador.getGrafo(quantidadeDeVertices));
        System.out.println("Teste movimentacao do jogador");
            System.out.println("Vertice jogador 1 antes: " + fase.getJogadores().get(0).getVertice().getIndice());
            System.out.println("Movendo...");
            fase.getJogadores().get(0).mover(0);
            System.out.println("Vertice jogador 1 depois: " + fase.getJogadores().get(0).getVertice().getIndice());*/ 
        
        return game;
    }
    
    public void geraMapa(Integer numJogadores, String [] playersName, Game game) {
        Grafo map = new Grafo();
        List<Player> jogadores = new ArrayList<>();
        int fatorMutltMapa = 4;                             //Fator que multiplicado pelo num de jogadores ira gerar o grafo
        
        for (int i = 1; i <= numJogadores; i++) {
            jogadores.add(new Player(i-1, playersName[i - 1]));
        }
        
        GrafoGenerator gerador = new GrafoGeneratorTeia();
        map = gerador.getGrafo(fatorMutltMapa * jogadores.size());

        List<Integer> verticesDisp = new ArrayList<>();
        List<Integer> posJogadores = new ArrayList<>();                         //Lista de posições dos jogadores já escolhidas que deverá ser verificado se a distancia 
        //do vertice escolhido é compativel com todos eles
        for (int i = 1; i <= (fatorMutltMapa * jogadores.size()); i++) {
            verticesDisp.add(i);
        }

        Integer verticeJogador = map.numeroAleatorio(verticesDisp.get(0), verticesDisp.get(verticesDisp.size() - 1));      //Sorteia uma posição na lista de vertices disponíveis
        jogadores.get(0).setVertice((VerticeItem) map.getVertice(verticeJogador));         //Posicionando o primeiro jogador
        posJogadores.add(verticeJogador);
        verticesDisp.remove(verticeJogador);
        boolean testePossibilidade;
        for (int i = 1; i < jogadores.size(); i++) {
            for (int j = 0; j < map.getNumeroDeVertices(); j++) {
                if (map.getMatrizDistancias()[verticeJogador - 1][j] >= 2) {
                    testePossibilidade = true;
                    for (int k = 0; k < posJogadores.size(); k++) {                 //Compara a distancia com todos os jogadores inseridos e verifica se o vértice é válido
                        if (map.getMatrizDistancias()[j][posJogadores.get(k) - 1] < 2) {
                            testePossibilidade = false;
                            break;
                        }
                    }
                    if (testePossibilidade) {
                        verticeJogador = j + 1;
                        posJogadores.add(verticeJogador);
                        jogadores.get(i).setVertice((VerticeItem) map.getVertice(verticeJogador));
                        removeElemLista(verticesDisp, verticeJogador);
                        break;
                    }
                }
            }
        }

        List<Integer> qtd = new ArrayList<>();
        List<Integer> tipos = new ArrayList<>();
        List<Integer> qtdMaxTipo = new ArrayList<>();
        qtdMaxTipo.add(Math.round((60 * verticesDisp.size()) / 100));           //Estabelece uma porcentagem para os elementos que deverá ter no mapa
        qtdMaxTipo.add(Math.round((40 * verticesDisp.size()) / 100));
        for (int i = 0; i < qtdMaxTipo.size(); i++) {
            qtd.add(0);
            tipos.add(i);
        }
        int verticeRecurso;
        int sortTipo;
        VerticeItem auxVertice;
        while (verticesDisp.size() > 0) {
            verticeRecurso = map.numeroAleatorio(0, (verticesDisp.size() - 1));
            if (qtd.size() > 0) {                           //Se a lista de quantidade ainda teme elementos, logo os tipos de vertices ainda não estão na sua quantidade máxima
                sortTipo = map.numeroAleatorio(0, (qtd.size() - 1));
                auxVertice = (VerticeItem) (map.getVertice(verticesDisp.get(verticeRecurso)));
                switch (tipos.get(sortTipo)) {
                    case 0:
                        auxVertice.addItem("GR");           //Ganha recurso
                        break;
                    case 1:
                        auxVertice.addItem("CR");           //Consome recurso
                        break;
                    default:
                }
                qtd.set(sortTipo, qtd.get(sortTipo) + 1);               ///Incrementa quantidade presente no grafo de determinado tipo de vértice
                if (qtd.get(sortTipo) >= qtdMaxTipo.get(sortTipo)) {
                    qtd.remove(sortTipo);
                    qtdMaxTipo.remove(sortTipo);
                }
            } else {                                                ///Atingiu as porcentagens dos vertices mas ainda não terminou de preencher o mapa
                sortTipo = 0;
                auxVertice = (VerticeItem) (map.getVertice(verticesDisp.get(verticeRecurso)));
                switch (sortTipo) {
                    case 0:
                        auxVertice.addItem("GR");           //Ganha recurso
                        break;
                    case 1:
                        auxVertice.addItem("CR");           //Consome recurso
                        break;
                    default:
                }
            }
            verticesDisp.remove(verticeRecurso);
        }
        game.setJogadores(jogadores);
        game.setMapa(map);
    }

    private void removeElemLista(List<Integer> lista, int elemento) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) == elemento) {
                lista.remove(i);
                break;
            }
        }
    }
    
    public static Game getGameStub(){
        List<Player> jogadores = new ArrayList<Player>(){{
            add(new Player(1001, 1, "André",    1,0,0));
            add(new Player(1015, 5, "Bernardo", 2,4,0));
            add(new Player(1002, 2, "Adriano",  1,1,0));
            add(new Player(1016, 6, "Bruno",    2,0,0));
            add(new Player(1003, 3, "Adalberto",1,0,0));
            add(new Player(1017, 7, "Brian",    2,0,0));
            add(new Player(1004, 4, "Alexandre",1,0,0));
            add(new Player(1018, 8, "Breno",    2,0,0));
        }};
        
        List<Item> items = new ArrayList<Item>(){{
            add(new Item(100, "{\"name\": \"emerald\", \"type\":\"demand\",\"points\":\"20\"}", 0));
            add(new Item(101, "{\"name\": \"saphire\", \"type\":\"demand\",\"points\":\"10\"}", 0));
            add(new Item(102, "{\"name\": \"CA\", \"type\":\"noidea\"}", 0));
            add(new Item(151, "{\"name\": \"emerald\", \"type\":\"good\", \"color\":\"green\"}", 1));
            add(new Item(152, "{\"name\": \"ruby\", \"type\":\"good\", \"color\":\"red\"}", 3));
            add(new Item(153, "{\"name\": \"saphire\", \"type\":\"good\", \"color\":\"blue\"}", 4));
            add(new Item(154, "{\"name\": \"ruby\", \"type\":\"demand\", \"points\":\"30\"}", 6));
        }};
        
        Game game = new Game("abcdef", 0, jogadores.size(), 6*jogadores.size(), "PlayersOrder");
        game.setIdentifier(1);
        game.setNodes("["
                + "{\"node\":0,\"x\":0,\"y\":0},"
                + "{\"node\":1,\"x\":1,\"y\":0},"
                + "{\"node\":2,\"x\":2,\"y\":0},"
                + "{\"node\":3,\"x\":0,\"y\":1},"
                + "{\"node\":4,\"x\":1,\"y\":1},"
                + "{\"node\":5,\"x\":2,\"y\":1},"
                + "{\"node\":6,\"x\":0,\"y\":2},"
                + "{\"node\":7,\"x\":1,\"y\":2},"
                + "{\"node\":8,\"x\":2,\"y\":2},"
                + "{\"node\":9,\"x\":0,\"y\":3},"
                + "{\"node\":10,\"x\":1,\"y\":3},"
                + "{\"node\":11,\"x\":0,\"y\":4},"
                + "{\"node\":12,\"x\":1,\"y\":5,\"items\":[]},"
                + "{\"node\":13,\"x\":3,\"y\":0,\"items\":[]},"
                + "{\"node\":14,\"x\":4,\"y\":0,\"items\":[]},"
                + "{\"node\":15,\"x\":3,\"y\":1,\"items\":[]},"
                + "{\"node\":16,\"x\":4,\"y\":1,\"items\":[]},"
                + "{\"node\":17,\"x\":3,\"y\":2,\"items\":[]},"
                + "{\"node\":18,\"x\":4,\"y\":2,\"items\":[]},"
                + "{\"node\":19,\"x\":3,\"y\":3,\"items\":[]},"
                + "{\"node\":20,\"x\":4,\"y\":3,\"items\":[]},"
                + "{\"node\":21,\"x\":3,\"y\":4,\"items\":[]},"
                + "{\"node\":22,\"x\":4,\"y\":4,\"items\":[]}"
                + "]");
        
        game.setJogadores(jogadores);
        game.setItens(items);
        game.setOpcoes(game.getOpcoes());
        return game;
    } 

}
