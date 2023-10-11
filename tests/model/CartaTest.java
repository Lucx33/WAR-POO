package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;

public class CartaTest {
	@Test
	public void testCartaConst(){
		Carta carta = new Carta("riodejaneiro", "estrela");
		assertEquals("riodejaneiro", carta.getTerritorio());
		assertEquals("estrela", carta.getFormaGeometrica());
	}
	@Test
	public void testCriarBaralho() {
		// Cria um baralho
		List<Carta> baralho = Carta.criarBaralho();
		// Verifica se o baralho tem 53 cartas
		assertEquals(53, baralho.size());

		// Verifica se a primeira carta é a esperada (africadosul, Triângulo)
		Carta primeira = baralho.get(0);
		assertEquals("africadosul", primeira.getTerritorio());
		assertEquals("Triângulo", primeira.getFormaGeometrica());

		// Verifica se a última carta é a esperada (c, ?)
		Carta ultima = baralho.get(52);
		assertEquals("c", ultima.getTerritorio());
		assertEquals("?", ultima.getFormaGeometrica());
	}
	@Test
	public void testEmbaralhar() {
		// Cria um baralho
		List<Carta> baralho = Carta.criarBaralho();

		// Embaralha o baralho
		List<Carta> baralhoEmbaralhado = Carta.shuf(baralho);

		// Verifica se o baralho tem 53 cartas
		assertEquals(53, baralhoEmbaralhado.size());

		// Verifica se a primeira carta é diferente da "esperada" (africadosul)
		Carta primeira = baralhoEmbaralhado.get(0);
		assertNotEquals("africadosul", primeira.getTerritorio());
	}

	@Test
	public void testComprarCarta() {
		// Cria um baralho
		List<Carta> baralho = Carta.criarBaralho();

		// Compra uma carta
		Carta carta = Carta.comprarCarta(baralho);

		// Verifica se a carta comprada é a esperada (africadosul, Triângulo)
		assertEquals("africadosul", carta.getTerritorio());
		assertEquals("Triângulo", carta.getFormaGeometrica());

		// Verifica se o baralho tem 52 cartas
		assertEquals(52, baralho.size());
	}

	@Test
	public void testAddBaralho() {
		// Cria um baralho
		List<Carta> baralho = Carta.criarBaralho();

		// Compra uma carta
		Carta carta = Carta.comprarCarta(baralho);


		// Verifica se a carta comprada é a esperada (africadosul, Triângulo)
        assertEquals("africadosul", carta.getTerritorio());
		assertEquals("Triângulo", carta.getFormaGeometrica());

		// Verifica se o baralho tem 52 cartas
		assertEquals(52, baralho.size());

		// Adiciona a carta ao baralho
		Carta.addBaralho(baralho, carta);

		// Verifica se o baralho tem 53 cartas
		assertEquals(53, baralho.size());

		// Verifica se a última carta é a esperada (africadosul, Triângulo)
		Carta ultima = baralho.get(52);
		assertEquals("africadosul", ultima.getTerritorio());
		assertEquals("Triângulo", ultima.getFormaGeometrica());
	}

	@Test
	public void testVerificaCartas() {
		// Cria um baralho
		List<Carta> baralho = Carta.criarBaralho();

		// Adiciona 3 cartas
		Carta carta1 = new Carta("a", "Triângulo");
		Carta carta2 = new Carta("b", "Quadrado");
		Carta carta3 = new Carta("c", "Círculo");


		// Cria uma lista de cartas para jogador1
		List<Carta> cartasJogador1 = new ArrayList<>();

		// Adiciona as cartas compradas à lista
		cartasJogador1.add(carta1);
		cartasJogador1.add(carta2);
		cartasJogador1.add(carta3);

		// Verifica se a lista de cartas é válida
		assertTrue(Carta.verificaCartas(cartasJogador1));

		// Cria uma lista de cartas para jogador2
		List<Carta> cartasJogador2 = new ArrayList<>();

		// Adiciona 3 cartas
		Carta carta4 = new Carta("d", "Triângulo");
		Carta carta5 = new Carta("e", "Triângulo");
		Carta carta6 = new Carta("f", "Triângulo");

		// Adiciona as cartas compradas à lista
		cartasJogador2.add(carta4);
		cartasJogador2.add(carta5);
		cartasJogador2.add(carta6);

		// Verifica se a lista de cartas é válida
		assertTrue(Carta.verificaCartas(cartasJogador2));

		// Cria uma lista de cartas para jogador3
		List<Carta> cartasJogador3 = new ArrayList<>();

		// Adiciona 3 cartas
		Carta carta7 = new Carta("g", "Quadrado");
		Carta carta8 = new Carta("h", "Quadrado");
		Carta carta9 = new Carta("i", "Quadrado");

		// Adiciona as cartas compradas à lista
		cartasJogador3.add(carta7);
		cartasJogador3.add(carta8);
		cartasJogador3.add(carta9);

		// Verifica se a lista de cartas é válida
		assertTrue(Carta.verificaCartas(cartasJogador3));

		// Cria uma lista de cartas para jogador4
		List<Carta> cartasJogador4 = new ArrayList<>();

		// Adiciona 3 cartas
		Carta carta10 = new Carta("j", "Círculo");
		Carta carta11 = new Carta("k", "Círculo");
		Carta carta12 = new Carta("l", "Círculo");

		// Adiciona as cartas compradas à lista
		cartasJogador4.add(carta10);
		cartasJogador4.add(carta11);
		cartasJogador4.add(carta12);

		// Verifica se a lista de cartas é válida
		assertTrue(Carta.verificaCartas(cartasJogador4));

		// Cria uma lista de cartas para jogador5
		List<Carta> cartasJogador5 = new ArrayList<>();

		// Adiciona 3 cartas
		Carta carta13 = new Carta("m", "Círculo");
		Carta carta14 = new Carta("n", "Triângulo");
		Carta carta15 = new Carta("o", "Círculo");

		// Adiciona as cartas compradas à lista
		cartasJogador5.add(carta13);
		cartasJogador5.add(carta14);
		cartasJogador5.add(carta15);

		// Verifica se a lista de cartas é válida
		assertFalse(Carta.verificaCartas(cartasJogador5));

	}

}
