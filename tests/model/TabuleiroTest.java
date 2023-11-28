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
    public void testFazFronteira() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.criaTabuleiro();

        assertTrue(tabuleiro.fazFronteira("brasil", "argentina"));
        assertFalse(tabuleiro.fazFronteira("brasil", "franca"));
    }

    @Test
    public void testAdicionaExercito() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.criaTabuleiro();

        tabuleiro.adicionaExercito("brasil", 5);

        Territorio brasil = Tabuleiro.buscaTerritorio("brasil");
        assertEquals(5, brasil.getQtdExercito());
    }


    @Test
    public void testVerificaControleContinente() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.criaTabuleiro();

        List<String> territoriosControlados = List.of("alasca", "calgary", "california", "groelandia", "mexico", "novayork", "quebec", "texas", "vancouver");

        assertTrue(tabuleiro.verificaTerritoriosContinente(territoriosControlados, "calgary"));
    }


    @Test
    public void testSimulaAtaque() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.criaTabuleiro();

        Dado dado = new Dado();
        List<Integer> dadosAtaque = List.of(6, 5, 4);
        List<Integer> dadosDefesa = List.of(3, 2, 1);

        tabuleiro.simulaAtaque("brasil", "argentina", dado, dadosAtaque, dadosDefesa);

        Territorio brasil = Tabuleiro.buscaTerritorio("brasil");
        Territorio argentina = Tabuleiro.buscaTerritorio("argentina");

        assertEquals(brasil.getIdJogadorDono(), argentina.getIdJogadorDono());
    }


}
