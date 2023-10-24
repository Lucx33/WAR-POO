package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;


public class DesenhaTabuleiro {
    private BufferedImage background1;
    private BufferedImage background2;
    List<Pais> paises;
    private Rectangle2D.Double invisibleRect;

    public DesenhaTabuleiro(String backgroundImagePath1, String backgroundImagePath2) throws IOException {
        background1 = ImageIO.read(new File(backgroundImagePath1));
        background2 = ImageIO.read(new File(backgroundImagePath2));
        paises = new ArrayList<>();
    }

    public void desenharFundo(Graphics2D g2d) {
        // Desenha o Oceano
        g2d.drawImage(background1, 0, 0, 1200, 700, null);

        // Desenha o Tabuleiro
        g2d.drawImage(background2, 100, -95, null);
    }


    public void adicionarPais(String nome, int x, int y) {
        Pais pais = new Pais(nome, x, y);
        paises.add(pais);
    }

    public void desenharPaises(Graphics2D g2d) {
        for (Pais pais : paises) {
            pais.desenhar(g2d);
        }
    }

    public Pais getPaisClicado(int x, int y) {
        for (Pais pais : paises) {
            if (pais.contains(x, y)) {
                return pais;
            }
        }
        return null;
    }

    // Método para verificar se um ponto está dentro do retângulo invisível
    public boolean isPointInsideInvisibleRect(int x, int y) {
        if (invisibleRect != null) {
            return invisibleRect.contains(x, y);
        }
        return false;
    }
}
