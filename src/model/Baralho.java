package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Baralho {
    List<Carta> baralho;
    static int qtdTroca;

    Baralho(){
    }

    Baralho criaBaralho() {
        qtdTroca = 4;
        baralho = new ArrayList<>();
        String[] territorios = {"africadosul", "angola", "argelia", "egito", "nigeria", "somalia", "alasca", "calgary",
                "california", "groelandia", "mexico", "novayork", "quebec", "texas", "vancouver", "arabiasaudita", "bangladesh", "cazaquistao",
                "china", "coreiadonorte","coreiadosul", "estonia", "india", "ira", "iraque", "japao", "jordania", "letonia",
                "mongolia", "paquistao", "russia", "siberia", "siria", "tailandia", "turquia", "argentina", "brasil", "peru",
                "venezuela", "espanha", "franca", "italia", "polonia", "reinounido", "romenia", "suecia", "ucrania", "australia",
                "indonesia", "novazelandia", "perth"};
        String[] formas = {"Triângulo", "Quadrado", "Círculo", "Triângulo", "Círculo", "Quadrado", "Triângulo", "Círculo",
                "Quadrado", "Círculo", "Quadrado", "Quadrado", "Círculo", "Triângulo", "Triângulo", "Círculo", "Círculo", "Círculo",
                "Quadrado", "Quadrado", "Triângulo", "Círculo", "Triângulo", "Quadrado", "Triângulo", "Círculo", "Quadrado", "Quadrado",
                "Triângulo", "Círculo", "Triângulo", "Quadrado", "Quadrado", "Triângulo", "Triângulo", "Quadrado", "Círculo", "Triângulo",
                "Triângulo", "Círculo", "Triângulo", "Quadrado", "Triângulo", "Círculo", "Triângulo", "Quadrado", "Círculo", "Triângulo",
                "Triângulo", "Quadrado", "Círculo"};

        for (int i = 0; i < territorios.length; i++) {
            baralho.add(new Carta(territorios[i], formas[i]));
        }
        return this;
    }

    List<Carta> distribuiCarta(int qtdPlayers){
        List<Carta> cartasJogador = new ArrayList<>();
        int qtdCartas = 51/qtdPlayers;
        for(int i = 0; i < qtdCartas; i++){
            cartasJogador.add(comprarCarta());
        }
        return cartasJogador;
    }



    void shuf(){
        Collections.shuffle(baralho);
    }

    Carta comprarCarta() {
        return this.baralho.remove(0);
    }

    void addBaralho(Carta carta) {
        this.baralho.add(carta);
    }

    void addCoringa() {
        this.baralho.add(new Carta("c", "?"));
        this.baralho.add(new Carta("c", "?"));
    }

    int size() {
        return baralho.size();
    }

    Carta get(int i) {
        return baralho.get(i);
    }

    static void addQtdTroca(int i){
        qtdTroca += i;
    }

    void imprimeBaralho(){
        for (Carta carta : baralho) {
            System.out.println(carta.getTerritorio() + " " + carta.getFormaGeometrica());
        }
    }

    Carta getCarta(String territorio){
        for (Carta carta : baralho) {
            if(carta.getTerritorio().equals(territorio)){
                return carta;
            }
        }
        return null;
    }
}