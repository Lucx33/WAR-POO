package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;
public class JogadorTest {
    @Test
    public void testJogadorConst(){
        Jogador jogador = new Jogador("jogador1","Azul");
        assertEquals("jogador1", jogador.getNome());
        assertEquals(0, jogador.getExercitos());
    }
    @Test
    public void testSetObjetivo(){
        Jogador jogador = new Jogador("jogador1","Azul");
        Objetivo objetivo = new Objetivo(1, "Descricao1");
        jogador.setObjetivo(objetivo);
        assertEquals(objetivo, jogador.objetivo);
    }
    @Test
    public void testAddCarta(){
        Jogador jogador = new Jogador("jogador1","Azul");
        Carta carta = new Carta("africadosul", "Triângulo");
        jogador.addCarta(carta);
        assertEquals(carta, jogador.cartas.get(0));
    }
    @Test
    public void testGetCartas(){
        Jogador jogador = new Jogador("jogador1","Azul");
        Carta carta = new Carta("africadosul", "Triângulo");
        jogador.addCarta(carta);
        assertEquals(carta, jogador.getCartas().get(0));
    }
    @Test
    public void testAddTerritorio(){
        Jogador jogador = new Jogador("jogador1","Azul");
        assertEquals(1, jogador.getIdJogador());
        Territorio territorio = new Territorio("africadosul", 1,1);
        jogador.addTerritorio(territorio);
        assertEquals(territorio, jogador.territorios.get(0));
    }
    @Test
    public void testGetTerritorios(){
        Jogador jogador = new Jogador("jogador1","Azul");
        Territorio territorio = new Territorio("africadosul", 1,1);
        jogador.addTerritorio(territorio);
        assertEquals(territorio, jogador.getTerritorios().get(0));
    }
    @Test
    public void testGetTerritoriosString(){
        Jogador jogador = new Jogador("jogador1","Azul");
        Territorio territorio = new Territorio("africadosul", 1,1);
        jogador.addTerritorio(territorio);
        assertEquals("africadosul", jogador.getTerritoriosString().get(0));
    }
    @Test
    public void testGetContinentes(){
        Jogador jogador = new Jogador("jogador1","Azul");
        Continente continente = new Continente("Africa");
        jogador.addContinente(continente);
        assertEquals(continente, jogador.getContinentes().get(0));
    }
    @Test
    public void testGetCor(){
        Jogador jogador = new Jogador("jogador1","Azul");
        assertEquals("Azul", jogador.getCor());
    }
    @Test
    public void testGetExercitos(){
        Jogador jogador = new Jogador("jogador1","Azul");
        assertEquals(0, jogador.getExercitos());
    }
    @Test
    public void testGetordemjogo(){
        Jogador jogador = new Jogador("jogador1","Azul");
        assertEquals(-1, jogador.getordemjogo());
    }
    @Test
    public void testGetIdJogador(){
        Jogador jogador1 = new Jogador("jogador1","Azul");
        assertEquals(1, jogador1.getIdJogador());
        Jogador jogador2 = new Jogador("jogador2","Amarelo");
        assertEquals(2, jogador2.getIdJogador());
    }
    @Test
    public void testSetExercitos(){
        Jogador jogador = new Jogador("jogador1","Azul");
        jogador.addExercitos(5);
        assertEquals(5, jogador.getExercitos());
    }
    @Test
    public void testSortearordemjogo(){
        Jogador jogador = new Jogador("jogador1","Azul");
        jogador.sortearordemjogo(5);
        assertTrue(jogador.getordemjogo() >= 1 && jogador.getordemjogo() <= 5);
    }
    @Test
    public void testGetCorId(){
        Jogador jogador = new Jogador("jogador1","Azul");
        assertEquals(1, jogador.getCorId(jogador));
    }
    @Test
    public void testAddContinente(){
        Jogador jogador = new Jogador("jogador1","Azul");
        Continente continente = new Continente("Africa");
        jogador.addContinente(continente);
        assertEquals(continente, jogador.continentes.get(0));
    }
}

