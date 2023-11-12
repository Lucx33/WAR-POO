package view;

import java.awt.*;

public class AuxTexto {
    public static void drawOutlinedText(Graphics2D g2d, String text,int size, int x, int y) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, size));
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                g2d.drawString(text, x + i, y + j);
            }
        }

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);
    }

}
