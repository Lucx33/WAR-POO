package controller;
import java.util.List;
import model.ApiModel;
import view.IniciaInterface;
import view.Jogo;
import view.PlayersInfo;

public class IniciaJogo implements ObserverSetPlayersInfo {
    static ApiModel partida;
    IniciaInterface interfaceJogo;
    static PlayersInfo playersInfo;

    Jogo jogo;
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
    public void update() {
        System.out.println("IniciaJogo: update");
        List<String> playerNames = playersInfo.getNames();
        List<String> playerColors = playersInfo.getColors();
        partida.setGame(playerNames, playerColors);
        jogo = new Jogo(playerNames, playerColors);
        for (String player : playerNames) {
            jogo.setCorDono(partida.getTerritoriosPorDono(player), playerColors.get(playerNames.indexOf(player)));
        }
        jogo.repaint();
    }


}
