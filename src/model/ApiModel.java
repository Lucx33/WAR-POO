package model;

import controller.Observer;
import controller.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiModel implements Observable{

    private static ApiModel ini=null;


    Baralho baralho;
    Tabuleiro tabuleiro;

    List<Objetivo> objetivos;
    List<Jogador> jogadoresList;

    Dado dado;

    private int jogadorAtual = 0;

    private int fase = 0;

    private List<Observer> observers = new ArrayList<>();


    public ApiModel(){
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
        tabuleiro.validaAtaque(nome1, nome2);
    }


    public void validaMovimento(String nome1, String nome2, int qtdExercito) {
        tabuleiro.validaMovimento(nome1, nome2, qtdExercito);
    }

    public void ganhaCarta(String jogador){
        this.jogadoresList.get(Integer.parseInt(jogador)).addCarta(baralho.comprarCarta());
    }

    public void turno(Integer jogador){
        Jogador temp = this.jogadoresList.get(jogador);
        temp.receberExercitos();
        notifyObservers();
    }

    public void fasePosicionamento(Integer jogador){
        Jogador temp = this.jogadoresList.get(jogador);
        temp.receberExercitos();
        notifyObservers();
    }

    public void printGameState() {
        System.out.println("====== Estado do Jogo ======");

        System.out.println("\n--- Jogadores ---");
        for(Jogador jogador : jogadoresList) {
            System.out.println("Nome: " + jogador.getNome());
            System.out.println("Cor: " + jogador.getCor());
            System.out.println("Exércitos: " + jogador.getExercitos());
            System.out.println("Objetivo: " + jogador.getObjetivo().toString());
            System.out.println("Territórios: " + jogador.getTerritorios().toString()); // Assumindo que Jogador tem um método getTerritorios() que retorna uma lista de territórios.
            System.out.println("Cartas: " + jogador.getCartas().toString()); // Assumindo que Jogador tem um método getCartas() que retorna uma lista de cartas.
            System.out.println("-----------------------------");
        }

        System.out.println("==============================");
    }

    public List<String> getTerritoriosPorDono(String jogadorNome) {
        for (Jogador jogador : jogadoresList) {
            if (jogador.getNome().equals(jogadorNome)) {
                return jogador.getTerritoriosString(); // Assumindo que Jogador tem um método getTerritorios() que retorna uma lista de nomes de territórios.
            }
        }
        return new ArrayList<>();
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    public String getCorJogadorAtual() {
        return jogadoresList.get(jogadorAtual).getCor();
    }

    public void proximoTurno() {
        jogadorAtual = (jogadorAtual + 1) % jogadoresList.size();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void addObserverTerritorios(Observer observer) {
        //aqui
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public Object get() {
        Object dados[]=new Object[5];
        dados[0]= "AtualizaExercitos";
        dados[1]= getExercitosAtuais();
        return dados;
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }

    public List<String> getNomesJogadores() {
        List<String> nomes = new ArrayList<>();

        for (Jogador jogador : jogadoresList) {
            nomes.add(jogador.getNome());
        }

        return nomes;
    }

    public List<String> getCoresJogadores() {
        List<String> cores = new ArrayList<>();

        for (Jogador jogador : jogadoresList) {
            cores.add(jogador.getCor());
        }

        return cores;
    }

    public int getExercitosAtuais(){
        return jogadoresList.get(jogadorAtual).getExercitos();
    }

    public void manipulaExercitos(String nome, String sinal){
        Jogador temp = jogadoresList.get(jogadorAtual);
        if(sinal.equals("+")) {
            temp.adiconaExercitoATerritorio(nome);
            temp.removeExercitos(1);
        }
        else {
            temp.removeExercitoATerritorio(nome);
            temp.addExercitos(1);
        }
    }

    public void addObserverToAllTerritories(Observer observer) {
        for (Continente continente : tabuleiro.getContinentes()) {
            for (Territorio territorio : continente.getTerritorios()) {
                territorio.addObserver(observer);
            }
        }
    }

    public List<String> getVizinhos(String nomeTerritorio) {
        return tabuleiro.vizinhos(nomeTerritorio.toLowerCase());
    }


}