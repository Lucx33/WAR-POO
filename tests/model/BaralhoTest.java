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
        assertEquals(51, baralho.size());

        // Verifica se a primeira carta é a esperada (africadosul, Triângulo)
        Carta primeira = baralho.get(0);
        assertEquals("africadosul", primeira.getTerritorio());
        assertEquals("Triângulo", primeira.getFormaGeometrica());

        // Verifica se a última carta é a esperada (c, ?)
        Carta ultima = baralho.get(50);
        assertEquals("perth", ultima.getTerritorio());
        assertEquals("Círculo", ultima.getFormaGeometrica());
    }

    @Test
    public void testAddCoringa(){
        // Cria um baralho
        Baralho baralho = new Baralho();
        // Verifica se o baralho tem 51 cartas
        assertEquals(51, baralho.size());

        // Verifica se a primeira carta é a esperada (africadosul, Triângulo)
        Carta primeira = baralho.get(0);
        assertEquals("africadosul", primeira.getTerritorio());
        assertEquals("Triângulo", primeira.getFormaGeometrica());

        Baralho.addCoringa();

        // Verifica se a última carta é a esperada (c, ?)
        Carta ultima = baralho.get(52);
        assertEquals("c", ultima.getTerritorio());
        assertEquals("?", ultima.getFormaGeometrica());
    }

    @Test
    public void testEmbaralhar() {
        // Cria um baralho
        Baralho baralho = new Baralho();

        Baralho.addCoringa();

        // Verifica se o baralho tem 53 cartas
        assertEquals(53, baralho.size());
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
        assertEquals(50, baralho.size());

        // Adiciona a carta ao baralho
        Baralho.addBaralho(carta);

        // Verifica se o baralho tem 51 cartas
        assertEquals(51, baralho.size());

        // Verifica se a última carta é a esperada (africadosul, Triângulo)
        Carta ultima = baralho.get(50);
        assertEquals("africadosul", ultima.getTerritorio());
        assertEquals("Triângulo", ultima.getFormaGeometrica());
    }
}
