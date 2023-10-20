
package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ContinenteTest {

    private Continente continente;
    private Territorio territorio1;
    private Territorio territorio2;
    private Jogador jogador;

    @Before
    public void setUp() {
        continente = new Continente("Continente1");
        territorio1 = new Territorio("Territorio1");
        territorio2 = new Territorio("Territorio2");
        jogador = new Jogador("Jogador1", "Vermelho");
    }

    @Test
    public void testGetNomeContinente() {
        assertEquals("Continente1", continente.getNome());
    }

    @Test
    public void testAdicionarTerritorio() {
        continente.adicionarTerritorio(territorio1);
        continente.adicionarTerritorio(territorio2);
        assertTrue(continente.getTerritorios().contains(territorio1));
        assertTrue(continente.getTerritorios().contains(territorio2));
    }

    @Test
    public void testGetJogadorDono() {
        assertNull(continente.getJogadorDono()); // Deve ser nulo inicialmente
    }

    @Test
    public void testSetJogadorDono() {
        continente.setJogadorDono(jogador);
        assertEquals(jogador, continente.getJogadorDono());
    }
    
    @Test
    public void testBonusExercitosAmericaDoNorte() {
        Continente americaDoNorte = new Continente("America do Norte");
        assertEquals(5, americaDoNorte.getBonusExercitos("America do Norte"));
    }

    @Test
    public void testBonusExercitosAmericaDoSul() {
        Continente americaDoSul = new Continente("America do Sul");
        assertEquals(2, americaDoSul.getBonusExercitos("America do Sul"));
    }

    @Test
    public void testBonusExercitosAfrica() {
        Continente africa = new Continente("Africa");
        assertEquals(3, africa.getBonusExercitos("Africa"));
    }

    @Test
    public void testBonusExercitosEuropa() {
        Continente europa = new Continente("Europa");
        assertEquals(5, europa.getBonusExercitos("Europa"));
    }

    @Test
    public void testBonusExercitosAsia() {
        Continente asia = new Continente("Asia");
        assertEquals(7, asia.getBonusExercitos("Asia"));
    }

    @Test
    public void testBonusExercitosOceania() {
        Continente oceania = new Continente("Oceania");
        assertEquals(2, oceania.getBonusExercitos("Oceania"));
    }

    @Test
    public void testBonusExercitosDesconhecido() {
        Continente continenteDesconhecido = new Continente("Continente Desconhecido");
        assertEquals(0, continenteDesconhecido.getBonusExercitos("Continente Desconhecido"));
    }
    
    
}
