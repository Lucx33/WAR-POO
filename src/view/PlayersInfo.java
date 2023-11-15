package view;

import controller.Observable;
import controller.Observer;

import java.util.ArrayList;
import java.util.List;

public class PlayersInfo implements Observable {
    static List<String> names;
    static List<String> colors;

    private List<Observer> observers = new ArrayList<>();

    static boolean status = false;

    void setPlayersInfo(List<String> nomes, List<String> cores) {
        names = nomes;
        colors = cores;
        status = true;
        notifyObservers();
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getColors() {
        return colors;
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

        dados[0]= "NovoJogo";
        dados[1]= names;
        dados[2]= colors;
        return dados;
    }

    void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this); // Notifica cada observador
        }
    }

}