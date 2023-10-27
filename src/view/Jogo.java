package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Jogo extends JPanel {
    BufferedImage tabuleiro;
    BufferedImage oceano;
    private DesenhaTabuleiro desenhaTabuleiro;

    public Jogo(List<String> playerNames, List<String> playerColors) {
        try {
            desenhaTabuleiro = new DesenhaTabuleiro("oceano3.jpg", "war_tabuleiro_mapa copy.png");
            adicionarPaises();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("War");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.add(this);
        frame.setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("Coordenadas: x = " + x + ", y = " + y);
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Obtém o contexto gráfico do JFrame
        Graphics2D g2d = (Graphics2D) g;

        // Chame o método para desenhar o tabuleiro
        desenhaTabuleiro.desenharFundo(g2d);

        desenhaTabuleiro.desenharPaises(g2d);

    }


    public void adicionarPaises() {
        // America do Sul
        desenhaTabuleiro.adicionarPais("Brasil", 391, 367);
        desenhaTabuleiro.adicionarPais("Argentina", 378, 462);
        desenhaTabuleiro.adicionarPais("Peru", 333, 401);
        desenhaTabuleiro.adicionarPais("Venezuela", 302, 337);

        // America do Norte
        desenhaTabuleiro.adicionarPais("Mexico", 246, 264);
        desenhaTabuleiro.adicionarPais("California", 215, 164);
        desenhaTabuleiro.adicionarPais("Texas", 279, 154);
        desenhaTabuleiro.adicionarPais("NovaYork", 354, 142);
        desenhaTabuleiro.adicionarPais("Vancouver", 256, 96);
        desenhaTabuleiro.adicionarPais("Quebec", 394, 91);
        desenhaTabuleiro.adicionarPais("Alasca", 195, 44);
        desenhaTabuleiro.adicionarPais("Calgary", 303, 47);
        desenhaTabuleiro.adicionarPais("Groelandia", 444, 40);

        //Africa
        desenhaTabuleiro.adicionarPais("Argelia", 562, 273);
        desenhaTabuleiro.adicionarPais("Nigeria", 608, 346);
        desenhaTabuleiro.adicionarPais("Egito", 670, 293);
        desenhaTabuleiro.adicionarPais("Somalia", 722, 393);
        desenhaTabuleiro.adicionarPais("Angola", 655, 412);
        desenhaTabuleiro.adicionarPais("AfricaDoSul", 677, 480);

        //Europa
        desenhaTabuleiro.adicionarPais("Espanha", 533, 191);
        desenhaTabuleiro.adicionarPais("Franca", 587, 152);
        desenhaTabuleiro.adicionarPais("Italia", 643, 168);
        desenhaTabuleiro.adicionarPais("Polonia", 677, 97);
        desenhaTabuleiro.adicionarPais("Romenia", 689, 178);
        desenhaTabuleiro.adicionarPais("Ucrania", 707, 140);
        desenhaTabuleiro.adicionarPais("Suecia", 633, 44);
        desenhaTabuleiro.adicionarPais("ReinoUnido", 558, 79);

        //Oceania
        desenhaTabuleiro.adicionarPais("Australia", 976, 505);
        desenhaTabuleiro.adicionarPais("NovaZelandia", 1034, 544);
        desenhaTabuleiro.adicionarPais("Perth", 903, 505);
        desenhaTabuleiro.adicionarPais("Indonesia", 995, 407);

        //Asia
        desenhaTabuleiro.adicionarPais("ArabiaSaudita", 771, 316);
        desenhaTabuleiro.adicionarPais("Jordania", 722, 253);
        desenhaTabuleiro.adicionarPais("Iraque", 773, 250);
        desenhaTabuleiro.adicionarPais("Siria", 749, 190);
        desenhaTabuleiro.adicionarPais("Turquia", 827, 148);
        desenhaTabuleiro.adicionarPais("Paquistao", 851, 217);
        desenhaTabuleiro.adicionarPais("Ira", 817, 259);
        desenhaTabuleiro.adicionarPais("China", 895, 199);
        desenhaTabuleiro.adicionarPais("India", 898, 297);
        desenhaTabuleiro.adicionarPais("Bangladesh", 946, 291);
        desenhaTabuleiro.adicionarPais("Tailandia", 1001, 298);
        desenhaTabuleiro.adicionarPais("CoreiaDoNorte", 961, 214);
        desenhaTabuleiro.adicionarPais("CoreiaDoSul", 1001, 237);
        desenhaTabuleiro.adicionarPais("Japao", 1050, 173);
        desenhaTabuleiro.adicionarPais("Mongolia", 963, 162);
        desenhaTabuleiro.adicionarPais("Cazaquistao", 925, 123);
        desenhaTabuleiro.adicionarPais("Russia", 883, 57);
        desenhaTabuleiro.adicionarPais("Siberia", 1000, 55);
        desenhaTabuleiro.adicionarPais("Estonia", 768, 40);
        desenhaTabuleiro.adicionarPais("Letonia", 762, 103);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Jogo(null, null);
            }
        });
    }

    public void setCorDono(List<String> territorios, String Cor) {
        // Iterar por todos os territórios fornecidos
        System.out.println("setCorDono: " + Cor);
        for (String nomeTerritorio : territorios) {
            Pais pais = desenhaTabuleiro.getPais(nomeTerritorio);
            if (pais != null) {
                System.out.println("setCorDono: " + nomeTerritorio);
                pais.setCor(Cor);
            }
        }
    }




}