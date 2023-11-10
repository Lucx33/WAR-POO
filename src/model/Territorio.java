package model;

class Territorio {
	String nome;
	int idJogadorDono;
	int qtdExercito;
	
	Territorio(String nome){
		this.nome = nome;
	}

	String getNome() {
		return nome;
	}

	int getIdJogadorDono() {
		return idJogadorDono;
	}

	void setIdJogadorDono(int id_jogador_dono) {
		this.idJogadorDono = id_jogador_dono;
	}

	int getQtdExercito() {
		return qtdExercito;
	}

	void setQtdExercito(int qtd_exercito) {
		this.qtdExercito = qtd_exercito;
	}

	static void ataque(Territorio atacante, Territorio defensor){
		Dado dado = new Dado();
		int qtdAtaque = atacante.getQtdExercito();
		int qtdDefesa = defensor.getQtdExercito();
		int[] dadosAtaque = dado.DadosAtaque(qtdAtaque);
		int[] dadosDefesa = dado.DadosDefesa(qtdDefesa);
		dado.compararResultados(dadosAtaque, dadosDefesa);
		if(defensor.qtdExercito == 0){
			defensor.setIdJogadorDono(atacante.getIdJogadorDono());
			defensor.setQtdExercito(atacante.getQtdExercito() - 1);
			atacante.setQtdExercito(1);
		}

	}

	static void movimenta(Territorio origem, Territorio destino, int qtdExercito){
		origem.setQtdExercito(origem.getQtdExercito() - qtdExercito);
		destino.setQtdExercito(destino.getQtdExercito() + qtdExercito);
	}

	@Override
    public String toString() {
        return  "Territorio = " + nome +
                ", ID Jogador Dono = " + idJogadorDono +
                ", Exercitos = " + qtdExercito;
                
    }
	
	
}