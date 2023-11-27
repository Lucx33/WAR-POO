package model;

import java.util.*;

class Jogador {
	String nome;
	String cor;
	List<Carta> cartas;
	List<Territorio> territorios;
	List<Continente> continentes;
	Objetivo objetivo;
	int ordemjogo;
	int exercitos;
	int idJogador;

	Jogador(String nome, String cor) {
		this.nome = nome;
		this.cor = cor;
		this.cartas = new ArrayList<>();
		this.territorios = new ArrayList<>();
		this.continentes = new ArrayList<>();
		this.ordemjogo = -1;
		this.exercitos = 0;
		this.objetivo = null;
		this.idJogador = getCorId(this);
	}

	String getNome() {
		return nome;
	}

	String getCor() {
		return cor;
	}

	int getExercitos() {
		return exercitos;
	}

	int getordemjogo() {
		return ordemjogo;
	}

	int getIdJogador() {
		return idJogador;
	}

	void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	void addCarta(Carta carta) {
		cartas.add(carta);
	}

	List<Carta> getCartas() {
		return cartas;
	}

	List<String> getCartasString() {
		List<String> cartasString = new ArrayList<>();
		for (Carta carta : cartas) {
			cartasString.add(carta.getTerritorio());
		}
		return cartasString;
	}

	void addTerritorio(Territorio territorio) {
		territorios.add(territorio);
	}

	List<Territorio> getTerritorios() {
		return territorios;
	}

	List<Continente> getContinentes() {
		return continentes;
	}

	List<String> getTerritoriosString() {
		List<String> territoriosString = new ArrayList<>();
		for (Territorio territorio : territorios) {
			territoriosString.add(territorio.getNome());
		}
		return territoriosString;
	}

	static List<Integer> sortearOrdemJogo(int n_jogadores) {
		List<Integer> order = new ArrayList<>();
		for (int i = 1; i <= n_jogadores; i++) {
			order.add(i);
		}
		Collections.shuffle(order);
		return order;
	}

	int getCorId(Jogador jogador) {
		return switch (cor) {
			case "Azul" -> 1;
			case "Amarelo" -> 2;
			case "Branco" -> 3;
			case "Verde" -> 4;
			case "Preto" -> 5;
			case "Vermelho" -> 6;
			default -> 0;
		};
	}

	@Override
	public String toString() {
		return "Jogador: " + nome + " | Cor: " + cor + " | Ordem de Jogada: " + ordemjogo;
	}

	void addExercitos(int i) {
		this.exercitos += i;
	}

	void removeExercitos(int i) {
		this.exercitos -= i;
	}

	void receberExercitos() {
		int numTerritorios = territorios.size();
		int numExercitosRecebidos = numTerritorios / 2;
		trocarCartas();
		addExercitos(numExercitosRecebidos);
	}

	void addContinente(Continente continente) {
		continentes.add(continente);
	}
	
	void removeContinente(Continente continente) {
		continentes.remove(continente);
	}


	boolean possuiContinente(Continente continente) {
		List<Territorio> territoriosDoContinente = continente.getTerritorios();
		return territorios.containsAll(territoriosDoContinente);
	}

	void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	void setExercitosIni(Baralho baralho) {
		while (!this.cartas.isEmpty()) {
			Carta carta = this.cartas.get(0);
			this.addTerritorio(Tabuleiro.buscaTerritorio(carta.getTerritorio()));
			Tabuleiro.buscaTerritorio(carta.getTerritorio()).setQtdExercito(1);
			Tabuleiro.buscaTerritorio(carta.getTerritorio()).setIdJogadorDono(this.idJogador);
			baralho.addBaralho(carta);
			this.cartas.remove(0);
		}
	}


	void setordemjogo(int ordemjogo) {
		this.ordemjogo = ordemjogo;
	}

	Objetivo getObjetivo() {
		return objetivo;
	}

	void trocarCartas(){
		if(Carta.verificaCartas(this.cartas)){
			System.out.println("Troca de cartas");
			int temp = Baralho.qtdTroca;
			Baralho.addQtdTroca(2);
			this.addExercitos(temp);
		};
	}

	void adiconaExercitoATerritorio(String territorio) {
		Territorio territorioAtual = Tabuleiro.buscaTerritorio(territorio);
		territorioAtual.addExercito();
	}

	void removeExercitoATerritorio(String territorio) {
		Territorio territorioAtual = Tabuleiro.buscaTerritorio(territorio);
		territorioAtual.removeExercito();
	}

	void removeTerritorio(Territorio territorio) {
		this.territorios.remove(territorio);
	}

	public boolean verificaControleContinente(Jogador jogador, Continente continente) {
		List<String> territoriosJogador = jogador.getTerritoriosString();

		for (Territorio territorio : continente.getTerritorios()) {
			if (!territoriosJogador.contains(territorio.getNome())) {
				return false;
			}
		}

		return true; // O jogador tem todos os territórios do continente
	}


	public List<String> getContinentesString() {
		List<String> continentesString = new ArrayList<>();
		for (Continente continente : continentes) {
			continentesString.add(continente.getNome());
		}
		return continentesString;
	}

    public String getCartasNomes() {
		String cartasNomes = "";
		for (Carta carta : cartas) {
			cartasNomes += carta.getTerritorio() + " ";
		}
		return cartasNomes;
    }
}