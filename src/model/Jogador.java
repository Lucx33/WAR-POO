package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Jogador {
    String nome;
    String cor;
    List<Carta> cartas;
    List<Territorio> territorios;
    List<Continente> continentes;
    Objetivo objetivo;
    int ordemjogo;
    int exercitos;
    int idJogador;

    Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.cartas = new ArrayList<>();
        this.territorios = new ArrayList<>();
        this.continentes = new ArrayList<>();
        this.ordemjogo = -1;
        this.exercitos = 0;
        this.objetivo = null;
        this.idJogador = getCorId(this);
    }

    String getNome() {
        return nome;
    }

    String getCor() { return cor; }

    int getExercitos() { return exercitos; }

    int getordemjogo() {
        return ordemjogo;
    }
    int getIdJogador() { return idJogador; }

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

    List<Territorio> getTerritorios() { return territorios; }
    List<Continente> getContinentes() { return continentes; }

    List<String> getTerritoriosString() {
        List<String> territoriosString = new ArrayList<>();
        for (Territorio territorio : territorios) {
            territoriosString.add(territorio.getNome());
        }
        return territoriosString;
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

    void addExercitos(int i) {
        this.exercitos += i;
    }

    void removeExercitos(int i) {
        this.exercitos -= i;
    }

    void addContinente(Continente continente) {
        continentes.add(continente);
    }
}
