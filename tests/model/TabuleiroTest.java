package model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
}
