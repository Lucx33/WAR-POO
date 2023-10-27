package controller;
import java.util.List;
import model.ApiModel;
import view.IniciaInterface;
import view.Jogo;
import view.PlayersInfo;

public class IniciaJogo implements ObserverSetPlayersInfo, ObserverTurno {
    static ApiModel partida;
    static IniciaInterface interfaceJogo;
    static PlayersInfo playersInfo;
    static Jogo telaJogo;
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
    public void updateInfo() {
        System.out.println("IniciaJogo: update");
        List<String> playerNames = playersInfo.getNames();
        List<String> playerColors = playersInfo.getColors();
        partida.setGame(playerNames, playerColors);
        telaJogo = new Jogo(playerNames, playerColors);
        for (String player : playerNames) {
            telaJogo.setCorDono(partida.getTerritoriosPorDono(player), playerColors.get(playerNames.indexOf(player)));
        }
        telaJogo.repaint();
    }

    @Override
    public void updateTurno(){
        partida.turno(partida.getJogadorAtual());
        partida.proximoTurno();
        telaJogo.atualizaJogadorAtual(partida.getJogadorAtual()); // Assumindo que você adicionará este método na tela do jogo
    }



}
