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
        this.circulo = new Ellipse2D.Double(x + 20 - (RAIO / 2), y + (RAIO - 10) -(RAIO / 2), RAIO, RAIO);
    }

    void desenhar(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(nome, x, y);

        // Para fins de depuração: desenhe o retângulo
        g2d.setColor(Color.BLACK); // Cor preta
        g2d.fill(circulo);
        g2d.draw(circulo);

        g2d.setColor(Color.WHITE); // Cor do texto
        g2d.setFont(new Font("Arial", Font.BOLD, 12)); // Fonte e tamanho
        g2d.drawString(String.valueOf(tropa), x + 17 , y + RAIO - 5); // Ajuste conforme necessário

    }

    boolean contains(int x, int y) {
        return circulo.contains(x, y);
    }
}
