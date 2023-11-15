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
        int qtdAtaque = 3;
        int qtdDefesa = 2;
        assertEquals(5, dado.lancamentoDados(qtdAtaque, qtdDefesa).size());
    }
}
