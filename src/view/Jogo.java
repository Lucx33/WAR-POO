package view;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import controller.Observable;
import controller.Observer;

public class Jogo extends JPanel implements Observable{
    BufferedImage dadoAtaque1;
    BufferedImage dadoAtaque2;
    BufferedImage dadoAtaque3;
    BufferedImage dadoDefesa1;
    BufferedImage dadoDefesa2;
    BufferedImage dadoDefesa3;
    BufferedImage superJogador;

    private DesenhaTabuleiro desenhaTabuleiro;
    String fase = "posicionamento";
    String posicionamentoContinente = "";
    Botao botaoClicado = null;
    private Pais ultimoPaisClicado = null;
    private Pais paisClicado = null;
    Color cor;
    boolean sp, click, save = false;

    private List<Observer> observers = new ArrayList<>();

    String sinal;
    int x, y;

    int qtd;

    int qtdInicial = 0;

    public Jogo() {
        try {
            desenhaTabuleiro = new DesenhaTabuleiro("src/images/war_tabuleiro_fundo.png", "src/images/war_tabuleiro_mapa copy.png");
            adicionarPaises();
            adicionarBotoes();
            dadoAtaque1 = desenhaTabuleiro.getImagemDado(-1, "ataque");
            dadoAtaque2 = desenhaTabuleiro.getImagemDado(-1, "ataque");
            dadoAtaque3 = desenhaTabuleiro.getImagemDado(-1, "ataque");
            dadoDefesa1 = desenhaTabuleiro.getImagemDado(-1, "defesa");
            dadoDefesa2 = desenhaTabuleiro.getImagemDado(-1, "defesa");
            dadoDefesa3 = desenhaTabuleiro.getImagemDado(-1, "defesa");
            superJogador = desenhaTabuleiro.getImagemSuperJogador();
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
                x = e.getX();
                y = e.getY();
                paisClicado = desenhaTabuleiro.getPaisClicado(x, y);
                botaoClicado = desenhaTabuleiro.getBotaoClicado(x, y);

                click = true;

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
        desenhaTabuleiro.desenharBotoes(g2d);

        // Desenha os dados

        desenhaTabuleiro.desenharDado(dadoAtaque1, g2d, 10, 40, cor);
        desenhaTabuleiro.desenharDado(dadoAtaque2, g2d, 10, 80, cor);
        desenhaTabuleiro.desenharDado(dadoAtaque3, g2d, 10, 120,cor);
        desenhaTabuleiro.desenharDado(dadoDefesa1, g2d, 10, 160,cor);
        desenhaTabuleiro.desenharDado(dadoDefesa2, g2d, 10, 200,cor);
        desenhaTabuleiro.desenharDado(dadoDefesa3, g2d, 10, 240,cor);

        desenhaTabuleiro.desenharSuperJogador(superJogador, g2d, 10, 280,cor);

        desenhaTabuleiro.desenharMao(g2d);

        desenhaTabuleiro.desenharObjetivo(g2d);

        g2d.setColor(Color.BLACK);

        switch (fase) {
            case "Posicionamento Continente":
                AuxTexto.drawOutlinedText(g2d, "Fase de Posicionamento", 25, 470, 535);
                AuxTexto.drawOutlinedText(g2d, "Você possui " + qtd + " exercitos para posicionar na " + posicionamentoContinente, 25, 270, 570);
                if (ultimoPaisClicado != null && ultimoPaisClicado.corDono == cor && ultimoPaisClicado.continente.equals(posicionamentoContinente)) {
                    ultimoPaisClicado.desenharTriangulos(g2d);
                }
                break;
            case "Posicionamento":
                AuxTexto.drawOutlinedText(g2d, "Fase de Posicionamento", 25, 470, 535);
                AuxTexto.drawOutlinedText(g2d, "Você possui " + qtd + " exercitos", 25, 470, 570);
                if (ultimoPaisClicado != null && ultimoPaisClicado.corDono == cor) {
                    ultimoPaisClicado.desenharTriangulos(g2d);
                }
                break;
            case "Ataque":
                AuxTexto.drawOutlinedText(g2d, "Fase de Ataque", 25, 500, 535);
                break;
            case "Movimento":
                AuxTexto.drawOutlinedText(g2d, "Fase de Movimento", 25, 490, 535);
                break;

        }

    }



    public void adicionarPaises() {
        // América do Sul
        desenhaTabuleiro.adicionarPais("Brasil", "America do Sul", 391, 387);
        desenhaTabuleiro.adicionarPais("Argentina", "America do Sul", 378, 482);
        desenhaTabuleiro.adicionarPais("Peru", "America do Sul", 333, 421);
        desenhaTabuleiro.adicionarPais("Venezuela", "America do Sul", 302, 357);

        // América do Norte
        desenhaTabuleiro.adicionarPais("Mexico", "America do Norte", 246, 284);
        desenhaTabuleiro.adicionarPais("California", "America do Norte", 215, 184);
        desenhaTabuleiro.adicionarPais("Texas", "America do Norte", 279, 174);
        desenhaTabuleiro.adicionarPais("NovaYork", "America do Norte", 354, 162);
        desenhaTabuleiro.adicionarPais("Vancouver", "America do Norte", 256, 116);
        desenhaTabuleiro.adicionarPais("Quebec", "America do Norte", 394, 111);
        desenhaTabuleiro.adicionarPais("Alasca", "America do Norte", 195, 64);
        desenhaTabuleiro.adicionarPais("Calgary", "America do Norte", 303, 67);
        desenhaTabuleiro.adicionarPais("Groelandia", "America do Norte", 444, 60);

        // África
        desenhaTabuleiro.adicionarPais("Argelia", "Africa", 562, 293);
        desenhaTabuleiro.adicionarPais("Nigeria", "Africa", 608, 366);
        desenhaTabuleiro.adicionarPais("Egito", "Africa", 670, 313);
        desenhaTabuleiro.adicionarPais("Somalia", "Africa", 722, 413);
        desenhaTabuleiro.adicionarPais("Angola", "Africa", 655, 432);
        desenhaTabuleiro.adicionarPais("AfricaDoSul", "Africa", 677, 500);

        // Europa
        desenhaTabuleiro.adicionarPais("Espanha", "Europa", 533, 211);
        desenhaTabuleiro.adicionarPais("Franca", "Europa", 587, 172);
        desenhaTabuleiro.adicionarPais("Italia", "Europa", 643, 188);
        desenhaTabuleiro.adicionarPais("Polonia", "Europa", 677, 117);
        desenhaTabuleiro.adicionarPais("Romenia", "Europa", 689, 198);
        desenhaTabuleiro.adicionarPais("Ucrania", "Europa", 707, 160);
        desenhaTabuleiro.adicionarPais("Suecia", "Europa", 633, 64);
        desenhaTabuleiro.adicionarPais("ReinoUnido", "Europa", 558, 99);

        // Oceania
        desenhaTabuleiro.adicionarPais("Australia", "Oceania", 976, 525);
        desenhaTabuleiro.adicionarPais("NovaZelandia", "Oceania", 1034, 564);
        desenhaTabuleiro.adicionarPais("Perth", "Oceania", 903, 525);
        desenhaTabuleiro.adicionarPais("Indonesia", "Oceania", 995, 427);

        // Ásia
        desenhaTabuleiro.adicionarPais("ArabiaSaudita", "Asia", 771, 336);
        desenhaTabuleiro.adicionarPais("Jordania", "Asia", 722, 273);
        desenhaTabuleiro.adicionarPais("Iraque", "Asia", 773, 270);
        desenhaTabuleiro.adicionarPais("Siria", "Asia", 749, 210);
        desenhaTabuleiro.adicionarPais("Turquia", "Asia", 827, 168);
        desenhaTabuleiro.adicionarPais("Paquistao", "Asia", 851, 237);
        desenhaTabuleiro.adicionarPais("Ira", "Asia", 817, 279);
        desenhaTabuleiro.adicionarPais("China", "Asia", 895, 219);
        desenhaTabuleiro.adicionarPais("India", "Asia", 898, 317);
        desenhaTabuleiro.adicionarPais("Bangladesh", "Asia", 946, 311);
        desenhaTabuleiro.adicionarPais("Tailandia", "Asia", 1001, 318);
        desenhaTabuleiro.adicionarPais("CoreiaDoNorte", "Asia", 961, 234);
        desenhaTabuleiro.adicionarPais("CoreiaDoSul", "Asia", 1001, 257);
        desenhaTabuleiro.adicionarPais("Japao", "Asia", 1050, 193);
        desenhaTabuleiro.adicionarPais("Mongolia", "Asia", 963, 182);
        desenhaTabuleiro.adicionarPais("Cazaquistao", "Asia", 925, 143);
        desenhaTabuleiro.adicionarPais("Russia", "Asia", 883, 77);
        desenhaTabuleiro.adicionarPais("Siberia", "Asia", 1000, 75);
        desenhaTabuleiro.adicionarPais("Estonia", "Asia", 768, 60);
        desenhaTabuleiro.adicionarPais("Letonia", "Asia", 762, 123);
    }


    public void adicionarBotoes(){
        desenhaTabuleiro.adicionarBotao(900, 600, "Terminar Fase", Color.YELLOW , false);
        desenhaTabuleiro.adicionarBotao(300, 600, "Objetivo", Color.YELLOW , true);
        desenhaTabuleiro.adicionarBotao(5,280,"Dados",Color.YELLOW, false);
        desenhaTabuleiro.adicionarBotao(1000,600,"Salvar",Color.YELLOW, true);
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

        if(sp){
            dados[0] = "SuperJogador";
            sp = false;
        }

        else if(save){
            dados[0] = "Salvar";
            save = false;
        }

        else if(click){
            dados[0] = "Click";
            dados[1] = x;
            dados[2] = y;
            click = false;
        }

        else{
            switch(fase){
                case "Posicionamento Continente":
                    dados[0] = "FasePosicionamentoContinente";
                    dados[1] = ultimoPaisClicado.nome;
                    dados[2] = sinal;
                    break;
                case "Posicionamento":
                    dados[0] = "FasePosicionamento";
                    dados[1] = ultimoPaisClicado.nome;
                    dados[2] = sinal;
                    break;
                case "MovimentoAtaque":
                    dados[0] = "FaseMovimentoAtaque";
                    dados[1] = ultimoPaisClicado.nome;
                    dados[2] = paisClicado.nome;
                    break;
                case "Ataque":
                    dados[0] = "FaseAtaque";
                    dados[1] = ultimoPaisClicado.nome;
                    dados[2] = paisClicado.nome;
                    break;
                case "Movimento":
                    dados[0] = "FaseMovimento";
                    dados[1] = ultimoPaisClicado.nome;
                    dados[2] = paisClicado.nome;
                    break;
                default:
                    dados[0] = "TrocaFase";
                    break;
            }

        }

        return dados;
    }


    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
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

    public void setExercitosPais(String pais, int qtdExercito) {
        Pais p = desenhaTabuleiro.getPais(pais);
        p.setExercitos(qtdExercito);
    }

    public void mostrarVizinhos(String pais, List<String> vizinhos) {
        Pais p = desenhaTabuleiro.getPais(pais);
        for (String vizinho : vizinhos) {
            Pais v = desenhaTabuleiro.getPais(vizinho);
            if(fase.equals("Ataque")){
                v.setCorTemp(Color.RED);
            }
            else if(fase.equals("Movimento")){
                v.setCorTemp(Color.GREEN);
            }
        }
    }

    public void handlePosicionamentoClick(int x, int y) {
        // Verifica se ele clicou no botão de terminar fase
        if(botaoClicado != null){
            switch(botaoClicado.getText()){
                case "Objetivo":
                    desenhaTabuleiro.alternarObjetivo();
                    repaint();
                    break;
                case "Dados":
                    sp = true;
                    notifyObservers();
                    break;
                case "Salvar":
                    save = true;
                    notifyObservers();
                    repaint();
                    break;

            }
        }
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


    public void handleAtaqueClick(int x, int y) {
        // Verifica se ele clicou no botão de terminar fase
        if(botaoClicado != null){
            switch(botaoClicado.getText()){
                case "Terminar Fase":
                    fase = "null";
                    notifyObservers();
                    repaint();
                    break;
                case "Objetivo":
                    desenhaTabuleiro.alternarObjetivo();
                    repaint();
                    break;
                case "Dados":
                    sp = true;
                    notifyObservers();
                    break;
            }
        }

        // Verifica se o clique foi em um país
        if (paisClicado != null) {

            // Verifica se já existe um país selecionado
            if(ultimoPaisClicado == null){
                ultimoPaisClicado = paisClicado;
                desenhaTabuleiro.resetarCores();
                notifyObservers();
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

    public void handleMovimentoClick(int x, int y) {
        // Verifica se ele clicou no botão de terminar fase
        if(botaoClicado != null){
            switch(botaoClicado.getText()){
                case "Terminar Fase":
                    fase = "null";
                    notifyObservers();
                    repaint();
                    break;
                case "Objetivo":
                    desenhaTabuleiro.alternarObjetivo();
                    repaint();
                    break;
                case "Dados":
                    sp = true;
                    notifyObservers();
                    break;
            }
        }

        // Verifica se o clique foi em um país
        if (paisClicado != null) {

            // Verifica se já existe um país selecionado
            if(ultimoPaisClicado == null){
                ultimoPaisClicado = paisClicado;
                desenhaTabuleiro.resetarCores();
                notifyObservers();
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

    public void handleMovimentoAtaqueClick(int x, int y){
        // Verifica se ele clicou no botão de terminar fase
        if(botaoClicado != null){
            if(botaoClicado.getText().equals("✓")){
                fase = "null";
                notifyObservers();
                repaint();
            }
            else if(botaoClicado.getText().equals("Objetivo")){
                desenhaTabuleiro.alternarObjetivo();
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

    public void resetTriangulos() {
        if(ultimoPaisClicado != null){
            ultimoPaisClicado.trianguloCima = null;
            ultimoPaisClicado.trianguloBaixo = null;
        }

    }

    public void resetBotoes() {
        desenhaTabuleiro.botoes.get(0).setVisivel();
        desenhaTabuleiro.botoes.get(3).setVisivel();
    }

    public void setFase(String fase){
        this.fase = fase;
    }

    public void exibeMao(Map<String, String> cartasJogadorAtual) {
        desenhaTabuleiro.setMao(cartasJogadorAtual);
    }

    public void setDados(List<Integer> ataque, List<Integer> defesa) {
        switch(ataque.size()){
            case 1:
                dadoAtaque1 = desenhaTabuleiro.getImagemDado(ataque.get(0), "ataque");
                dadoAtaque2 = desenhaTabuleiro.getImagemDado(-1, "ataque");
                dadoAtaque3 = desenhaTabuleiro.getImagemDado(-1, "ataque");
                break;
            case 2:
                dadoAtaque1 = desenhaTabuleiro.getImagemDado(ataque.get(0), "ataque");
                dadoAtaque2 = desenhaTabuleiro.getImagemDado(ataque.get(1), "ataque");
                dadoAtaque3 = desenhaTabuleiro.getImagemDado(-1, "ataque");
                break;
            case 3:
                dadoAtaque1 = desenhaTabuleiro.getImagemDado(ataque.get(0), "ataque");
                dadoAtaque2 = desenhaTabuleiro.getImagemDado(ataque.get(1), "ataque");
                dadoAtaque3 = desenhaTabuleiro.getImagemDado(ataque.get(2), "ataque");
                break;
        }

        switch(defesa.size()){
            case 1:
                dadoDefesa1 = desenhaTabuleiro.getImagemDado(defesa.get(0), "defesa");
                dadoDefesa2 = desenhaTabuleiro.getImagemDado(-1, "defesa");
                dadoDefesa3 = desenhaTabuleiro.getImagemDado(-1, "defesa");
                break;
            case 2:
                dadoDefesa1 = desenhaTabuleiro.getImagemDado(defesa.get(0), "defesa");
                dadoDefesa2 = desenhaTabuleiro.getImagemDado(defesa.get(1), "defesa");
                dadoDefesa3 = desenhaTabuleiro.getImagemDado(-1, "defesa");
                break;
            case 3:
                dadoDefesa1 = desenhaTabuleiro.getImagemDado(defesa.get(0), "defesa");
                dadoDefesa2 = desenhaTabuleiro.getImagemDado(defesa.get(1), "defesa");
                dadoDefesa3 = desenhaTabuleiro.getImagemDado(defesa.get(2), "defesa");
                break;
        }

        repaint();
    }

    public void setObjetivo(int objetivo) {
        desenhaTabuleiro.setObjetivo(objetivo);
    }

    public void setContinente(String s) {
        posicionamentoContinente = s;
    }


    public void fimJogo(String jogador) {
        JOptionPane.showMessageDialog(null, "O jogador " + jogador + " venceu o jogo!");
    }
}