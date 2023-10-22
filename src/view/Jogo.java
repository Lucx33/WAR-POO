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

    public Jogo(List<String> playerNames, List<String> playerColors) {
        try {
            tabuleiro = ImageIO.read(new File("war_tabuleiro_mapa copy.png"));
            oceano = resizeImage("oceano3.jpg", 1200, 700);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("War");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.add(this);
        frame.setVisible(true);
    }

    BufferedImage resizeImage(String imagePath, int width, int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(imagePath));
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();
        return bufferedResizedImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(oceano, 0, 0, null);

        g2d.drawImage(tabuleiro, 100, -90, null);

        // Aqui você pode adicionar o código para desenhar o tabuleiro e outros elementos do jogo
    }

    public static void main(String args []) {
        new Jogo(null, null);
    }

}
