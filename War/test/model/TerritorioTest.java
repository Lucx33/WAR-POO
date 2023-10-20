package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class TerritorioTest {

    private Territorio territorio;
    private List<Territorio> fronteiras;

    @Before
    public void setUp() {
        territorio = new Territorio("Territorio1");
        fronteiras = new ArrayList<>();
    }

    @Test
    public void testGetNomeTerritorio() {
        assertEquals("Territorio1", territorio.getNome());
    }

    @Test
    public void testSetIdJogadorDono() {
        territorio.setIdJogadorDono(1);
        assertEquals(1, territorio.getIdJogadorDono());
    }

    @Test
    public void testSetQtdExercito() {
        territorio.setQtdExercito(10);
        assertEquals(10, territorio.getQtdExercito());
    }

   
}
