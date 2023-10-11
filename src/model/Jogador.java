package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Jogador {
    String nome;
    String cor;
    List<Carta> cartas;
    List<Territorio> territorios;
    Objetivo objetivo;
    int ordemjogo;

    Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.cartas = new ArrayList<>();
        this.territorios = new ArrayList<>();
        this.ordemjogo = -1; 
    }

    String getNome() {
        return nome;
    }

    String getCor() {
        return cor;
    }

    int getordemjogo() {
        return ordemjogo;
    }

    void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    void addCarta(Carta carta) {
        cartas.add(carta);
    }

    List<Carta> getCartas() {
        return cartas;
    }

    void addTerritorio(Territorio territorio) {
        territorios.add(territorio);
    }

    List<Territorio> getTerritorios() {
        return territorios;
    }

    void sortearordemjogo(int n_jogadores) {
        Random random = new Random();
        this.ordemjogo = random.nextInt(n_jogadores) + 1;
    }

    int getCorId(Jogador jogador){
        return switch (cor) {
            case "Azul" -> 1;
            case "Amarelo" -> 2;
            case "Branco" -> 3;
            case "Verde" -> 4;
            case "Preto" -> 5;
            case "Vermelho" -> 6;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "Jogador: " + nome + " | Cor: " + cor + " | Ordem de Jogada: " + ordemjogo;
    }
}
