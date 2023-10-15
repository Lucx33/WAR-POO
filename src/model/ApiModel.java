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
            setExercitosIni(jogador);
        }
    }

    public void validaAtaque(){

    }

    void setExercitosIni(Jogador jogador) {
        for(jogador.cartas.size(); !jogador.cartas.isEmpty(); jogador.cartas.remove(0)){
            buscaTerritorio(jogador.cartas.get(0).getTerritorio()).setQtdExercito(1);
            Baralho.addBaralho(jogador.cartas.get(0));
        }
    }

    public void validaMovimento(){

    }

    static Territorio buscaTerritorio(String target) {
        for (Territorio territorio : tabuleiro.territorios) {
            if (territorio.getNome().equals(target)) {
                return territorio;
            }
        }
        return null;
    }

    public void turnoJogador(){
        // Recebe os exercitos
        recebeExercito();

        // Ataque
        validaAtaque();

        // Movimento
        validaMovimento();
    }

}
