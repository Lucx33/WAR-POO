package view;

import java.awt.*;
import java.awt.geom.*;

public class Botao {
    private int x;
    private int y;
    private String text;
    private boolean visible;
    Shape retangulo;

    Color cor = new Color(255, 255, 255);
    public Botao(int x, int y, String text, Color cor, boolean visible) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.visible = visible;
        this.cor = cor;
        switch (text){
            case "Objetivo":
                this.retangulo = new Ellipse2D.Double(x - 25, y - 25, 50, 50);
                break;
            case "Dados":
                this.retangulo = new Rectangle2D.Double(x, y, 70, 45);
                break;
            default:
                this.retangulo = new Rectangle2D.Double(x, y, 100, 100);
                break;
        }

    }


    public void desenhar(Graphics2D g2d) {
        if(visible) {
            switch (text) {
                case "Terminar Fase":
                    g2d.setColor(cor);
                    g2d.fill(retangulo);
                    g2d.setColor(Color.BLACK);
                    g2d.draw(retangulo);
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Arial", Font.BOLD, 12));
                    AuxTexto.drawOutlinedText(g2d, text, 12, x + 10, y + 30);
                    break;
                case "Objetivo":
                    int raio = 30;
                    // Define a cor para desenhar o símbolo
                    g2d.setColor(Color.RED);

                    // Desenha o círculo externo
                    g2d.fillOval(x - raio, y - raio, 2 * raio, 2 * raio);

                    // Define a cor para o círculo interno
                    g2d.setColor(Color.WHITE);

                    // Calcula o raio do círculo interno (por exemplo, 80% do raio externo)
                    raio = (int) (raio * 0.7);

                    // Desenha o círculo interno
                    g2d.fillOval(x - raio, y - raio, 2 * raio, 2 * raio);

                    raio = (int) (raio * 0.5);

                    g2d.setColor(Color.RED);

                    g2d.fillOval(x - raio, y - raio, 2 * raio, 2 * raio);

                    break;
                case "Dados":
                    g2d.setColor(cor);
                    g2d.fill(retangulo);
                    g2d.setColor(Color.BLACK);
                    g2d.draw(retangulo);
                    g2d.setColor(Color.BLACK);
                    break;
            }
        }

    }
    void setVisivel() {
        if(visible) {
            visible = false;
        } else {
            visible = true;
        }
    }
    public String getText(){
        return text;
    }
}