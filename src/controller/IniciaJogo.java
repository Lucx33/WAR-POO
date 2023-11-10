package controller;
import java.awt.*;
import java.util.List;
import model.ApiModel;
import model.Turno;
import view.IniciaInterface;
import view.Jogo;
import view.PlayersInfo;

public class IniciaJogo implements Observer{
    static ApiModel partida;
    static IniciaInterface interfaceJogo;
    static PlayersInfo playersInfo;
    static Jogo telaJogo;

    Observable obs;
    IniciaJogo(){
        // Cria uma instancia do PlayersInfo
        playersInfo = new PlayersInfo();

        //Inicia Interface
        interfaceJogo = new IniciaInterface(playersInfo);

        // Cria uma instancia do jogo
        partida = ApiModel.getInstance();

        // Adiciona o IniciaJogo como observador do PlayersInfo
        playersInfo.addObserver(this);
    }

    public static void main(String[] args) {
        new IniciaJogo();
    }
    @Override
    public void notify(Observable arg) {
        if (arg instanceof PlayersInfo) {
            updateInfo();
        } else if (arg instanceof Turno) {
            updateTurno();
        }
    }


    public void updateInfo() {
        List<String> playerNames = playersInfo.getNames();
        List<String> playerColors = playersInfo.getColors();
        partida.setGame(playerNames, playerColors);
        telaJogo = new Jogo(playerNames, playerColors);
        int qtd_jogadores = 0;
        for (String player : playerNames) {
            qtd_jogadores++;
            telaJogo.setCorDono(partida.getTerritoriosPorDono(player), playerColors.get(playerNames.indexOf(player)));
        }
        telaJogo.repaint(); 
    }

    public void updateTurno(){
        partida.turno(partida.getJogadorAtual());
        partida.proximoTurno();
        telaJogo.atualizaJogadorAtual(partida.getJogadorAtual()); // Assumindo que você adicionará este método na tela do jogo
    }

}