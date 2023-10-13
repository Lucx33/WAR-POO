package model;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

public class DadoTest {
    @Test
    public void testRolar(){
        Dado dado = new Dado();
        int res = dado.rolar();
        assertTrue(res >= 1 && res <= 6);
    }
    @Test
    public void testDadosAtaque(){
        Dado dado = new Dado();
        int[] dadosAtaque = dado.DadosAtaque(3);
        assertEquals(3, dadosAtaque.length);
        for (int j : dadosAtaque) {
            assertTrue(j >= 1 && j <= 6);
        }
    }
    @Test
    public void testDadosDefesa(){
        Dado dado = new Dado();
        int[] dadosDefesa = dado.DadosDefesa(3);
        assertEquals(3, dadosDefesa.length);
        for (int j : dadosDefesa) {
            assertTrue(j >= 1 && j <= 6);
        }
    }


}
