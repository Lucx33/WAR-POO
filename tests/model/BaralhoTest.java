package model;

import org.junit.*; 
import static org.junit.Assert.assertEquals;

public class BaralhoTest {

    @Test
    public void testCriarBaralho() {
        Baralho baralho = new Baralho();
        baralho.criaBaralho();
        assertEquals(51, baralho.size());

        Carta primeira = baralho.get(0);
        assertEquals("africadosul", primeira.getTerritorio());
        assertEquals("Triângulo", primeira.getFormaGeometrica());

        Carta ultima = baralho.get(50);
        assertEquals("perth", ultima.getTerritorio());
        assertEquals("Círculo", ultima.getFormaGeometrica());
    }

    @Test
    public void testAddCoringa() {
        Baralho baralho = new Baralho();
        baralho.criaBaralho();
        assertEquals(51, baralho.size());

        Carta primeira = baralho.get(0);
        assertEquals("africadosul", primeira.getTerritorio());
        assertEquals("Triângulo", primeira.getFormaGeometrica());

        baralho.addCoringa();

        Carta ultima = baralho.get(52);
        assertEquals("c", ultima.getTerritorio());
        assertEquals("?", ultima.getFormaGeometrica());
    }

    @Test
    public void testEmbaralhar() {
        Baralho baralho = new Baralho();
        baralho.criaBaralho();
        baralho.addCoringa();
        assertEquals(53, baralho.size());
    }

    @Test
    public void testAddBaralho() {
        Baralho baralho = new Baralho();
        baralho.criaBaralho();
        Carta carta = baralho.comprarCarta();

        assertEquals("africadosul", carta.getTerritorio());
        assertEquals("Triângulo", carta.getFormaGeometrica());

        assertEquals(50, baralho.size());

        baralho.addBaralho(carta);

        assertEquals(51, baralho.size());

        Carta ultima = baralho.get(50);
        assertEquals("africadosul", ultima.getTerritorio());
        assertEquals("Triângulo", ultima.getFormaGeometrica());
    }

    @Test
    public void testImprimeBaralho(){
        Baralho baralho = new Baralho();
        baralho.criaBaralho();
        baralho.imprimeBaralho();
        System.out.println("");
        baralho.imprimeBaralho();
    }
}
