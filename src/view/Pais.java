package view;

import java.awt.*;
import java.awt.geom.*;

class Pais {
    String nome;
    int x, y;
    static final int RAIO = 30;
    Shape circulo;
    Shape trianguloCima;
    Shape trianguloBaixo;
    Shape caixaConfirmacao;
    int tropa = 0;
    Color corDono;

    Color corTemp = Color.BLACK;

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

        if(nome.equals("Romenia")) {
            AuxTexto.drawOutlinedText(g2d, nome, 14,x - 4 * nome.length(), y + 28);
        } else {
            AuxTexto.drawOutlinedText(g2d, nome,14, x - 4 * nome.length(), y -20);
        }

        // Fill the circle with its color
        g2d.setColor(corDono);
        g2d.fill(circulo);

        // Set the color for the circle's outline
        g2d.setColor(corTemp);
        g2d.setStroke(new BasicStroke(3)); // Set the outline thickness
        g2d.draw(circulo); // Draw the circle's outline

        g2d.setColor(Color.WHITE); // Text color
        g2d.setFont(new Font("Arial", Font.BOLD, 12)); // Font and size
        AuxTexto.drawOutlinedText(g2d, String.valueOf(tropa), 12,x - 4 , y + 4); // Adjust as necessary
    }


    // Helper method for drawing outlined text
    boolean containsPais(int x, int y) {
        return circulo.contains(x, y);
    }
    boolean contaisTrianguloCima(int x, int y) {
        return trianguloCima.contains(x, y);
    }
    boolean contaisTrianguloBaixo(int x, int y) {
        return trianguloBaixo.contains(x, y);
    }

    boolean contaisCaixaConfirmacao(int x, int y) {
        return caixaConfirmacao.contains(x, y);
    }

    void setCor(Color cor) {
        this.corDono = cor;
    }

    void desenharTriangulos(Graphics2D g2d) {
        int lineLength = 8;
        int centroX, centroY;
        

        // Definir os pontos para o trianguloCima e Desenhar o trianguloCima
        int[] xPontosCima = {x, x - RAIO + 15, x + RAIO - 15};
        int[] yPontosCima = {y - 55, y - 20 - RAIO / 2, y - 20 - RAIO / 2};
        trianguloCima = new Polygon(xPontosCima, yPontosCima, 3);

        g2d.setColor(Color.YELLOW);

        g2d.fill(trianguloCima);

        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(3)); // Exemplo: Espessura do contorno de 2

        // Desenhar o contorno do triângulo
        g2d.draw(trianguloCima);

        // Calcula o centro do triangulo para desenhar o sinal de +
        centroX = (xPontosCima[0] + xPontosCima[1] + xPontosCima[2]) / 3;
        centroY = (yPontosCima[0] + yPontosCima[1] + yPontosCima[2]) / 3;

        g2d.drawLine(centroX - lineLength / 2, centroY, centroX + lineLength / 2, centroY);

        g2d.drawLine(centroX, centroY - lineLength / 2, centroX, centroY + lineLength / 2);


        
        // Definir os pontos para o trianguloBaixo
        int[] xPontosBaixo = {x, x - RAIO + 15, x + RAIO - 15};
        int[] yPontosBaixo = {y + 55, y + 20 + RAIO / 2, y + 20 + RAIO / 2};
        trianguloBaixo = new Polygon(xPontosBaixo, yPontosBaixo, 3);

        // Preencher o trianguloBaixo
        g2d.setColor(Color.YELLOW);
        g2d.fill(trianguloBaixo);

        // Desenhar o contorno do trianguloBaixo
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(trianguloBaixo);

        // Calcula o centro do trianguloBaixo para desenhar o sinal de -
        centroX = (xPontosBaixo[0] + xPontosBaixo[1] + xPontosBaixo[2]) / 3;
        centroY = (yPontosBaixo[0] + yPontosBaixo[1] + yPontosBaixo[2]) / 3;

        g2d.drawLine(centroX - lineLength / 2, centroY, centroX + lineLength / 2, centroY);
    }

    void desenharCaixaConfirmacao(Graphics2D g2d){
        int lineLength = 8;
        int centroX, centroY;

        int tempy = y;
        y = y + 4;


        // Definir os pontos para o trianguloCima e Desenhar o trianguloCima
        int[] xPontos = {x + 25, x + 45, x + 45, x + 25};
        int[] yPontos = {y - 15, y - 15, y + 5, y + 5};
        caixaConfirmacao = new Polygon(xPontos, yPontos, 4);

        g2d.setColor(Color.GREEN);

        g2d.fill(caixaConfirmacao);

        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(3)); // Exemplo: Espessura do contorno de 2

        // Desenhar o contorno da caixa de confirmação
        g2d.draw(caixaConfirmacao);

        centroX = (xPontos[0] + xPontos[1]) / 2;
        centroY = (yPontos[0] + yPontos[1]) / 2;

        g2d.setColor(Color.BLACK);
        // Define the points of the check mark
        int[] xPoints = {centroX - 5, centroX, centroX + 7};
        int[] yPoints = {centroY + 9, centroY + 15, centroY + 1};

        g2d.drawPolyline(xPoints, yPoints, 3); // Draw the check mark

        y = tempy;
    }

    void setTropa(int tropa) {
        this.tropa = tropa;
    }

    public void setExercitos(int qtd) {
        this.tropa = qtd;
    }

    public void setCorTemp(Color cor) {
        this.corTemp = cor;
    }


}