package controller;

import model.ApiModel;
import view.IniciaInterface;
import view.PlayersInfo;

import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        ControladorJogo jogo = new ControladorJogo();
        PlayersInfo playersInfo = new PlayersInfo();
        jogo.interfaceJogo = new IniciaInterface(playersInfo);

        List<String> names = new ArrayList<>();
        names.add("Cano");
        names.add("John");
        names.add("André");

        List<String> cores = new ArrayList<>();
        cores.add("Branco");
        cores.add("Vermelho");
        cores.add("Verde");

        playersInfo.setPlayersInfo(names, cores);

        // Cria uma instancia do jogo
        jogo.partida = ApiModel.getInstance();


        // Adiciona o IniciaJogo como observador do PlayersInfo
        playersInfo.addObserver(jogo);
        jogo.partida.addObserver(jogo);
        jogo.handleNovoJogo(names, cores);
    }
}
