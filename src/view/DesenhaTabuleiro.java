package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DesenhaTabuleiro {
    private BufferedImage background1;
    private BufferedImage background2;
    List<Pais> paises;
    List<Botao> botoes;
    Mao mao;
    Image objetivo;
    boolean visibilidadeObjetivo = false;

    public DesenhaTabuleiro(String backgroundImagePath1, String backgroundImagePath2) throws IOException {
        background1 = ImageIO.read(new File(backgroundImagePath1));
        background2 = ImageIO.read(new File(backgroundImagePath2));

        paises = new ArrayList<>();
        botoes = new ArrayList<>();
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

    public void adicionarBotao(int x, int y, String text, Color cor, boolean visible) {
        Botao botao = new Botao(x, y, text, cor, visible);
        botoes.add(botao);
    }

    public void desenharBotoes(Graphics2D g2d) {
        for (Botao botao : botoes) {
            botao.desenhar(g2d);
        }
    }

    public Botao getBotaoClicado(int x, int y) {
        for (Botao botao : botoes) {
            if (botao.retangulo.contains(x, y)) {
                return botao;
            }
        }
        return null;
    }

    public void desenharMao(Graphics2D g2d) {
        if(mao != null){
            mao.desenhar(g2d);
        }
    }

    public void setMao(Map<String, String> cartas) {
        this.mao = new Mao(cartas);
    }

    public BufferedImage getImagemDado(int numero, String tipo) {
        try {
            if(numero == -1){
                return ImageIO.read(new File("src/images/dado_desativado.png"));
            }
            return ImageIO.read(new File("src/images/dado_" + tipo + "_" + numero + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void desenharObjetivo(Graphics2D g2d) {
        if(visibilidadeObjetivo){
            g2d.drawImage(objetivo, 470, 60, null);
        }
    }

    public void alternarObjetivo() {
        if(visibilidadeObjetivo){
            visibilidadeObjetivo = false;
        }
        else{
            visibilidadeObjetivo = true;
        }
    }

    public void setObjetivo(int idObjetivo) {

        try {
            String formataObjetivo = idObjetivo < 10 ? String.format("0%d", idObjetivo) : String.valueOf(idObjetivo);

            BufferedImage temp = ImageIO.read(new File("src/images/obj_" + formataObjetivo + ".jpg"));
            objetivo = temp.getScaledInstance(270, 440, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}