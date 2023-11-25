package view;

import controller.Observable;
import controller.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SuperJogador extends JFrame implements Observable {
    private boolean visivel = false;

    private List<Observer> observers = new ArrayList<>();
    // Grupos de botões de rádio para os dados
    private ButtonGroup[] grupoDadosAtaque = new ButtonGroup[3];
    private ButtonGroup[] grupoDadosDefesa = new ButtonGroup[3];
    private boolean estadoAnteriorLigado = false;


    public SuperJogador() {
        setTitle("Super Jogador");
        setSize(600, 400);

        // Painel para os componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        // Painel para os botões Ligado e Desligado
        JPanel panelLigadoDesligado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton ligado = new JRadioButton("Ligado");
        ligado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ligado.isSelected() && !estadoAnteriorLigado) {
                    notifyObservers();
                    estadoAnteriorLigado = true;
                }
            }
        });
        JRadioButton desligado = new JRadioButton("Desligado");
        desligado.setSelected(true);
        desligado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(desligado.isSelected() && estadoAnteriorLigado) {
                    notifyObservers();
                    estadoAnteriorLigado = false;
                }
            }
        });
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(ligado);
        grupo.add(desligado);
        panelLigadoDesligado.add(ligado);
        panelLigadoDesligado.add(desligado);
        panel.add(panelLigadoDesligado);
        // Cria e adiciona os botões de rádio para os dados de ataque
        for (int i = 0; i < grupoDadosAtaque.length; i++) {
            grupoDadosAtaque[i] = new ButtonGroup();
            JPanel painelDado = new JPanel(new FlowLayout(FlowLayout.LEFT));
            painelDado.add(new JLabel("Dado de Ataque " + (i + 1) + ":"));
            for (int j = 1; j <= 6; j++) {
                JRadioButton dado = new JRadioButton(String.valueOf(j));
                grupoDadosAtaque[i].add(dado);
                painelDado.add(dado);
                if (j == 1) { // Seleciona o primeiro botão por padrão
                    dado.setSelected(true);
                }
            }
            panel.add(painelDado);
        }

        // Cria e adiciona os botões de rádio para os dados de defesa
        for (int i = 0; i < grupoDadosDefesa.length; i++) {
            grupoDadosDefesa[i] = new ButtonGroup();
            JPanel painelDado = new JPanel(new FlowLayout(FlowLayout.LEFT));
            painelDado.add(new JLabel("Dado de Defesa " + (i + 1) + ":"));
            for (int j = 1; j <= 6; j++) {
                JRadioButton dado = new JRadioButton(String.valueOf(j));
                grupoDadosDefesa[i].add(dado);
                painelDado.add(dado);
                if (j == 1) { // Seleciona o primeiro botão por padrão
                    dado.setSelected(true);
                }
            }
            panel.add(painelDado);
        }

        // Adiciona o painel à janela
        add(panel, BorderLayout.CENTER);

        // Torna a janela visível
        setVisible(visivel);
    }



    public void alternaVisibilidade() {
        visivel = !visivel;
        setVisible(visivel);
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
        Object[] dados = new Object[5];
        dados[0] = "SuperJogador"; // Exemplo de identificação
        dados[1] = getDadosAtaque();
        return dados;
    }

    public List<Integer> getDadosAtaque(){
        // Obtém os valores dos botões de rádio de ataque
        List<Integer> valoresAtaque = new ArrayList<>();
        for (ButtonGroup grupo : grupoDadosAtaque) {
            Enumeration<AbstractButton> botoes = grupo.getElements();
            while (botoes.hasMoreElements()) {
                AbstractButton botao = botoes.nextElement();
                if (botao.isSelected()) {
                    valoresAtaque.add(Integer.parseInt(botao.getText()));
                    break;
                }
            }
        }
        return valoresAtaque;
    }

    public List<Integer> getDadosDefesa(){
        // Obtém os valores dos botões de rádio de defesa
        List<Integer> valoresDefesa = new ArrayList<>();
        for (ButtonGroup grupo : grupoDadosDefesa) {
            Enumeration<AbstractButton> botoes = grupo.getElements();
            while (botoes.hasMoreElements()) {
                AbstractButton botao = botoes.nextElement();
                if (botao.isSelected()) {
                    valoresDefesa.add(Integer.parseInt(botao.getText()));
                    break;
                }
            }
        }
        return valoresDefesa;
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }

}
