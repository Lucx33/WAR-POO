package controller;
import java.util.List;
import model.ApiModel;
import view.IniciaInterface;
import view.Jogo;
import view.PlayersInfo;
import java.util.Map;

public class IniciaJogo implements Observer{
    static ApiModel partida;
    static IniciaInterface interfaceJogo;
    static PlayersInfo playersInfo;
    static Jogo telaJogo;

    Observable obs;
    Object lob[];
    String tipo;


    IniciaJogo(){
        // Cria uma instancia do PlayersInfo
        playersInfo = new PlayersInfo();

        //Inicia Interface
        interfaceJogo = new IniciaInterface(playersInfo);

        // Cria uma instancia do jogo
        partida = ApiModel.getInstance();

        // Adiciona o IniciaJogo como observador do PlayersInfo
        playersInfo.addObserver(this);
        partida.addObserver(this);

    }

    public static void main(String[] args) {
        new IniciaJogo();
    }
    @Override
    public void notify(Observable arg) {
        obs = arg;
        lob = (Object []) obs.get();
        tipo = (String) lob[0];

        switch(tipo){
            case "NovoJogo":
                List<String> playerNames = (List<String>) lob[1];
                List<String> playerColors = (List<String>) lob[2];
                updateInfo(playerNames, playerColors);
                updateTurno();
                break;
            case "AtualizaExercitos":
                int qtd = (Integer) lob[1];
                telaJogo.setExercitos(qtd);
                telaJogo.repaint();
                break;

            case "FasePosicionamento":
                String pais = (String) lob[1];
                String sinal = (String) lob[2];
                partida.manipulaExercitos(pais, sinal);
                telaJogo.setExercitos(partida.getExercitosAtuais());
                telaJogo.repaint();
                if(partida.getExercitosAtuais() == 0){
                    telaJogo.trocaFase();
                }
                break;

            case "FaseAtaque":
                String paisAtacante = (String) lob[1];
                String paisDefensor = (String) lob[2];
                List<String> vizinhos = partida.getVizinhos(paisAtacante);

                if(vizinhos.contains(paisDefensor.toLowerCase())){
                    partida.validaAtaque(paisAtacante, paisDefensor);
                }
                else{
                    telaJogo.mostrarVizinhos(paisAtacante, vizinhos);
                }
                telaJogo.repaint();
                break;

            case "AtualizaTerritorio":
                String nome = (String) lob[1];
                int idJogadorDono = (Integer) lob[2];
                int qtdExercito = (Integer) lob[3];
                telaJogo.setPais(nome, idJogadorDono, qtdExercito);
                telaJogo.repaint();
                break;

        }
    }


    public void updateInfo(List<String> playerNames, List<String> playerColors) {
        partida.setGame(playerNames, playerColors);
        partida.addObserverToAllTerritories(this);

        telaJogo = new Jogo(partida.getNomesJogadores(), partida.getCoresJogadores());
        telaJogo.addObserver(this);


        int qtd_jogadores = 0;
        for (String player : playerNames) {
            qtd_jogadores++;
            telaJogo.setCorDono(partida.getTerritoriosPorDono(player), playerColors.get(playerNames.indexOf(player)));
        }
        telaJogo.repaint();
    }

    public void updateTurno(){
        partida.turno(partida.getJogadorAtual());
        telaJogo.atualizaJogadorAtual(partida.getCorJogadorAtual()); // Assumindo que você adicionará este método na tela do jogo
        telaJogo.setExercitos(partida.getExercitosAtuais());
        telaJogo.repaint();
    }



}