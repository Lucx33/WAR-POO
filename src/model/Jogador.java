package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Jogador {
    private String nome;
    private String cor;
    private List<Carta> cartas;
    private List<Territorio> territorios;
    private Objetivo objetivo;
    private int ordemjogo;

    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.cartas = new ArrayList<>();
        this.territorios = new ArrayList<>();
        this.ordemjogo = -1; 
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getordemjogo() {
        return ordemjogo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public void addCarta(Carta carta) {
        cartas.add(carta);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void addTerritorio(Territorio territorio) {
        territorios.add(territorio);
    }

    public List<Territorio> getTerritorios() {
        return territorios;
    }

    public void sortearordemjogo(int n_jogadores) {
        Random random = new Random();
        this.ordemjogo = random.nextInt(n_jogadores) + 1;
    }

    @Override
    public String toString() {
        return "Jogador: " + nome + " | Cor: " + cor + " | Ordem de Jogada: " + ordemjogo;
    }
}
