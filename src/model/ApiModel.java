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


    public void setGame(List<String> players, List<String> cores) {
        // Cria o baralho e embaralha
        this.baralho.criaBaralho();
        this.baralho.shuf();

        // Cria o tabuleiro
        this.objetivos = Objetivo.criarObjetivos();

        // Cria o tabuleiro
        this.tabuleiro.criaTabuleiro();

        // Distribui os territórios
        int resto = baralho.size() % players.size();

        for (int i = 0; i < players.size(); i++) {
            Jogador jogador = new Jogador(players.get(i), cores.get(i));
            jogador.setObjetivo(objetivos.get(i));
            jogador.setCartas(baralho.distribuiCarta(players.size()));
            if (resto > 0) {
                jogador.addCarta(baralho.comprarCarta());
                resto--;
            }
            jogador.setExercitosIni(baralho);
            this.jogadoresList.add(jogador);
        }

        // Use a função sortearOrdemJogo para obter uma ordem aleatória
        List<Integer> ordem = Jogador.sortearOrdemJogo(players.size());

        // Crie uma nova lista de jogadores com base na ordem aleatória
        List<Jogador> jogadoresOrdenados = new ArrayList<>();
        for (int i : ordem) {
            jogadoresOrdenados.add(this.jogadoresList.get(i - 1));
        }

        // Atualize a lista jogadoresList para refletir a ordem aleatória
        this.jogadoresList = jogadoresOrdenados;

        // Adiciona os coringas ao baralho
        baralho.addCoringa();
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


    public void validaMovimento(String nome1, String nome2, int qtdExercito) {
        Territorio origem = Tabuleiro.buscaTerritorio(nome1);
        Territorio destino = Tabuleiro.buscaTerritorio(nome2);

        if (origem.getIdJogadorDono() == destino.getIdJogadorDono()) {
            if(this.tabuleiro.fazFronteira(origem.nome, destino.nome)){
                Territorio.movimenta(origem, destino, qtdExercito);
            }
            else{
                System.out.println("Territorios não fazem fronteira");
            }
        }
        else{
            System.out.println("Territorios não pertencem ao mesmo jogador");
        }
    }

    public void ganhaCarta(String jogador){
        this.jogadoresList.get(Integer.parseInt(jogador)).addCarta(baralho.comprarCarta());
    }

    public void turno(String jogador){
        this.jogadoresList.get(Integer.parseInt(jogador)).receberExercitos();
    }
}