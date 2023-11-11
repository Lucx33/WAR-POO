package view;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import controller.Observable;
import controller.Observer;

public class Jogo extends JPanel implements Observable{
    BufferedImage tabuleiro;
    BufferedImage oceano;
    BufferedImage dadoAtaque1;
    BufferedImage dadoAtaque2;
    BufferedImage dadoAtaque3;
    BufferedImage dadoDefesa1;
    BufferedImage dadoDefesa2;
    BufferedImage dadoDefesa3;
    private DesenhaTabuleiro desenhaTabuleiro;

    private Pais ultimoPaisClicado = null;
    private String ultimaCor = null;

    private List<Observer> observers = new ArrayList<>();

    public Jogo(List<String> playerNames, List<String> playerColors) {
        try {
            desenhaTabuleiro = new DesenhaTabuleiro("oceano3.jpg", "war_tabuleiro_mapa copy.png");
            adicionarPaises();
            dadoAtaque1 = ImageIO.read(new File("dado_ataque_1.png"));
            dadoAtaque2 = ImageIO.read(new File("dado_ataque_2.png"));
            dadoAtaque3 = ImageIO.read(new File("dado_ataque_3.png"));
            dadoDefesa1 = ImageIO.read(new File("dado_defesa_1.png"));
            dadoDefesa2 = ImageIO.read(new File("dado_defesa_2.png"));
            dadoDefesa3 = ImageIO.read(new File("dado_defesa_3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(null);

        JFrame frame = new JFrame("War");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.add(this);
        frame.setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Pais paisClicado = desenhaTabuleiro.getPaisClicado(x, y);
                System.out.println("Clicou em: " + paisClicado.nome);
            }
        });

        // Adicionar um botão "Finalizar Turno"
        JButton botaoFinalizarTurno = new JButton("Finalizar Turno");
        botaoFinalizarTurno.setBounds(120, 605, 120, 30); // Defina as coordenadas do botão
        this.add(botaoFinalizarTurno);

        // Adicionar um ActionListener para o botão "Finalizar Turno"
        botaoFinalizarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers();
            }
        });



    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Obtém o contexto gráfico do JFrame
        Graphics2D g2d = (Graphics2D) g;

        // Chame o método para desenhar o tabuleiro
        desenhaTabuleiro.desenharFundo(g2d);

        desenhaTabuleiro.desenharPaises(g2d);

        // Desenha os dados

        desenhaTabuleiro.desenharDado(dadoAtaque1, g2d, 10, 40,Color.black);
        desenhaTabuleiro.desenharDado(dadoAtaque2, g2d, 10, 80,Color.black);
        desenhaTabuleiro.desenharDado(dadoAtaque3, g2d, 10, 120,Color.black);
        desenhaTabuleiro.desenharDado(dadoDefesa1, g2d, 10, 160,Color.black);
        desenhaTabuleiro.desenharDado(dadoDefesa2, g2d, 10, 200,Color.black);
        desenhaTabuleiro.desenharDado(dadoDefesa3, g2d, 10, 240,Color.black);


    }



    public void adicionarPaises() {
        // America do Sul
        desenhaTabuleiro.adicionarPais("Brasil", 391, 367);
        desenhaTabuleiro.adicionarPais("Argentina", 378, 462);
        desenhaTabuleiro.adicionarPais("Peru", 333, 401);
        desenhaTabuleiro.adicionarPais("Venezuela", 302, 337);

        // America do Norte
        desenhaTabuleiro.adicionarPais("Mexico", 246, 264);
        desenhaTabuleiro.adicionarPais("California", 215, 164);
        desenhaTabuleiro.adicionarPais("Texas", 279, 154);
        desenhaTabuleiro.adicionarPais("NovaYork", 354, 142);
        desenhaTabuleiro.adicionarPais("Vancouver", 256, 96);
        desenhaTabuleiro.adicionarPais("Quebec", 394, 91);
        desenhaTabuleiro.adicionarPais("Alasca", 195, 44);
        desenhaTabuleiro.adicionarPais("Calgary", 303, 47);
        desenhaTabuleiro.adicionarPais("Groelandia", 444, 40);

        //Africa
        desenhaTabuleiro.adicionarPais("Argelia", 562, 273);
        desenhaTabuleiro.adicionarPais("Nigeria", 608, 346);
        desenhaTabuleiro.adicionarPais("Egito", 670, 293);
        desenhaTabuleiro.adicionarPais("Somalia", 722, 393);
        desenhaTabuleiro.adicionarPais("Angola", 655, 412);
        desenhaTabuleiro.adicionarPais("AfricaDoSul", 677, 480);

        //Europa
        desenhaTabuleiro.adicionarPais("Espanha", 533, 191);
        desenhaTabuleiro.adicionarPais("Franca", 587, 152);
        desenhaTabuleiro.adicionarPais("Italia", 643, 168);
        desenhaTabuleiro.adicionarPais("Polonia", 677, 97);
        desenhaTabuleiro.adicionarPais("Romenia", 689, 178);
        desenhaTabuleiro.adicionarPais("Ucrania", 707, 140);
        desenhaTabuleiro.adicionarPais("Suecia", 633, 44);
        desenhaTabuleiro.adicionarPais("ReinoUnido", 558, 79);

        //Oceania
        desenhaTabuleiro.adicionarPais("Australia", 976, 505);
        desenhaTabuleiro.adicionarPais("NovaZelandia", 1034, 544);
        desenhaTabuleiro.adicionarPais("Perth", 903, 505);
        desenhaTabuleiro.adicionarPais("Indonesia", 995, 407);

        //Asia
        desenhaTabuleiro.adicionarPais("ArabiaSaudita", 771, 316);
        desenhaTabuleiro.adicionarPais("Jordania", 722, 253);
        desenhaTabuleiro.adicionarPais("Iraque", 773, 250);
        desenhaTabuleiro.adicionarPais("Siria", 749, 190);
        desenhaTabuleiro.adicionarPais("Turquia", 827, 148);
        desenhaTabuleiro.adicionarPais("Paquistao", 851, 217);
        desenhaTabuleiro.adicionarPais("Ira", 817, 259);
        desenhaTabuleiro.adicionarPais("China", 895, 199);
        desenhaTabuleiro.adicionarPais("India", 898, 297);
        desenhaTabuleiro.adicionarPais("Bangladesh", 946, 291);
        desenhaTabuleiro.adicionarPais("Tailandia", 1001, 298);
        desenhaTabuleiro.adicionarPais("CoreiaDoNorte", 961, 214);
        desenhaTabuleiro.adicionarPais("CoreiaDoSul", 1001, 237);
        desenhaTabuleiro.adicionarPais("Japao", 1050, 173);
        desenhaTabuleiro.adicionarPais("Mongolia", 963, 162);
        desenhaTabuleiro.adicionarPais("Cazaquistao", 925, 123);
        desenhaTabuleiro.adicionarPais("Russia", 883, 57);
        desenhaTabuleiro.adicionarPais("Siberia", 1000, 55);
        desenhaTabuleiro.adicionarPais("Estonia", 768, 40);
        desenhaTabuleiro.adicionarPais("Letonia", 762, 103);

    }



    public void setCorDono(List<String> territorios, String Cor) {
        // Iterar por todos os territórios fornecidos
        for (String nomeTerritorio : territorios) {
            Pais pais = desenhaTabuleiro.getPais(nomeTerritorio);
            if (pais != null) {
                pais.setCor(Cor);
            }
        }
    }


    public void atualizaJogadorAtual(int jogadorAtual) {

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

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public Object get() {
        return this;
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }
}