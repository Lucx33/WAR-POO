package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApiModel {

    private static ApiModel ini=null;

    Baralho baralho;
    Tabuleiro tabuleiro;

    List<Objetivo> objetivos;
    List<Jogador> jogadoresList;

    Dado dado;


    private ApiModel(){
        this.baralho = new Baralho();
        this.tabuleiro = new Tabuleiro();
        this.objetivos = new ArrayList<>();
        this.jogadoresList = new ArrayList<>();
        this.dado = new Dado();
    }

    public static ApiModel getInstance(){
        if(ini==null){
            ini = new ApiModel();
        }
        return ini;
    }


    public void setGame(List<String>players, List<String>cores){
        this.baralho.criaBaralho();
        this.objetivos = Objetivo.criarObjetivos();
        this.tabuleiro.criaTabuleiro();

        int resto = baralho.size() % players.size();
        for (int i = 0; i < players.size(); i++) {
            Jogador jogador = new Jogador(players.get(i), cores.get(i));
            jogador.setObjetivo(objetivos.get(i));
            jogador.setCartas(baralho.distribuiCarta(players.size()));
            if(resto > 0){
                jogador.addCarta(baralho.comprarCarta());
                resto--;
            }
            jogador.setExercitosIni(baralho);
            this.jogadoresList.add(jogador);
        }
    }

    public void validaAtaque(String nome1, String nome2){
        Territorio atacante = Tabuleiro.buscaTerritorio(nome1);
        Territorio defensor = Tabuleiro.buscaTerritorio(nome2);
        if(atacante.getIdJogadorDono() == defensor.getIdJogadorDono()) {
            System.out.println("Territorios do mesmo jogador");
        }
        else if(this.tabuleiro.fazFronteira(atacante.nome, defensor.nome)){
            Territorio.ataque(atacante, defensor);
        }
        else{
            System.out.println("Territorios não fazem fronteira");
        }
    }


    public void validaMovimento(){

    }



}