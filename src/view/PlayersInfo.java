package view;

import controller.ObserverSetPlayersInfo;

import java.util.ArrayList;
import java.util.List;

public class PlayersInfo {
    static List<String> names;
    static List<String> colors;

    private List<ObserverSetPlayersInfo> observers = new ArrayList<>();

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

    public void addObserver(ObserverSetPlayersInfo observer) {
        observers.add(observer);
    }

    public void removeObserver(ObserverSetPlayersInfo observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (ObserverSetPlayersInfo observer : observers) {
            observer.update();
        }
    }


}
