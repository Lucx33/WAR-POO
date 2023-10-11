package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

public class CartaTest {
	@Before
	public void before() {
		System.out.println("Iniciando os testes");	
	}
	@Test
	public void testCriarBaralho() {
		List<Carta> baralho = Carta.criarBaralho();
		assertEquals(53, baralho.size());
		Carta primeira = baralho.get(0);
		Carta ultima = baralho.get(52);
		assertEquals("africadosul", primeira.getTerritorio());
		assertEquals("Triângulo", primeira.getFormaGeometrica());
		assertEquals("c", ultima.getTerritorio());
		assertEquals("?", ultima.getFormaGeometrica());
	}

}
