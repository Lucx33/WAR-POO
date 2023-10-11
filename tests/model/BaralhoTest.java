package model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BaralhoTest {
    @Test
    public void testCriarBaralho() {
        // Cria um baralho
        Baralho baralho = new Baralho();
        // Verifica se o baralho tem 53 cartas
        assertEquals(53, baralho.size());

        // Verifica se a primeira carta é a esperada (africadosul, Triângulo)
        Carta primeira = baralho.get(0);
        assertEquals("africadosul", primeira.getTerritorio());
        assertEquals("Triângulo", primeira.getFormaGeometrica());

        // Verifica se a última carta é a esperada (c, ?)
        Carta ultima = baralho.get(52);
        assertEquals("c", ultima.getTerritorio());
        assertEquals("?", ultima.getFormaGeometrica());
    }
    @Test
    public void testEmbaralhar() {
        // Cria um baralho
        Baralho baralho = new Baralho();

        // Embaralha o baralho
        baralho.shuf();

        // Verifica se o baralho tem 53 cartas
        assertEquals(53, baralho.size());

        // Verifica se a primeira carta é diferente da "esperada" (africadosul)
        Carta primeira = baralho.get(0);
        assertNotEquals("africadosul", primeira.getTerritorio());
    }

    @Test
    public void testAddBaralho() {
        // Cria um baralho
        Baralho baralho = new Baralho();

        // Compra uma carta
        Carta carta = Baralho.comprarCarta();


        // Verifica se a carta comprada é a esperada (africadosul, Triângulo)
        assertEquals("africadosul", carta.getTerritorio());
        assertEquals("Triângulo", carta.getFormaGeometrica());

        // Verifica se o baralho tem 52 cartas
        assertEquals(52, baralho.size());

        // Adiciona a carta ao baralho
        Baralho.addBaralho(carta);

        // Verifica se o baralho tem 53 cartas
        assertEquals(53, baralho.size());

        // Verifica se a última carta é a esperada (africadosul, Triângulo)
        Carta ultima = baralho.get(52);
        assertEquals("africadosul", ultima.getTerritorio());
        assertEquals("Triângulo", ultima.getFormaGeometrica());
    }
}
