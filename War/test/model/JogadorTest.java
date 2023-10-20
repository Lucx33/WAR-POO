package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

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

  

}
