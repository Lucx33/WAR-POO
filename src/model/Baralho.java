package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    static List<Carta> baralho;

    public Baralho() {
        baralho = new ArrayList<>();
        String[] territorios = {"africadosul", "angola", "argelia", "egito", "nigeria", "somalia", "alasca", "calgary",
                "california", "groelandia", "mexico", "novayork", "quebec", "texas", "vancouver", "arabiasaudita", "bangladesh", "cazaquistao",
                "china", "coreiadonorte","coreiadosul", "estonia", "india", "ira", "iraque", "japao", "jordania", "letonia",
                "mongolia", "paquistao", "russia", "siberia", "siria", "tailandia", "turquia", "argentina", "brasil", "peru",
                "venezuela", "espanha", "franca", "italia", "polonia", "reinounido", "romenia", "suecia", "ucrania", "australia",
                "indonesia", "novazelandia", "perth", "c", "c"};
        String[] formas = {"Triângulo", "Quadrado", "Círculo", "Triângulo", "Círculo", "Quadrado", "Triângulo", "Círculo",
                "Quadrado", "Círculo", "Quadrado", "Quadrado", "Círculo", "Triângulo", "Triângulo", "Círculo", "Círculo", "Círculo",
                "Quadrado", "Quadrado", "Triângulo", "Círculo", "Triângulo", "Quadrado", "Triângulo", "Círculo", "Quadrado", "Quadrado",
                "Triângulo", "Círculo", "Triângulo", "Quadrado", "Quadrado", "Triângulo", "Triângulo", "Quadrado", "Círculo", "Triângulo",
                "Triângulo", "Círculo", "Triângulo", "Quadrado", "Triângulo", "Círculo", "Triângulo", "Quadrado", "Círculo", "Triângulo",
                "Triângulo", "Quadrado", "Círculo", "?", "?"};

        for (int i = 0; i < territorios.length; i++) {
            baralho.add(new Carta(territorios[i], formas[i]));
        }
    }

    static List<Carta> distribuiCarta(int qtdPlayers){
        List<Carta> cartasJogador = new ArrayList<>();
        int qtdCartas = baralho.size()/qtdPlayers;
        for(int i = 0; i < qtdCartas; i++){
            cartasJogador.add(comprarCarta());
        }
        return cartasJogador;
    }

    void shuf(){
        Collections.shuffle(baralho);
    }

    static Carta comprarCarta() {
        return baralho.remove(0);
    }

    static void addBaralho(Carta carta) {
        baralho.add(carta);
    }

    int size() {
        return baralho.size();
    }

    Carta get(int i) {
        return baralho.get(i);
    }
}
