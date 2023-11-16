package model;

import controller.Observer;
import controller.Observable;

import java.util.ArrayList;
import java.util.HashMap;
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

    boolean jaGanhou = false;
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

    /**
     * Inicia o jogo, criando o baralho, o tabuleiro, os objetivos e distribuindo os territórios.
     * @param players Lista de nomes dos jogadores
     * @param cores Lista de cores dos jogadores
     */
    public void setGame(List<String> players, List<String> cores) {
        // Cria o baralho e embaralha
        this.baralho.criaBaralho();
        this.baralho.shuf();

        // Cria os objetivos
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



    /**
     * Valida e realiza um ataque
     * @param atacante Nome do território atacante
     * @param defensor Nome do território defensor
     */
    public void validaAtaque(String atacante, String defensor){
        tabuleiro.validaAtaque(atacante, defensor, dado);
    }

    public void verificaObjetivo() {
    	Jogador temp = jogadoresList.get(jogadorAtual);
    	Objetivo objetivo = temp.getObjetivo();
    	if(Objetivo.verificaObjetivo(objetivo, temp)) {
    		System.out.println("Objetivo cumprido");
    	}
    }



    /**
     * Valida e realiza um movimento
     * @param origem Nome do território de origem
     * @param destino Nome do território de destino
     */
    public void movimenta(String origem, String destino) {
        tabuleiro.validaMovimento(origem, destino);
    }

    /**
     * Valida e realiza um recebimento de carta
     * para o jogador atual
     */
    public void ganhaCarta(){
        // Verifica se o jogador já ganhou uma carta nesse turno
        if (!jaGanhou) {
            Jogador temp = jogadoresList.get(jogadorAtual);
            temp.addCarta(baralho.comprarCarta());
            jaGanhou = true;
        }
    }



    /**
     * Executa o turno do jogador atual
     * @param jogador valor da Posição do jogador na lista de jogadores
     */
    public void turno(Integer jogador){
        Jogador temp = this.jogadoresList.get(jogador);
        temp.receberExercitos();
        notifyObservers();
    }



    /**
     * Método auxiliar para printar o estado do jogo
     */
    public void printGameState() {
        System.out.println("====== Estado do Jogo ======");

        System.out.println("\n--- Jogadores ---");
        for(Jogador jogador : jogadoresList) {
            System.out.println("Nome: " + jogador.getNome());
            System.out.println("Cor: " + jogador.getCor());
            System.out.println("Id: " + jogador.getIdJogador());
            System.out.println("Exércitos: " + jogador.getExercitos());
            System.out.println("Objetivo: " + jogador.getObjetivo().toString());
            System.out.println("Territórios: " + jogador.getTerritorios().toString()); // Assumindo que Jogador tem um método getTerritorios() que retorna uma lista de territórios.
            System.out.println("Cartas: " + jogador.getCartas().toString()); // Assumindo que Jogador tem um método getCartas() que retorna uma lista de cartas.
            System.out.println("-----------------------------");
        }

        System.out.println("==============================");
    }



    /**
     * Resgata os territórios de um jogador pelo nome
     * @param jogadorNome Nome do jogador
     */
    public List<String> getTerritoriosPorDono(String jogadorNome) {
        for (Jogador jogador : jogadoresList) {
            if (jogador.getNome().equals(jogadorNome)) {
                return jogador.getTerritoriosString(); // Assumindo que Jogador tem um método getTerritorios() que retorna uma lista de nomes de territórios.
            }
        }
        return new ArrayList<>();
    }



    /**
     * Resgata a posição do jogador atual na lista de jogadores
     */
    public int getJogadorAtual() {
        return jogadorAtual;
    }



    /**
     * Resgata a cor do Jogador atual
     */
    public String getCorJogadorAtual() {
        return jogadoresList.get(jogadorAtual).getCor();
    }



    /**
     * Troca o jogador atual para o próximo da lista
     * trocando assim o "Turno"
     */
    public void proximoTurno() {
        jogadorAtual = (jogadorAtual + 1) % jogadoresList.size();
        jaGanhou = false;
        Tabuleiro.resetAllMovimentadosVitoria();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
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



    /**
     * Método para resgatar os nomes dos jogadores
     */
    public List<String> getNomesJogadores() {
        List<String> nomes = new ArrayList<>();

        for (Jogador jogador : jogadoresList) {
            nomes.add(jogador.getNome());
        }

        return nomes;
    }



    /**
     * Método para resgatar as cores dos jogadores
     */
    public List<String> getCoresJogadores() {
        List<String> cores = new ArrayList<>();

        for (Jogador jogador : jogadoresList) {
            cores.add(jogador.getCor());
        }

        return cores;
    }



    /**
     * Método para resgatar a quantidade de exércitos do jogador atual
     */
    public int getExercitosAtuais(){
        return jogadoresList.get(jogadorAtual).getExercitos();
    }



    public int getExercitosMovimentadosVitoria(String nomeTerritorio){
        return Tabuleiro.buscaTerritorio(nomeTerritorio).getExercitosMovimentadosVitoria();
    }

    public int getExercitosPais(String nomeTerritorio){
        return Tabuleiro.buscaTerritorio(nomeTerritorio).getQtdExercito();
    }



    /**
     * Método para manipular os exércitos do jogador atual em um território
     * @param nome Nome do território
     * @param sinal Sinal de adição ou subtração
     */
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



    public void addObserverToTabuleiro(Observer observer) {
        tabuleiro.addObserver(observer);
    }

    public void addObserverToDice(Observer observer) {
    	dado.addObserver(observer);
    }



    /**
     * Método para resgatar os territórios vizinhos de um território
     * @param nomeTerritorio Nome do território
     */
    public List<String> getVizinhos(String nomeTerritorio) {
        return tabuleiro.vizinhos(nomeTerritorio.toLowerCase());
    }

    public void trocaDono(String nome) {
    	Jogador temp = jogadoresList.get(jogadorAtual);

    	for(Continente continente : tabuleiro.getContinentes()) {
    		if(continente.contemTerritorio(nome)){
    			
    		}
    	}
        for(Jogador jogador : jogadoresList) {
            if(jogador.getTerritoriosString().contains(nome)) {
                jogador.removeTerritorio(Tabuleiro.buscaTerritorio(nome));
            }

        }
        temp.addTerritorio(Tabuleiro.buscaTerritorio(nome));
    }

    public Map<String, String> getCartasJogadorAtual() {
        Jogador temp = jogadoresList.get(jogadorAtual);
        List<String> paises = temp.getCartasString();
        Map<String, String> cartasMap = new HashMap<>();

        for (String pais : paises) {
            String continente = Tabuleiro.buscaContinente(pais).getNomeCurto();
            cartasMap.put(pais, continente);
        }

        return cartasMap;
    }


    public void movimentaVitoria(String origem, String destino) {
        Territorio temp = Tabuleiro.buscaTerritorio(destino);
        if (Tabuleiro.buscaTerritorio(destino).getExercitosMovimentadosVitoria() <= 3) {
            tabuleiro.validaMovimento(origem, destino);
            temp.addExercitoMovimentadosVitoria();
        }
        tabuleiro.validaMovimento(origem, destino);
    }


    public int getObjetivoJogadorAtual() {
        return jogadoresList.get(jogadorAtual).getObjetivo().getObjetivoId();
    }

    public List<String> getTerritoriosAtuais(){
        return jogadoresList.get(jogadorAtual).getTerritoriosString();
    }
}