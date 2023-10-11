package model;

class Territorio {
	
	String nome;
	int idJogadorDono;
	int qtdExercito;
	
	Territorio(String nome, int id_jogador_dono, int qtd){
		this.nome = nome;
		this.idJogadorDono = id_jogador_dono;
		this.qtdExercito = qtd;
	}

	public String getNome() {
		return nome;
	}

	public int getIdJogadorDono() {
		return idJogadorDono;
	}

	public void setIdJogadorDono(int id_jogador_dono) {
		this.idJogadorDono = id_jogador_dono;
	}

	public int getQtdExercito() {
		return qtdExercito;
	}

	public void setQtdExercito(int qtd_exercito) {
		this.qtdExercito = qtd_exercito;
	}
	
	@Override
    public String toString() {
        return  "Territorio = " + nome +
                ", ID Jogador Dono = " + idJogadorDono +
                ", Exercitos = " + qtdExercito;
                
    }
	
	
}
