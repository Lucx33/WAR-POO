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
	
	@Override
    public String toString() {
        return  "Territorio = " + nome +
                ", ID Jogador Dono = " + idJogadorDono +
                ", Exercitos = " + qtdExercito;
                
    }
	
	
}