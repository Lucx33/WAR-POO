package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApiModel {
    public void setGame(int qntPlayers) {
        // Cria o baralho e embaralha
        Baralho baralho = new Baralho();
        baralho.shuf();

        // Cria os objetivos e embaralha
        List<Objetivo> objetivos = Objetivo.criarObjetivos();
        Collections.shuffle(objetivos);

        // Cria o tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro();

        // Cria os jogadoresList
        List<Jogador> jogadoresList = new ArrayList<>();

        // Cria os jogadoresList e distribui os objetivos e cartas (terrtórios iniciais)
        for(int i = 0; i < qntPlayers; i++){
            Jogador jogador = new Jogador("Jogador " + i, "Cor " + i);
            jogadoresList.add(jogador);
            jogador.setObjetivo(objetivos.get(i));
            jogador.setCartas(Baralho.distribuiCarta(qntPlayers));
        }
    }

    public void validaAtaque(){

    }

    public void posicionaExercito(){

    }

    public void validaMovimento(){

    }

}
