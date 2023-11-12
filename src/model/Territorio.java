package model;

import controller.Observable;
import controller.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Territorio implements Observable {
	String nome;
	int idJogadorDono;
	int qtdExercito;

	private List<Observer> observers = new ArrayList<>();
	
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
		notifyObservers();
	}

	int getQtdExercito() {
		return qtdExercito;
	}

	void setQtdExercito(int qtd_exercito) {
		this.qtdExercito = qtd_exercito;
		notifyObservers();
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


	void addExercito() {
		this.qtdExercito += 1;
		notifyObservers();
	}
	void removeExercito() {
		this.qtdExercito -= 1;
		notifyObservers();
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
		dados[0]= "AtualizaTerritorio";
		dados[1]= this.nome;
		dados[2]= this.idJogadorDono;
		dados[3]= this.qtdExercito;
		return dados;
	}

	protected void notifyObservers() {
		for (Observer observer : observers) {
			observer.notify(this);
		}
	}

}