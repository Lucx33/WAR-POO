package model;

import controller.Observable;
import controller.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.sort;

class Dado implements Observable {
    Random random;
    private List<Observer> observers = new ArrayList<>();

    List<Integer> resultadosAtaque = new ArrayList<>();
    List<Integer> resultadosDefesa = new ArrayList<>();
    Dado() {
        random = new Random();
    }

    int rolar() {
        return random.nextInt(6) + 1;
    }

    List<Integer> lancamentoDados(int qtdAtaque, int qtdDefesa) {;
        resultadosAtaque.clear();
        resultadosDefesa.clear();
        for (int i = 0; i < qtdAtaque; i++) {
            resultadosAtaque.add(rolar());
        }
        for (int i = 0; i < qtdDefesa; i++) {
            resultadosDefesa.add(rolar());
        }
        List<Integer> resultados = new ArrayList<>();
        resultados.addAll(resultadosAtaque);
        resultados.addAll(resultadosDefesa);

        notifyObservers();
        return resultados;
    }

    List<Integer> simulacaoDados(int qtdAtaque, int qtdDefesa, List<Integer> ataque, List<Integer> defesa) {
        resultadosAtaque.clear();
        resultadosDefesa.clear();
        for (int i = 0; i < qtdAtaque; i++) {
            resultadosAtaque.add(ataque.get(i));
        }
        for (int i = 0; i < qtdDefesa; i++) {
            resultadosDefesa.add(defesa.get(i));
        }
        List<Integer> resultados = new ArrayList<>();
        resultados.addAll(resultadosAtaque);
        resultados.addAll(resultadosDefesa);

        notifyObservers();
        return resultados;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public Object get() {
        Object dados[]=new Object[5];
        dados[0]= "LancamentoDados";
        dados[1]= this.resultadosAtaque;
        dados[2]= this.resultadosDefesa;
        return dados;
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }
}