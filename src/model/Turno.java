package model;

import controller.Observable;
import controller.Observer;

import java.util.ArrayList;
import java.util.List;

public class Turno implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private int currentPlayer = 0;

    public void nextTurn() {
        // Aqui vai a lógica para passar para o próximo jogador.
        currentPlayer = (currentPlayer + 1) % 3;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public Object get() {
        return null;
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify();
        }
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}

