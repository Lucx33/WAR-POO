package model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TabuleiroTest {
    @Test
    public void testCriarTabuleiro() {
        // Cria um tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro();

        for (Continente continente : tabuleiro.continentes) {
            System.out.println("Continente: " + continente.getNome());
            for (Territorio territorio : continente.getTerritorios()) {
                System.out.println("\tTerritorio: " + territorio.getNome());
            }
        }

    }

    @Test
    public void imprimeTabuleiro() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.criaTabuleiro();
        tabuleiro.imprimeTabuleiro();
        assertTrue(true);
    }
}
