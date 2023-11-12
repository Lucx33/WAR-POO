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
        g2d.drawImage(background2, 100, -75, null);
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
            if (pais.containsPais(x, y)) {
                return pais;
            }
        }
        return null;
    }

    public Pais getPais(String nomeTerritorio) {
        for (Pais pais : paises) {
            if (pais.nome.toLowerCase().equals(nomeTerritorio)) {
                return pais;
            }
        }
        return null;
    }

    public void desenharDado(BufferedImage dado, Graphics2D g2d, int x, int y,Color corBorda) {
        // Desenhe a imagem do dado nas coordenadas (x, y)
        g2d.drawImage(dado, x, y,null);

        // Defina a cor da borda
        g2d.setColor(corBorda);

        // Desenhe a borda ao redor da imagem do dado
        g2d.setStroke(new BasicStroke(4)); // Ajuste a largura da borda conforme necessário
        g2d.drawRect(x, y, dado.getWidth(), dado.getHeight());
    }

    public void resetarCores() {
        for (Pais pais : paises) {
            pais.setCorTemp(Color.BLACK); // Resetar para a cor padrão
        }
    }

}