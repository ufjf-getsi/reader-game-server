package Mundo;

import java.util.Objects;

public class GameSteps {

    public Boolean atualizarJogadorAtual(Game game) {
        if (game.getTurnsLeft() > 0) {
            String[] rounds = game.getPlayersOrder().split(";");
            Integer[] order = new Integer[rounds.length];
            for (Integer i = 0; i < rounds.length; i++) {
                order[i] = Integer.parseInt(rounds[i]);
            }

            Integer posicaoAtual = game.getCurrentPlayer();
            for (Integer i = 0; i < rounds.length; i++) {
                if (posicaoAtual.equals(order[i])) {
                    if (i == (rounds.length - 1)) {
                        game.setCurrentPlayer(order[0]);
                    } else {
                        game.setCurrentPlayer(order[i + 1]);
                    }
                }
            }

            atualizarNumeroDeRodadas(game);
            return true;
        } else {
            return false;
        }
    }

    private void atualizarNumeroDeRodadas(Game game) {
        Integer numDeRodades = game.getTurnsLeft();
        numDeRodades--;
        game.setTurnsLeft(numDeRodades);
    }
}
