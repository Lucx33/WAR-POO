package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class ApiModelTest{
	
	//Teste 1 -----------------------------------
    @Test
    public void test1(){
        System.out.println("Teste 1");
        Jogador jogador1 = new Jogador("jogador1","Azul");
        Jogador jogador2 = new Jogador("jogador2","Amarelo");
        Jogador jogador3 = new Jogador("jogador3","Verde");
        Jogador jogador4 = new Jogador("jogador4","Branco");
        Jogador jogador5 = new Jogador("jogador5","Preto");

        List<Integer> ordem = jogador1.sortearOrdemJogo(5);
        jogador1.setordemjogo(ordem.get(0));
        jogador2.setordemjogo(ordem.get(1));
        jogador3.setordemjogo(ordem.get(2));
        jogador4.setordemjogo(ordem.get(3));
        jogador5.setordemjogo(ordem.get(4));

        System.out.println(jogador1.getordemjogo());
        System.out.println(jogador2.getordemjogo());
        System.out.println(jogador3.getordemjogo());
        System.out.println(jogador4.getordemjogo());
        System.out.println(jogador5.getordemjogo());

    }
    
	//Teste 2 -----------------------------------

    @Test
    public void test2(){
        System.out.println("Teste 2");
        Jogador jogador = new Jogador("jogador1","Azul");
        List<Objetivo> objetivos = Objetivo.criarObjetivos();
        jogador.setObjetivo(objetivos.get(0));

        System.out.println(jogador.getObjetivo().getDescricao());

    }
    
	//Teste 3 -----------------------------------

    @Test
    public void test3(){
        System.out.println("Teste 3");
// Cria o baralho e embaralha
        Baralho baralho = new Baralho();
        baralho.shuf();

        // Cria os objetivos e embaralha
        List<Objetivo> objetivos = Objetivo.criarObjetivos();
        Collections.shuffle(objetivos);

        // Cria o tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro();

        // Cria os jogadoresList
        List<Jogador> jogadoresList = new ArrayList<>();

        // Cria os jogadoresList e distribui os objetivos e cartas (terrtórios iniciais)
        for(int i = 0; i < 5; i++){
            Jogador jogador = new Jogador("Jogador " + i, "Cor " + i);
            jogadoresList.add(jogador);
            jogador.setObjetivo(objetivos.get(i));
            jogador.setCartas(Baralho.distribuiCarta(5));
            jogador.setExercitosIni();
        }
        assertEquals(5, jogadoresList.size());

        for(Jogador jogador : jogadoresList){
            for(Territorio territorio : jogador.getTerritorios()){
                System.out.println(jogador.getNome() + " | " + territorio.getNome() + " | " + territorio.getQtdExercito());
            }
        }
       

    }
    
    //Teste 4 -----------------------------------------
    @Test
    public void testReceberExercitosComBaseEmTerritorios() {
        Jogador jogador = new Jogador("Jogador1", "Azul");
        Territorio territorio1 = new Territorio("Territorio1");
        Territorio territorio2 = new Territorio("Territorio2");
        Territorio territorio3 = new Territorio("Territorio3");

        jogador.addTerritorio(territorio1);
        jogador.addTerritorio(territorio2);
        jogador.addTerritorio(territorio3);

        jogador.receberExercitos();

        int exercitosEsperados = jogador.getTerritorios().size() / 2;

        assertEquals(exercitosEsperados, jogador.getExercitos());
    
}
    
    
	//Teste 5 -------------------------------------

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

    // Teste 6 ---------------------
    @Test
    public void test6(){
        System.out.println("Teste 6");
        Jogador jogador = new Jogador("jogador1","Azul");
        Baralho baralho = new Baralho();
        Baralho.addCoringa();
        baralho.shuf();

        System.out.println("Exercitos antes da troca: " + jogador.getExercitos());

        for(int i = 0; i < 5; i++){
            jogador.addCarta(Baralho.comprarCarta());
            System.out.println("Carta " + (1+i) + ": " + jogador.getCartas().get(i).getTerritorio() + " | " + jogador.getCartas().get(i).getFormaGeometrica());
        }
        jogador.trocarCartas();
        System.out.println("Exercitos depois da troca: " + jogador.getExercitos());
    }
}