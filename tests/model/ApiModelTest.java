package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class ApiModelTest{
    @Test
    public void test1(){
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
    @Test
    public void test2(){
        Jogador jogador = new Jogador("jogador1","Azul");

    }
}
