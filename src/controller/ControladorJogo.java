package controller;
import java.util.List;
import model.ApiModel;
import view.IniciaInterface;
import view.Jogo;
import view.PlayersInfo;

public class ControladorJogo implements Observer{
    static ApiModel partida;
    static IniciaInterface interfaceJogo;
    static PlayersInfo playersInfo;
    static Jogo telaJogo;
    
    boolean posicionamentoInicial, fasePosicionamento, faseAtaque, faseMovimento;

    Observable obs;
    Object dados[];
    String tipo;


    ControladorJogo(){
        // Cria uma instancia do PlayersInfo
        playersInfo = new PlayersInfo();

        //Inicia Interface
        interfaceJogo = new IniciaInterface(playersInfo);

        // Cria uma instancia do jogo
        partida = ApiModel.getInstance();


        // Adiciona o IniciaJogo como observador do PlayersInfo
        playersInfo.addObserver(this);
        partida.addObserver(this);

    }

    public static void main(String[] args) {
        new ControladorJogo();
    }
    
    
    

    /**
     * Método de notificação chamado quando um objeto observável é alterado.
     * Este método é parte central do padrão Observer, permitindo que o objeto ControladorJogo
     * seja notificado de mudanças em objetos observáveis.
     *
     * @param arg O objeto observável que notificou a mudança.
     */
    @Override
    public void notify(Observable arg) {
        // Recebe o objeto observável que notificou a mudança.
        obs = arg;

        // Obtém os dados transmitidos pelo objeto observável.
        dados = (Object []) obs.get();

        // Obtém o tipo de notificação, que determina a natureza da mudança.
        tipo = (String) dados[0];

        // Um switch para lidar com diferentes tipos de notificações.
        switch(tipo){
            // Cada caso no switch corresponde a um tipo específico de notificação.
            // Para cada tipo, um método 'handle' correspondente é chamado para lidar com a mudança específica.
        	// Os campos de 'dados' mudam pra cada tipo de notificação
        
            case "NovoJogo":
            	
                handleNovoJogo((List<String>) dados[1], (List<String>) dados[2]);
                break;
            case "AtualizaExercitos":
                
            	handleAtualizaExercitos((Integer)dados[1]);
                break;

            case "FasePosicionamento":
            	
            	handleFasePosicionamento((String) dados[1], (String) dados[2]);
                break;

            case "FaseAtaque":
                handleFaseAtaque((String) dados[1], (String) dados[2]);
                break;

            case "AtualizaTerritorio":
                handleAtualizaTerritorio((String) dados[1], (Integer) dados[2], (Integer) dados[3]);
                break;

            case "FaseMovimento":
                handleFaseMovimento((String) dados[1], (String) dados[2]);
                break;

            case "MudancaDeDono":
                handleMudancaDeDono((int) dados[1], (String) dados[2]);
                break;

            case "TrocaTurno":
                handleTrocaTurno();
                break;
            case "LancamentoDados":
            	handleLancamentoDados((List<Integer>) dados[1], (List<Integer>) dados[2]);
            	break;
        }
    }

    private void handleLancamentoDados(List<Integer> dado, List<Integer> dado1) {
        
    }


    /**
     * Inicializa um novo jogo com as informações dos jogadores.
     * Este método configura o jogo com os nomes e cores dos jogadores,
     * atualiza a interface do jogo e inicia o jogo.
     *
     * @param playerNames Lista de nomes dos jogadores.
     * @param playerColors Lista de cores associadas a cada jogador.
     */
    public void handleNovoJogo(List<String> playerNames, List<String> playerColors) {
        partida.setGame(playerNames, playerColors);
        partida.addObserverToAllTerritories(this);
        partida.addObserverToTabuleiro(this);
        partida.addObserverToDice(this);

        telaJogo = new Jogo(partida.getNomesJogadores(), partida.getCoresJogadores());
        telaJogo.addObserver(this);


        for (String player : playerNames) {
            telaJogo.setCorDono(partida.getTerritoriosPorDono(player), playerColors.get(playerNames.indexOf(player)));
        }
        telaJogo.repaint();
        handleTrocaTurno();
    }
    
    
    
    /**
     * Gerencia a troca de turnos no jogo.
     * Este método é responsável por avançar o jogo para o próximo turno,
     * atualizando o estado do jogo e a interface do usuário de acordo.
     */
    public void handleTrocaTurno(){
    	partida.proximoTurno();
        partida.turno(partida.getJogadorAtual());
       
        telaJogo.atualizaJogadorAtual(partida.getCorJogadorAtual());
        telaJogo.setExercitos(partida.getExercitosAtuais());
        telaJogo.repaint();
    }
    
    
    
    /**
     * Atualiza a quantidade de exércitos na interface do jogo
     * Na fase de posicionamento
     * 
     * 
     * @param qtd A quantidade de exércitos a serem atualizados na interface do jogo.
     */
    public void handleAtualizaExercitos(int qtd) {
    	telaJogo.setExercitos(qtd);
        telaJogo.repaint();
    }
    
    
    
    /**
     * Gerencia a fase de posicionamento de exércitos no jogo.
     * Este método é chamado durante a fase de posicionamento e
     * é responsável por manipular os exércitos de acordo com as ações do jogador.
     *
     * @param pais O país onde os exércitos serão posicionados ou removidos.
     * @param sinal Indica se os exércitos serão adicionados ('+') ou removidos ('-').
     */
    public void handleFasePosicionamento(String pais, String sinal) {
        partida.manipulaExercitos(pais, sinal);

        telaJogo.setExercitos(partida.getExercitosAtuais());
        telaJogo.repaint();

        if(partida.getExercitosAtuais() == 0){
            telaJogo.trocaFase();
        }
    }
    
    
    
    /**
     * Gerencia a fase de ataque no jogo.
     * Este método é chamado durante a fase de ataque e verifica se o ataque é válido.
     *
     * @param paisAtacante O país que está atacando.
     * @param paisDefensor O país que está sendo atacado.
     */
    public void handleFaseAtaque(String paisAtacante, String paisDefensor) {
        List<String> vizinhos = partida.getVizinhos(paisAtacante);

        if(vizinhos.contains(paisDefensor.toLowerCase())){
            partida.validaAtaque(paisAtacante, paisDefensor);
        } else {
            telaJogo.mostrarVizinhos(paisAtacante, vizinhos);
        }
        telaJogo.repaint();
    }
    
    
    
    /**
     * Atualiza as informações de um território no jogo.
     * Este método é chamado quando um território é atualizado, seja por conquista ou movimentação de tropas.
     *
     * @param nome O nome do território.
     * @param idJogadorDono O ID do jogador que possui o território.
     * @param qtdExercito A quantidade de exércitos no território.
     */
    public void handleAtualizaTerritorio(String nome, int idJogadorDono, int qtdExercito) {
        telaJogo.setPais(nome, idJogadorDono, qtdExercito);
        telaJogo.repaint();
    }
    
    

    /**
     * Gerencia a fase de movimento de exércitos entre territórios.
     * Este método é chamado durante a fase de movimentação de exércitos.
     *
     * @param paisOrigem O país de origem dos exércitos.
     * @param paisDestino O país de destino dos exércitos.
     */
    public void handleFaseMovimento(String paisOrigem, String paisDestino) {
        List<String> vizinhos = partida.getVizinhos(paisOrigem);

        if(vizinhos.contains(paisDestino.toLowerCase())){
            partida.movimenta(paisOrigem, paisDestino);
        } else {
            telaJogo.mostrarVizinhos(paisOrigem, vizinhos);
        }
        telaJogo.repaint();
    }
    
    

    /**
     * Gerencia a mudança de dono de um território no jogo.
     * Este método é chamado quando um território muda de dono após um ataque bem-sucedido
     * E o defensor chegou a 0 exércitos.
     *
     * @param idAtacante O ID do jogador que conquistou o território.
     * @param nomeTerritorio O nome do território conquistado.
     */
    public void handleMudancaDeDono(int idAtacante, String nomeTerritorio) {
        System.out.println("Mudanca de Dono");
        partida.trocaDono(nomeTerritorio, idAtacante);
        partida.ganhaCarta(idAtacante);
    }



}