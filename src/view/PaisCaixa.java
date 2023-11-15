package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaisCaixa extends JDialog {
    private JLabel infoLabel;
    private JButton plusButton;
    private JButton minusButton;

    public PaisCaixa(JFrame parent, String countryName) {
        super(parent, countryName, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        // Layout personalizado com BorderLayout
        setLayout(new BorderLayout());

        // Texto informativo na parte superior
        infoLabel = new JLabel("20 peças adicionáveis restantes", JLabel.CENTER);
        add(infoLabel, BorderLayout.NORTH);

        // Painel para os botões "+" e "-"
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Botões "+" e "-"
        plusButton = new JButton("+");
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para o botão "+"
                System.out.println("Botão '+' pressionado");
            }
        });
        buttonPanel.add(plusButton);

        minusButton = new JButton("-");
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para o botão "-"
                System.out.println("Botão '-' pressionado");
            }
        });
        buttonPanel.add(minusButton);

        // Adiciona o painel de botões ao centro
        add(buttonPanel, BorderLayout.CENTER);
    }


}