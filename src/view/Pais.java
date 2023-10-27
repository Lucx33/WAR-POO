package view;

import java.awt.*;
import java.awt.geom.*;

class Pais {
    String nome;
    int x, y;
    static final int RAIO = 30;
    Shape circulo;
    int tropa = 0;
    Color corDono;

    Pais(String nome, int x, int y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.tropa = 1;
        this.circulo = new Ellipse2D.Double(x - (RAIO / 2), y -(RAIO / 2), RAIO, RAIO);
        this.corDono = Color.WHITE;
    }

    void desenhar(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        if(nome.equals("Romenia")) {
            drawOutlinedText(g2d, nome, x - 4 * nome.length(), y + 28);
        } else {
            drawOutlinedText(g2d, nome, x - 4 * nome.length(), y -20);
        }

        // Fill the circle with its color
        g2d.setColor(corDono);
        g2d.fill(circulo);

        // Set the color for the circle's outline
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3)); // Set the outline thickness
        g2d.draw(circulo); // Draw the circle's outline

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

    void setCor(String cor) {
        switch(cor.toLowerCase()) {
            case "vermelho":
                this.corDono = Color.RED;
                break;
            case "azul":
                this.corDono = Color.BLUE;
                break;
            case "verde":
                this.corDono = Color.GREEN;
                break;
            case "amarelo":
                this.corDono = Color.YELLOW;
                break;
            case "preto":
                this.corDono = Color.BLACK;
                break;
            case "branco":
                this.corDono = Color.WHITE;
                break;
        }
    }
}
