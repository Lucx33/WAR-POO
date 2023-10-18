package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class DadoTest {
    private Dado dado;

    @Before
    public void setUp() {
        dado = new Dado();
    }

    @Test
    public void testRolar() {
        int res = dado.rolar();
        assertTrue(res >= 1 && res <= 6);
    }

    @Test
    public void testDadosAtaque() {
        int[] dadosAtaque = dado.DadosAtaque(3);
        assertEquals(3, dadosAtaque.length);
        for (int j : dadosAtaque) {
            assertTrue(j >= 1 && j <= 6);
        }
    }

    @Test
    public void testDadosDefesa() {
        int[] dadosDefesa = dado.DadosDefesa(3);
        assertEquals(3, dadosDefesa.length);
        for (int j : dadosDefesa) {
            assertTrue(j >= 1 && j <= 6);
        }
    }
}
