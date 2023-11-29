package model;
	
	import controller.Observer;
	import controller.Observable;
	
	import javax.swing.*;
	import java.io.*;
	import java.util.*;
	
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
	
	    private boolean end, load = false;
	
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
	        for(Jogador jogador : jogadoresList) {
	            if(jogador.getTerritoriosString().isEmpty()) {
	                Jogador morto = jogador;
	                Jogador assasino = jogadoresList.get(jogadorAtual);
	                if(assasino.getObjetivo().getObjetivoId() == morto.getCorId()){
	                	end = true;
	                	notifyObservers();
	                }
	            }
	        }
			for(Jogador jogador : jogadoresList) {
				if(jogador.getTerritoriosString().isEmpty()) {
					System.out.println("Jogador " + jogador.getNome() + " perdeu");
					jogadoresList.remove(jogador);
					break;
				}
			}
	        for(Jogador jogador: jogadoresList) {
	        	boolean result = Objetivo.verificaObjetivo(jogador.objetivo, jogador, jogadoresList);
	    		if(result) {
	    			end = true;
	    			notifyObservers();
	    		}
	    	}
	    }
	
	
	    /**
	     * Verifica se o jogador atual cumpriu seu objetivo
	     */
	    public void verificarObjetivo() {
	    	Jogador temp = jogadoresList.get(jogadorAtual);
	    	Objetivo objetivo = temp.getObjetivo();
	    	boolean result = Objetivo.verificaObjetivo(objetivo, temp, jogadoresList);
	    	if(result) {
	    		System.out.println("Objetivo cumprido");
	            end = true;
	            notifyObservers();
	    	}
	    }
	
	
	
	    /**
	     * Valida e realiza um movimento
	     * @param origem Nome do território de origem
	     * @param destino Nome do território de destino
	     */
	    public void movimenta(String origem, String destino) {
	        tabuleiro.validaMovimento(origem, destino);
			verificarObjetivo();
	    }
	
	    /**
	     * Valida e realiza um recebimento de carta
	     * para o jogador atual
	     */
	    public void ganhaCarta(){
	        // Verifica se o jogador já ganhou uma carta nesse turno
	        if (!jaGanhou) {
	            Jogador temp = jogadoresList.get(jogadorAtual);
	            if (temp.getCartas().size() < 5) {
	                temp.addCarta(baralho.comprarCarta());
	                jaGanhou = true;
	            }
	            else {
	                System.out.println("Jogador ja possui 5 cartas");
	            }
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
	            System.out.println("Territórios: " + jogador.getTerritorios().toString());
	            System.out.println("Cartas: " + jogador.getCartas().toString());
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
	                return jogador.getTerritoriosString();
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
	        if(load){
	            dados[0]= "Load";
	            dados[1]= getCorJogadorAtual();
	            load = false;
	            return dados;
	        }
	        else if(end){
	            dados[0]= "FimJogo";
	            dados[1]= getCorJogadorAtual();
	            return dados;
	        }
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
	
	
	    /**
	     * Método para resgatar a quantidade de exércitos do jogador atual
	     */
	    public int getExercitosMovimentadosVitoria(String nomeTerritorio){
	        return Tabuleiro.buscaTerritorio(nomeTerritorio).getExercitosMovimentadosVitoria();
	    }
	
	    /**
	     * Método para resgatar a quantidade de exércitos de um território
	     * @param nomeTerritorio Nome do território
	     */
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
	        verificarObjetivo();
	    }
	
	
	
	    /**
	     * Adiciona um observador a todos os territórios do tabuleiro
	     */
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
	
	    public void trocarCartas() {
	        Jogador temp = jogadoresList.get(jogadorAtual);
	        temp.trocarCartas();
	        temp.removeCartasUsadasNaTroca();
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
	        Continente continenteTemp = null;
	    	for(Continente continente : tabuleiro.getContinentes()) {
	    		if(continente.contemTerritorio(nome)){
	    			continenteTemp = continente;
	    		}
	    	}
	        for(Jogador jogador : jogadoresList) {
	            if(jogador.getTerritoriosString().contains(nome)) {
	                jogador.removeTerritorio(Tabuleiro.buscaTerritorio(nome));
	            }
	            if(jogador.getContinentes().contains(continenteTemp)) {
	            	jogador.removeContinente(continenteTemp);
	            }
	
	        }
	        temp.addTerritorio(Tabuleiro.buscaTerritorio(nome));
	
	        if(temp.verificaControleContinente(continenteTemp)) {
	        	temp.addContinente(continenteTemp);
	        }
	
	    }
	
	
	    /**
	     * Resgata as cartas do jogador atual
	     */
	    public Map<String, String> getCartasJogadorAtual() {
	        Jogador temp = jogadoresList.get(jogadorAtual);
	        List<String> paises = temp.getCartasString();
	        Map<String, String> cartasMap = new HashMap<>();
	
	        for (String pais : paises) {
	            String continente = Tabuleiro.buscaContinentePais(pais).getNomeCurto();
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
	
	    public List<String> getContinentesAtuais() {
	        return jogadoresList.get(jogadorAtual).getContinentesString();
	    }
	
	    public int getExercitosContinente(String nome) {
	        return Tabuleiro.buscaContinente(nome).getBonusExercitos(nome);
	    }
	
	    public void setExercitosAtuais(int exercitosContinente) {
	        jogadoresList.get(jogadorAtual).addExercitos(exercitosContinente);
	    }
	
	    public void superJogador(String paisAtacante, String paisDefensor, List<Integer> dadosAtaque, List<Integer> dadosDefesa) {
	        tabuleiro.simulaAtaque(paisAtacante, paisDefensor, dado, dadosAtaque, dadosDefesa);
	    }
	

	    public void saveGameState() {
	        // Cria um JFileChooser
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Salvar estado do jogo");
	
	        // Mostra o diálogo de salvar arquivo e verifica se o usuário selecionou um arquivo
	        int userSelection = fileChooser.showSaveDialog(null);
	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            // Obtém o arquivo selecionado pelo usuário
	            File fileToSave = fileChooser.getSelectedFile();
	
	            // Salva o estado do jogo no arquivo
	            try (FileWriter outputStream = new FileWriter(fileToSave)) {
	                // Salva os dados a partir do jogador atual até o final da lista
	                for (int i = jogadorAtual; i < jogadoresList.size(); i++) {
	                    Jogador jogador = jogadoresList.get(i);
	                    outputStream.write("Nome: " + jogador.getNome() + "\n");
	                    outputStream.write("Cor: " + jogador.getCor() + "\n");
	                    outputStream.write("Objetivo: " + jogador.getObjetivo().getDescricao() + "!" + jogador.getObjetivo().getObjetivoId() + "\n");
	                    String territorios = jogador.getTerritorios().toString();
	                    String territoriosSemColchetes = territorios.substring(1, territorios.length() - 1);
	                    outputStream.write("Territórios: " + territoriosSemColchetes + "\n");
	                    outputStream.write("Cartas: " + jogador.getCartasNomes() + "\n");
	                }
	
	                // Continua salvando do início da lista até o jogador atual
	                for (int i = 0; i < jogadorAtual; i++) {
	                    Jogador jogador = jogadoresList.get(i);
	                    outputStream.write("Nome: " + jogador.getNome() + "\n");
	                    outputStream.write("Cor: " + jogador.getCor() + "\n");
	                    outputStream.write("Objetivo: " + jogador.getObjetivo().getDescricao() + "!" + jogador.getObjetivo().getObjetivoId() + "\n");
	                    String territorios = jogador.getTerritorios().toString();
	                    String territoriosSemColchetes = territorios.substring(1, territorios.length() - 1);
	                    outputStream.write("Territórios: " + territoriosSemColchetes + "\n");
	                    outputStream.write("Cartas: " + jogador.getCartasNomes() + "\n");
	                }
	
	            } catch (IOException e) {
	                JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	
	
	
	    public void loadGameState() {
	        // Cria um JFileChooser para escolher o arquivo
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Carregar estado do jogo");
	        tabuleiro.criaTabuleiro();
	        baralho.criaBaralho();
	        baralho.shuf();
	        baralho.addCoringa();
	        objetivos = Objetivo.criarObjetivos();
	
	
	        // Mostra o diálogo de abrir arquivo e verifica se o usuário selecionou um arquivo
	        int userSelection = fileChooser.showOpenDialog(null);
	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            // Obtém o arquivo escolhido pelo usuário
	            File fileToLoad = fileChooser.getSelectedFile();
	
	            // Limpa o estado atual do jogo
	            jogadoresList.clear();
	            // outros estados do jogo também devem ser limpos ou reinicializados aqui
	            String line;
	            // Carrega o estado do jogo a partir do arquivo
	            try (Scanner scanner = new Scanner(fileToLoad)) {
	                line = scanner.nextLine();
	                while (line.contains("Nome")) {
	                    String nome = line.substring(6);
	                    line = scanner.nextLine();
	                    String cor = line.substring(5);
	                    Jogador jogador = new Jogador(nome, cor);
	                    jogadoresList.add(jogador);
	                    line = scanner.nextLine();
	                    String descricao = line.substring(10, line.indexOf("!"));
	                    int id = Integer.parseInt(line.substring(line.indexOf("!") + 1));
	                    Objetivo objetivo = new Objetivo(id, descricao);
	                    jogador.setObjetivo(objetivo);
	                    line = scanner.nextLine();
	                    // Territórios
	                    String todosTerritorios = line.substring(line.indexOf(":") + 2);
	                    String[] territoriosArray = todosTerritorios.split(", ");
	                    for (String terr : territoriosArray) {
	                        String[] detalhesTerritorio = terr.split(" ");
	                        if (detalhesTerritorio.length == 3) {
	                            String nomeTerritorio = detalhesTerritorio[0];
	                            int idDono = Integer.parseInt(detalhesTerritorio[1]);
	                            int qtdExercito = Integer.parseInt(detalhesTerritorio[2]);
	                            Territorio territorio = Tabuleiro.buscaTerritorio(nomeTerritorio);
	                            territorio.setIdJogadorDono(idDono);
	                            territorio.setQtdExercito(qtdExercito);
	                            jogador.addTerritorio(territorio);
	                        }
	                    }
	                    line = scanner.nextLine();
	                    //
	                    String todasCartas = line.substring(line.indexOf(":") + 1);
	                    String[] cartasArray = todasCartas.split(" ");
	                    for (String carta : cartasArray) {
	                        if(!carta.isEmpty()){
	                            jogador.addCarta(baralho.pegaCarta(carta));
	                        }
	                    }
	                    if(scanner.hasNextLine()) {
	                        line = scanner.nextLine();
	                    } else {
	                    	for(Jogador temp : jogadoresList) {
	        	            	for(Continente continente : Tabuleiro.continentes) {
	        	            		if(temp.verificaControleContinente(continente)) {
	        	            			temp.continentes.add(continente);
	        	            		}
	        	            	}
	        	            }
	                        load = true;
	                        notifyObservers();
	                        break;
	                    }
	                }
	            } catch (IOException e) {
	                JOptionPane.showMessageDialog(null, "Erro ao carregar o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	            
	        }
	    }
	}
