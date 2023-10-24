package view;

import java.awt.*;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("War");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.add(this);
        frame.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Obtém o contexto gráfico do JFrame
        Graphics2D g2d = (Graphics2D) g;

        // Chame o método para desenhar o tabuleiro
        desenhaTabuleiro.desenharFundo(g2d);
        //desenhaTabuleiro.desenharBrasil(g2d, 360, 350);
        desenhaTabuleiro.adicionarPais("Brasil", 375, 335);
        desenhaTabuleiro.adicionarPais("Argentina", 353, 455);
        desenhaTabuleiro.desenharPaises(g2d);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Jogo(null, null);
            }
        });
    }



}