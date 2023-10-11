package model;

import java.util.ArrayList;
import java.util.List;

public class Continente {
    String nome;
    List<Territorio> territorios;
    Jogador jogadorDono;  // Pode ser nulo se o continente não tiver dono

    Continente(String nome) {
        this.nome = nome;
        this.territorios = new ArrayList<>();
        this.jogadorDono = null;  // Inicialmente, o continente não tem dono
    }

    String getNome() {
        return nome;
    }

    void adicionarTerritorio(Territorio territorio) {
        territorios.add(territorio);
    }

    List<Territorio> getTerritorios() {
        return territorios;
    }

    Jogador getJogadorDono() {
        return jogadorDono;
    }

    void setJogadorDono(Jogador jogadorDono) {
        this.jogadorDono = jogadorDono;
    }

   
}
