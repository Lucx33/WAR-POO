package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Mao {
    int x,y;
    Color cor;
    Boolean visible;
    Shape quadro;

    int objetivo;

    Map<String, String> cartas;

    List<BufferedImage> cardImages;

    public Mao(Map<String, String> cartas) {
        this.x = 350;
        this.y = 540;
        this.cor = new Color(40, 26, 13);
        this.visible = true;
        this.quadro = new Rectangle(x, y, 500, 130);
        this.objetivo = -1;
        this.cartas = cartas;
        this.cardImages = new ArrayList<>();

    }

    public void desenhar(Graphics2D g2d) {
        if(visible) {
            // Desenhar o quadro que representa a mão
            g2d.setColor(cor); // Define a cor do desenho
            g2d.fill(quadro);  // Preenche o retângulo com a cor especificada

            // Agora, vamos desenhar as cartas
            int xOffset = 20; // Deslocamento horizontal para desenhar as cartas
            int newWidth = 70; // Largura desejada para as cartas
            int newHeight = 100; // Altura desejada para as cartas
            for (String carta : cartas.keySet()) {
                try {
                    if(carta.equals("?")){
                        BufferedImage image = ImageIO.read(new File("src/images/war_carta_coringa.png"));
                        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                        cardImages.add(image);
                        g2d.drawImage(resizedImage, x + xOffset, y + 10, null);
                    }
                    else {
                        BufferedImage image = ImageIO.read(new File("src/images/war_carta_" + cartas.get(carta) + "_" + carta + ".png"));
                        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                        cardImages.add(image);
                        g2d.drawImage(resizedImage, x + xOffset, y + 10, null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Incrementa o deslocamento para a próxima carta
                xOffset += 25 + newWidth; // Ajuste o deslocamento considerando a nova largura
            }
        }
    }

}
