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
import java.util.Map;
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
    boolean fasePosicionamento = true;
    boolean faseAtaque = false;
    boolean faseMovimento = false;
    Botao botaoClicado = null;
    private Pais ultimoPaisClicado = null;
    private Pais paisClicado = null;
    Color cor;

    private List<Observer> observers = new ArrayList<>();

    String sinal;

    int qtd;

    int qtdInicial = 0;

    public Jogo(List<String> playerNames, List<String> playerColors) {
        try {
            desenhaTabuleiro = new DesenhaTabuleiro("src/images/war_tabuleiro_fundo.png", "src/images/war_tabuleiro_mapa copy.png");
            adicionarPaises();
            adicionarBotoes();
            dadoAtaque1 = ImageIO.read(new File("src/images/dado_ataque_1.png"));
            dadoAtaque2 = ImageIO.read(new File("src/images/dado_ataque_2.png"));
            dadoAtaque3 = ImageIO.read(new File("src/images/dado_ataque_3.png"));
            dadoDefesa1 = ImageIO.read(new File("src/images/dado_defesa_1.png"));
            dadoDefesa2 = ImageIO.read(new File("src/images/dado_defesa_2.png"));
            dadoDefesa3 = ImageIO.read(new File("src/images/dado_defesa_3.png"));
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
                paisClicado = desenhaTabuleiro.getPaisClicado(x, y);
                botaoClicado = desenhaTabuleiro.getBotaoClicado(x, y);

                // Função para tratamento de cliques
                // Nas diferentes fases do jogo

                // Fase de Posicionamento
                if(fasePosicionamento){
                    handlePosicionamentoClick(x, y);
                }

                // Fase de Ataque
                else if(faseAtaque){
                    handleAtaqueClick(x, y);
                }

                // Fase de Movimento
                else if(faseMovimento){
                    handleMovimentoClick(x, y);
                }

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
        desenhaTabuleiro.desenharBotoes(g2d);

        // Desenha os dados

        desenhaTabuleiro.desenharDado(dadoAtaque1, g2d, 10, 40, cor);
        desenhaTabuleiro.desenharDado(dadoAtaque2, g2d, 10, 80, cor);
        desenhaTabuleiro.desenharDado(dadoAtaque3, g2d, 10, 120,cor);
        desenhaTabuleiro.desenharDado(dadoDefesa1, g2d, 10, 160,cor);
        desenhaTabuleiro.desenharDado(dadoDefesa2, g2d, 10, 200,cor);
        desenhaTabuleiro.desenharDado(dadoDefesa3, g2d, 10, 240,cor);

        g2d.setColor(Color.BLACK);
        if(fasePosicionamento){
            AuxTexto.drawOutlinedText(g2d, "Fase de Posicionamento", 25,490, 570);
            AuxTexto.drawOutlinedText(g2d, "Você possui " + qtd + " exercitos", 25,490, 600);
            if (ultimoPaisClicado != null && ultimoPaisClicado.corDono == cor) {
                ultimoPaisClicado.desenharTriangulos(g2d);
            }
        }
        else if(faseAtaque){
            AuxTexto.drawOutlinedText(g2d, "Fase de Ataque", 25,490, 570);
            if (ultimoPaisClicado != null && ultimoPaisClicado.corDono == cor) {

            }

        }
        else if(faseMovimento){
            AuxTexto.drawOutlinedText(g2d, "Fase de Movimento", 25,490, 570);
        }

    }



    public void adicionarPaises() {
        // America do Sul
        desenhaTabuleiro.adicionarPais("Brasil", 391, 387);
        desenhaTabuleiro.adicionarPais("Argentina", 378, 482);
        desenhaTabuleiro.adicionarPais("Peru", 333, 421);
        desenhaTabuleiro.adicionarPais("Venezuela", 302, 357);

        // America do Norte
        desenhaTabuleiro.adicionarPais("Mexico", 246, 284);
        desenhaTabuleiro.adicionarPais("California", 215, 184);
        desenhaTabuleiro.adicionarPais("Texas", 279, 174);
        desenhaTabuleiro.adicionarPais("NovaYork", 354, 162);
        desenhaTabuleiro.adicionarPais("Vancouver", 256, 116);
        desenhaTabuleiro.adicionarPais("Quebec", 394, 111);
        desenhaTabuleiro.adicionarPais("Alasca", 195, 64);
        desenhaTabuleiro.adicionarPais("Calgary", 303, 67);
        desenhaTabuleiro.adicionarPais("Groelandia", 444, 60);

        // África
        desenhaTabuleiro.adicionarPais("Argelia", 562, 293);
        desenhaTabuleiro.adicionarPais("Nigeria", 608, 366);
        desenhaTabuleiro.adicionarPais("Egito", 670, 313);
        desenhaTabuleiro.adicionarPais("Somalia", 722, 413);
        desenhaTabuleiro.adicionarPais("Angola", 655, 432);
        desenhaTabuleiro.adicionarPais("AfricaDoSul", 677, 500);

        // Europa
        desenhaTabuleiro.adicionarPais("Espanha", 533, 211);
        desenhaTabuleiro.adicionarPais("Franca", 587, 172);
        desenhaTabuleiro.adicionarPais("Italia", 643, 188);
        desenhaTabuleiro.adicionarPais("Polonia", 677, 117);
        desenhaTabuleiro.adicionarPais("Romenia", 689, 198);
        desenhaTabuleiro.adicionarPais("Ucrania", 707, 160);
        desenhaTabuleiro.adicionarPais("Suecia", 633, 64);
        desenhaTabuleiro.adicionarPais("ReinoUnido", 558, 99);

        // Oceania
        desenhaTabuleiro.adicionarPais("Australia", 976, 525);
        desenhaTabuleiro.adicionarPais("NovaZelandia", 1034, 564);
        desenhaTabuleiro.adicionarPais("Perth", 903, 525);
        desenhaTabuleiro.adicionarPais("Indonesia", 995, 427);

        // Ásia
        desenhaTabuleiro.adicionarPais("ArabiaSaudita", 771, 336);
        desenhaTabuleiro.adicionarPais("Jordania", 722, 273);
        desenhaTabuleiro.adicionarPais("Iraque", 773, 270);
        desenhaTabuleiro.adicionarPais("Siria", 749, 210);
        desenhaTabuleiro.adicionarPais("Turquia", 827, 168);
        desenhaTabuleiro.adicionarPais("Paquistao", 851, 237);
        desenhaTabuleiro.adicionarPais("Ira", 817, 279);
        desenhaTabuleiro.adicionarPais("China", 895, 219);
        desenhaTabuleiro.adicionarPais("India", 898, 317);
        desenhaTabuleiro.adicionarPais("Bangladesh", 946, 311);
        desenhaTabuleiro.adicionarPais("Tailandia", 1001, 318);
        desenhaTabuleiro.adicionarPais("CoreiaDoNorte", 961, 234);
        desenhaTabuleiro.adicionarPais("CoreiaDoSul", 1001, 257);
        desenhaTabuleiro.adicionarPais("Japao", 1050, 193);
        desenhaTabuleiro.adicionarPais("Mongolia", 963, 182);
        desenhaTabuleiro.adicionarPais("Cazaquistao", 925, 143);
        desenhaTabuleiro.adicionarPais("Russia", 883, 77);
        desenhaTabuleiro.adicionarPais("Siberia", 1000, 75);
        desenhaTabuleiro.adicionarPais("Estonia", 768, 60);
        desenhaTabuleiro.adicionarPais("Letonia", 762, 123);
    }

    public void adicionarBotoes(){
        desenhaTabuleiro.adicionarBotao(900, 600, "Terminar Fase", false);
    }

    public void setCorDono(List<String> territorios, String Cor) {
        // Iterar por todos os territórios fornecidos
        for (String nomeTerritorio : territorios) {
                Pais pais = desenhaTabuleiro.getPais(nomeTerritorio);
            if (pais != null) {
                pais.setCor(corFromString(Cor));
            }
        }
    }

    public Color corFromString(String corStr) {
        switch (corStr) {
            case "Vermelho":
                return Color.RED;
            case "Azul":
                return Color.BLUE;
            case "Verde":
                return Color.GREEN;
            case "Preto":
                return Color.BLACK;
            case "Branco":
                return Color.WHITE;
            case "Amarelo":
                return Color.YELLOW;
            default:
                return null;
        }
    }

    public Color corFromInt(int corInt) {
        return switch (corInt) {
            case 1 -> Color.BLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.WHITE;
            case 4 -> Color.GREEN;
            case 5 -> Color.BLACK;
            case 6 -> Color.RED;
            default -> null; // ou alguma cor padrão
        };
    }
    public void atualizaJogadorAtual(String corStr) {
        Color cor = corFromString(corStr);
        if (cor != null) {
            this.cor = cor;
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public Object get() {
        Object[] dados = new Object[5];

        if (fasePosicionamento) {

            dados[0] = "FasePosicionamento";
            dados[1] = ultimoPaisClicado.nome;
            dados[2] = sinal;

        } else if (faseAtaque) {

            dados[0] = "FaseAtaque";
            dados[1] = ultimoPaisClicado.nome;
            dados[2] = paisClicado.nome;

        } else if (faseMovimento) {
            dados[0] = "FaseMovimento";
            dados[1] = ultimoPaisClicado.nome;
            dados[2] = paisClicado.nome;

        } else {

            dados[0] = "TrocaTurno";
            fasePosicionamento = true;

        }

        return dados;
    }


    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }

    public void trocaFase(){
        if(fasePosicionamento){
            fasePosicionamento = false;
            ultimoPaisClicado.trianguloCima = null;
            ultimoPaisClicado.trianguloBaixo = null;
            faseAtaque = true;
            desenhaTabuleiro.botoes.get(0).setVisivel();
        }
        else if(faseAtaque){
            faseAtaque = false;
            faseMovimento = true;
        }
        else if(faseMovimento){
            faseMovimento = false;
            desenhaTabuleiro.botoes.get(0).setVisivel();
        }
    }


    public void setExercitos(int qtd) {
        this.qtd = qtd;
        if(qtdInicial == 0){
            qtdInicial = qtd;
        }
    }

    public void setPais(String pais, int cor, int qtdExercito) {
        Pais p = desenhaTabuleiro.getPais(pais);
        p.setCor(corFromInt(cor));
        p.setExercitos(qtdExercito);
    }

    public void mostrarVizinhos(String pais, List<String> vizinhos) {
        Pais p = desenhaTabuleiro.getPais(pais);
        for (String vizinho : vizinhos) {
            Pais v = desenhaTabuleiro.getPais(vizinho);
            if(faseAtaque){
                v.setCorTemp(Color.RED);
            }
            else if(faseMovimento){
                v.setCorTemp(Color.GREEN);
            }
        }
    }

    void handlePosicionamentoClick(int x, int y) {
        // Verifica se o clique foi em um país
        if (paisClicado != null) {
            ultimoPaisClicado = paisClicado;
            repaint();
        }

        // Se ele clicou em um país que é dele
        else if(ultimoPaisClicado != null){

            // Verifica se ele clicou no trianguloCima
            if(ultimoPaisClicado.trianguloCima != null && ultimoPaisClicado.contaisTrianguloCima(x,y)){
                sinal = "+";
                notifyObservers();
            }

            // Verifica se ele clicou no trianguloBaixo
            else if(ultimoPaisClicado.trianguloBaixo != null && ultimoPaisClicado.contaisTrianguloBaixo(x,y) && qtdInicial - qtd > 0){
                sinal = "-";
                notifyObservers();
            }

            // Se ele clicou em um país que não é dele
            else{
                ultimoPaisClicado = null;
                repaint();
            }
        }

        // Se ele clicou em um país que não é dele ou não clicou em pais
        else{
            ultimoPaisClicado = null;
            repaint();
        }
    }

    void handleAtaqueClick(int x, int y) {
        // Verifica se ele clicou no botão de terminar fase
        if(botaoClicado != null){
            if(botaoClicado.getText().equals("Terminar Fase")){
                trocaFase();
                repaint();
            }
        }

        // Verifica se o clique foi em um país
        if (paisClicado != null) {

            // Verifica se já existe um país selecionado
            if(ultimoPaisClicado == null){
                ultimoPaisClicado = paisClicado;
                desenhaTabuleiro.resetarCores();
                repaint();
            }

            // Verifica se o país clicado é um vizinho do país selecionado
            else if(paisClicado != ultimoPaisClicado){
                notifyObservers();
                repaint();
            }

            // Se ele clicou no mesmo país duas vezes
            else {
                ultimoPaisClicado = paisClicado; // Set the last clicked country
                notifyObservers();
                repaint(); // Request to repaint the panel
            }
        }

        // Se ele clicou em um país que não é dele ou não clicou em pais
        else{
            ultimoPaisClicado = null;
            desenhaTabuleiro.resetarCores();
            repaint();
        }
    }

    void handleMovimentoClick(int x, int y) {
        // Verifica se ele clicou no botão de terminar fase
        if(botaoClicado != null){
            if(botaoClicado.getText().equals("Terminar Fase")){
                trocaFase();
                notifyObservers();
                repaint();
            }
        }

        // Verifica se o clique foi em um país
        if (paisClicado != null) {

            // Verifica se já existe um país selecionado
            if(ultimoPaisClicado == null){
                ultimoPaisClicado = paisClicado;
                desenhaTabuleiro.resetarCores();
                repaint();
            }

            // Verifica se o país clicado é um vizinho do país selecionado
            else if(paisClicado != ultimoPaisClicado){
                notifyObservers();
                repaint();
            }

            // Se ele clicou no mesmo país duas vezes
            else {
                ultimoPaisClicado = paisClicado; // Set the last clicked country
                notifyObservers();
                repaint(); // Request to repaint the panel
            }
        }

        // Se ele clicou em um país que não é dele ou não clicou em pais
        else{
            ultimoPaisClicado = null;
            desenhaTabuleiro.resetarCores();
            repaint();
        }
    }

}