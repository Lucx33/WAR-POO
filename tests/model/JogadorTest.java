package model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JogadorTest {
    private Jogador jogador;

    @Before
    public void setUp() {
        jogador = new Jogador("jogador1", "Azul");
    }

    @Test
    public void testJogadorConst() {
        assertEquals("jogador1", jogador.getNome());
        assertEquals(0, jogador.getExercitos());
    }

    @Test
    public void testSetObjetivo() {
        Objetivo objetivo = new Objetivo(1, "Descricao1");
        jogador.setObjetivo(objetivo);
        assertEquals(objetivo, jogador.getObjetivo());
    }

    @Test
    public void testAddCarta() {
        Carta carta = new Carta("africadosul", "Triângulo");
        jogador.addCarta(carta);
        assertEquals(carta, jogador.getCartas().get(0));
    }

    @Test
    public void testAddTerritorio() {
        Territorio territorio = new Territorio("africadosul");
        jogador.addTerritorio(territorio);
        assertEquals(territorio, jogador.getTerritorios().get(0));
    }

    @Test
    public void testAddRemoveContinente() {
        Continente continente = new Continente("América do Sul");
        jogador.addContinente(continente);
        assertTrue(jogador.getContinentes().contains(continente));

        jogador.removeContinente(continente);
        assertFalse(jogador.getContinentes().contains(continente));
    }

    @Test
    public void testVerificaControleContinente() {
        Continente continente = new Continente("América do Norte");
        Territorio territorio1 = new Territorio("Alaska");
        Territorio territorio2 = new Territorio("México");
        continente.adicionarTerritorio(territorio1);
        continente.adicionarTerritorio(territorio2);

        jogador.addTerritorio(territorio1);
        jogador.addTerritorio(territorio2);

        assertTrue(jogador.verificaControleContinente(jogador, continente));
    }

    @Test
    public void testAddRemoveExercitos() {
        jogador.addExercitos(5);
        assertEquals(5, jogador.getExercitos());

        jogador.removeExercitos(2);
        assertEquals(3, jogador.getExercitos());
    }


    @Test
    public void testReceberExercitos() {
        jogador.addTerritorio(new Territorio("Brasil"));
        jogador.addTerritorio(new Territorio("Argentina"));
        jogador.addTerritorio(new Territorio("Chile"));
        jogador.addTerritorio(new Territorio("Peru"));
        jogador.receberExercitos();
        assertTrue(jogador.getExercitos() >= 2);
    }

    @Test
    public void testManipulacaoCartas() {
        Carta carta1 = new Carta("Brasil", "Círculo");
        Carta carta2 = new Carta("Argentina", "Quadrado");
        jogador.addCarta(carta1);
        jogador.addCarta(carta2);

        List<String> cartasNomes = jogador.getCartasString();
        assertTrue(cartasNomes.contains("Brasil"));
        assertTrue(cartasNomes.contains("Argentina"));
    }





}
