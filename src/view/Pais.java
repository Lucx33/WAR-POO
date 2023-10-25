package view;

import java.awt.*;
import java.awt.geom.*;

class Pais {
    String nome;
    int x, y;
    static final int RAIO = 30;
    Shape circulo;
    int tropa = 0;
    int corDono = 0;

    Pais(String nome, int x, int y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.tropa = 1;
        this.circulo = new Ellipse2D.Double(x - (RAIO / 2), y -(RAIO / 2), RAIO, RAIO);
    }

    void desenhar(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        if(nome.equals("Romenia")) {
            drawOutlinedText(g2d, nome, x - 4 * nome.length(), y + 28);
        } else {
            drawOutlinedText(g2d, nome, x - 4 * nome.length(), y -20);
        }

        // For debugging purposes: draw the circle
        g2d.setColor(Color.BLACK); // Black color
        g2d.fill(circulo);
        g2d.draw(circulo);

        g2d.setColor(Color.WHITE); // Text color
        g2d.setFont(new Font("Arial", Font.BOLD, 12)); // Font and size
        drawOutlinedText(g2d, String.valueOf(tropa), x - 4 , y + 4); // Adjust as necessary
    }

    // Helper method for drawing outlined text
    private void drawOutlinedText(Graphics2D g2d, String text, int x, int y) {
        g2d.setColor(Color.BLACK);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                g2d.drawString(text, x + i, y + j);
            }
        }

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);
    }

    boolean contains(int x, int y) {
        return circulo.contains(x, y);
    }
}
