package view;

import controller.ControladorJogo;
import controller.Observable;
import controller.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.File;
import java.io.IOException;


public class Menu extends JFrame implements Observable {

    public final int LARG_DEFAULT = 1200;
    public final int ALT_DEFAULT = 700;
    private JComboBox<String> playerCountComboBox;
    private JPanel playerInfoPanel;
    PlayersInfo info;

    List<Observer> observers = new ArrayList<>();

    public Menu(PlayersInfo playersInfo) {
        setSize(LARG_DEFAULT, ALT_DEFAULT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Usando um layout null

        JLabel background = new JLabel(new ImageIcon("src/images/menu.png"));
        background.setBounds(0, 0, LARG_DEFAULT, ALT_DEFAULT);
        add(background);

        // Adicione o painel para informações dos jogadores
        playerInfoPanel = new JPanel();
        playerInfoPanel.setOpaque(false); // Torna o painel transparente
        playerInfoPanel.setLayout(new GridLayout(0, 2));
        playerInfoPanel.setBounds(0, 0, LARG_DEFAULT, ALT_DEFAULT);
        background.add(playerInfoPanel);

        JButton newGameButton = new JButton("Novo Jogo");
        JButton continueGameButton = new JButton("Continuar Jogo");

        newGameButton.setBounds(LARG_DEFAULT / 2 - 75, ALT_DEFAULT / 2 + 160, 200, 50);
        continueGameButton.setBounds(LARG_DEFAULT / 2 - 75, ALT_DEFAULT / 2 + 220, 200, 50);

        background.add(newGameButton);
        background.add(continueGameButton);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewGameDialog(playersInfo);;
            }
        });

        continueGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers();
            }
        });

    }

    void openNewGameDialog(PlayersInfo playersInfo) {
        String[] playerCountOptions = {"3 Jogadores", "4 Jogadores", "5 Jogadores", "6 Jogadores"};
        JComboBox<String> playerCountComboBox = new JComboBox<>(playerCountOptions);
        JTextField[] playerNameFields = new JTextField[6];
        JComboBox<String>[] playerColorComboBoxes = new JComboBox[6];

        JPanel panel = new JPanel(new GridLayout(0, 3));
        panel.add(new JLabel("Quantos jogadores?"));
        panel.add(playerCountComboBox);
        for (int i = 0; i < 6; i++) {
            playerNameFields[i] = new JTextField();
            playerColorComboBoxes[i] = new JComboBox<>(new String[]{"Vermelho", "Azul", "Verde", "Amarelo", "Preto", "Branco"});
            playerNameFields[i].setEnabled(false);
            playerColorComboBoxes[i].setEnabled(false);


        }
        panel.add(new JLabel());

        for (int i = 0; i < 6; i++) {
            panel.add(new JLabel("Nome Jogador " + (i + 1)));
            panel.add(playerNameFields[i]);
            panel.add(playerColorComboBoxes[i]);

        }

        playerCountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedPlayers = playerCountComboBox.getSelectedIndex() + 3;
                for (int i = 0; i < 6; i++) {
                    playerNameFields[i].setEnabled(i < selectedPlayers);
                    playerColorComboBoxes[i].setEnabled(i < selectedPlayers);
                }
            }
        });

        int result = JOptionPane.showConfirmDialog(this, panel, "Configuração de Novo Jogo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int selectedPlayers = playerCountComboBox.getSelectedIndex() + 3;
            List<String> playerNames = new ArrayList<>();
            List<String> playerColors = new ArrayList<>();

            for (int i = 0; i < selectedPlayers; i++) {
                playerNames.add(playerNameFields[i].getText());
                playerColors.add((String) playerColorComboBoxes[i].getSelectedItem());
            }

            // Feche a janela de diálogo do menu
            dispose();

            playersInfo.setPlayersInfo(playerNames, playerColors);
        }



    }


    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }
    @Override
    public void addObserver(Observer o) {

        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public Object get() {
        Object dados[]=new Object[5];
        dados[0]= "Continuar";
        return dados;
    }
}