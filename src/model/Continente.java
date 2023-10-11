package model;

import java.util.ArrayList;
import java.util.List;

public class Continente {
    private String nome;
    private List<Territorio> territorios;
    private Jogador jogadorDono;  // Pode ser nulo se o continente não tiver dono

    public Continente(String nome) {
        this.nome = nome;
        this.territorios = new ArrayList<>();
        this.jogadorDono = null;  // Inicialmente, o continente não tem dono
    }

    public String getNome() {
        return nome;
    }

    public void adicionarTerritorio(Territorio territorio) {
        territorios.add(territorio);
    }

    public List<Territorio> getTerritorios() {
        return territorios;
    }

    public Jogador getJogadorDono() {
        return jogadorDono;
    }

    public void setJogadorDono(Jogador jogadorDono) {
        this.jogadorDono = jogadorDono;
    }
    
    public void verificarDonoDoContinente(Continente continente, Jogador jogadorDesejado) {
        boolean todosTerritoriosPertencemAoJogador = true;
        
        for (Territorio territorio : continente.getTerritorios()) {
            if (territorio.getIdJogadorDono() != jogadorDesejado.getIdJogador()) {
                todosTerritoriosPertencemAoJogador = false;
                break;
            }
        }
        
        if (todosTerritoriosPertencemAoJogador) {
            continente.setJogadorDono(jogadorDesejado);
        }
    }

   
}
