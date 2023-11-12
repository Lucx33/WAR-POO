package view;

import java.awt.*;
import java.awt.geom.*;

public class Botao {
    private int x;
    private int y;
    private String text;
    private boolean visible;

    Shape retangulo;

    public Botao(int x, int y, String text, boolean visible) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.visible = visible;
        this.retangulo = new Rectangle2D.Double(x, y, 100, 50);
    }

    public void desenhar(Graphics2D g2d) {
        if(visible) {
            g2d.setColor(Color.YELLOW);
            g2d.fill(retangulo);
            g2d.setColor(Color.BLACK);
            g2d.draw(retangulo);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            AuxTexto.drawOutlinedText(g2d, text, 12, x + 10, y + 30);
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
