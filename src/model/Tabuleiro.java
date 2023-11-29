package model;


import controller.Observable;
import controller.Observer;

import java.util.*;


class Tabuleiro implements Observable{

    static Map<String, List<String>> fronteiras = new HashMap<>();
    static List <Continente> continentes = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    String mudanca;
    Tabuleiro(){

    }

    static void resetAllMovimentadosVitoria() {
        for (Continente continente : continentes) {
            for (Territorio territorio : continente.getTerritorios()) {
                territorio.resetMovimentadosVitoria();
            }
        }
    }

    static Continente buscaContinentePais(String pais) {
        for (Continente continente : continentes) {
            for (Territorio territorio : continente.getTerritorios()) {
                if (territorio.getNome().equals(pais)) {
                    return continente;
                }
            }
        }
        return null;
    }

    static Continente buscaContinente(String nome){
        for(Continente continente : continentes){
            if(continente.getNome().equals(nome)){
                return continente;
            }
        }
        return null;
    }

    Tabuleiro criaTabuleiro() {
        String[] nomesPaises = {"africadosul", "angola", "argelia", "egito", "nigeria", "somalia", "alasca", "calgary",
                "california", "groelandia", "mexico", "novayork", "quebec", "texas", "vancouver", "arabiasaudita", "bangladesh", "cazaquistao",
                "china", "coreiadonorte","coreiadosul", "estonia", "india", "ira", "iraque", "japao", "jordania", "letonia",
                "mongolia", "paquistao", "russia", "siberia", "siria", "tailandia", "turquia", "argentina", "brasil", "peru",
                "venezuela", "espanha", "franca", "italia", "polonia", "reinounido", "romenia", "suecia", "ucrania", "australia",
                "indonesia", "novazelandia", "perth"};

        /*Afr = 6  AmeNor = 9, Asia = 20, AmeSul = 4, Afr = 4, Europa = 8 , Oce = 4*/

        String[] nomesContinentes = {"Africa","America do Norte","Asia","America do Sul","Europa","Oceania"};
        int[] continentesPaises = {6, 9, 20, 4, 8, 4};

        int indice = 0;

        for (int i = 0; i < continentesPaises.length; i++) {
            Continente continente = new Continente(nomesContinentes[i]);
            for (int j = 0; j < continentesPaises[i]; j++) {
                continente.adicionarTerritorio (new Territorio(nomesPaises[indice]));
                indice++;
            }
            continentes.add(continente);
        }

        initFronteiras();
        return this;
    }

    void initFronteiras() {
        // AMERICA DO SUL
        fronteiras.put("brasil", List.of("argentina", "peru", "venezuela", "nigeria"));
        fronteiras.put("argentina", List.of("brasil", "peru"));
        fronteiras.put("peru", List.of("brasil", "argentina", "venezuela"));
        fronteiras.put("venezuela", List.of("mexico", "brasil", "peru"));

        // AMERICA DO NORTE
        fronteiras.put("mexico", List.of("venezuela", "california", "texas"));
        fronteiras.put("california", List.of("vancouver", "texas", "mexico"));
        fronteiras.put("texas", List.of("mexico", "california", "vancouver", "novayork", "quebec"));
        fronteiras.put("vancouver", List.of("california", "texas", "quebec", "alasca", "calgary"));
        fronteiras.put("novayork", List.of("texas", "quebec"));
        fronteiras.put("quebec", List.of("novayork", "texas", "vancouver", "groelandia"));
        fronteiras.put("alasca", List.of("vancouver", "calgary", "siberia"));
        fronteiras.put("calgary", List.of("vancouver", "alasca", "groelandia"));
        fronteiras.put("groelandia", List.of("calgary", "quebec", "reinounido"));

        // EUROPA
        fronteiras.put("reinounido", List.of("groelandia", "franca"));
        fronteiras.put("franca", List.of("espanha", "italia", "suecia", "reinounido"));
        fronteiras.put("espanha", List.of("argelia", "franca"));
        fronteiras.put("italia", List.of("franca", "argelia", "polonia", "romenia", "suecia"));
        fronteiras.put("suecia", List.of("franca", "italia", "letonia", "estonia"));
        fronteiras.put("polonia", List.of("italia", "letonia", "romenia", "ucrania"));
        fronteiras.put("romenia", List.of("egito", "italia", "polonia", "ucrania"));
        fronteiras.put("ucrania", List.of("polonia", "romenia", "letonia", "turquia"));

        // ASIA
        fronteiras.put("letonia", List.of("polonia", "ucrania", "suecia", "estonia", "turquia", "cazaquistao", "russia"));
        fronteiras.put("estonia", List.of("suecia", "russia", "letonia"));
        fronteiras.put("turquia", List.of("letonia", "cazaquistao", "ucrania", "paquistao", "siria", "china"));
        fronteiras.put("siberia", List.of("russia", "cazaquistao", "alasca"));
        fronteiras.put("russia", List.of("estonia", "letonia", "cazaquistao", "siberia"));
        fronteiras.put("cazaquistao", List.of("letonia", "turquia", "russia", "siberia", "japao", "china", "mongolia"));
        fronteiras.put("paquistao", List.of("turquia", "india", "china", "siria", "ira"));
        fronteiras.put("siria", List.of("turquia", "ira", "jordania", "iraque","paquistao"));
        fronteiras.put("arabiasaudita", List.of("jordania", "iraque", "somalia"));
        fronteiras.put("bangladesh", List.of("coreiadosul", "india", "tailandia", "indonesia"));
        fronteiras.put("china", List.of("turquia", "cazaquistao", "mongolia", "coreiadonorte", "coreiadosul", "paquistao", "india"));
        fronteiras.put("coreiadonorte", List.of("japao", "china", "coreiadosul"));
        fronteiras.put("coreiadosul", List.of("china", "coreiadonorte", "india", "bangladesh", "tailandia"));
        fronteiras.put("india", List.of("paquistao", "china", "coreiadosul", "bangladesh", "indonesia"));
        fronteiras.put("ira", List.of("paquistao", "siria", "iraque"));
        fronteiras.put("iraque", List.of("arabiasaudita", "jordania", "siria", "ira"));
        fronteiras.put("japao", List.of("cazaquistao", "mongolia", "coreiadonorte"));
        fronteiras.put("jordania", List.of("arabiasaudita", "siria", "iraque", "egito"));
        fronteiras.put("mongolia", List.of("china", "japao", "cazaquistao"));
        fronteiras.put("tailandia", List.of("bangladesh", "coreiadosul"));
        

        // AFRICA
        fronteiras.put("argelia", List.of("espanha", "italia", "nigeria", "egito"));
        fronteiras.put("nigeria", List.of("brasil", "argelia", "egito", "somalia", "angola"));
        fronteiras.put("angola", List.of("africadosul", "somalia", "nigeria"));
        fronteiras.put("egito", List.of("romenia", "argelia", "nigeria", "somalia", "jordania"));
        fronteiras.put("somalia", List.of("egito", "nigeria", "angola", "africadosul", "arabiasaudita"));
        fronteiras.put("africadosul", List.of("angola", "somalia"));

        // OCEANIA
        fronteiras.put("australia", List.of("indonesia", "novazelandia", "perth"));
        fronteiras.put("indonesia", List.of("australia", "novazelandia", "bangladesh", "india"));
        fronteiras.put("novazelandia", List.of("indonesia", "australia"));
        fronteiras.put("perth", List.of("australia"));
    }

    static Territorio buscaTerritorio(String nome){
        nome = nome.toLowerCase();
        for(Continente continente : continentes){
            for(Territorio territorio : continente.getTerritorios()){
                if(territorio.getNome().equals(nome)){
                    return territorio;
                }
            }
        }
        return null;
    }

    boolean fazFronteira(String territorio1, String territorio2) {
        List<String> vizinhos = fronteiras.get(territorio1);
        return vizinhos != null && vizinhos.contains(territorio2);
    }

    void imprimeTabuleiro() {
        for (Continente continente : continentes) {
            // Imprimir o nome do continente
            System.out.println("Continente: " + continente.getNome());

            // Imprimir os territórios deste continente
            for (Territorio territorio : continente.getTerritorios()) {
                System.out.println("  Território: " + territorio.getNome() + " " + territorio.getIdJogadorDono() + " " + territorio.getQtdExercito());

                // Imprimir as fronteiras deste território
                List<String> vizinhos = fronteiras.get(territorio.getNome());
                System.out.print("    Fronteiras: ");
                for (String vizinho : vizinhos) {
                    System.out.print(vizinho + ", ");
                }
                System.out.println();  // Para mudar de linha após imprimir todos os vizinhos
            }
            System.out.println();  // Uma linha em branco entre continentes para melhor visualização
        }
    }

    void validaAtaque(String nome1, String nome2, Dado dado){
        Territorio atacante = Tabuleiro.buscaTerritorio(nome1);
        Territorio defensor = Tabuleiro.buscaTerritorio(nome2);
        if(fazFronteira(atacante.nome, defensor.nome) && atacante.getIdJogadorDono() != defensor.getIdJogadorDono()){
            this.ataque(atacante, defensor, dado);
        }
    }

    void validaMovimento(String nome1, String nome2) {
        Territorio origem = Tabuleiro.buscaTerritorio(nome1);
        Territorio destino = Tabuleiro.buscaTerritorio(nome2);

        if (origem.getIdJogadorDono() == destino.getIdJogadorDono()) {
            if(fazFronteira(origem.nome, destino.nome)){
                Territorio.movimenta(origem, destino);
            }
            else{
                System.out.println("Territorios não fazem fronteira");
            }
        }
        else{
            System.out.println("Territorios não pertencem ao mesmo jogador");
        }
    }

    void adicionaExercito(String nomeTerritorio, int qtdExercito) {
        Territorio territorio = buscaTerritorio(nomeTerritorio);
        territorio.setQtdExercito(territorio.getQtdExercito() + qtdExercito);
    }

    static List<Continente> getContinentes() {
        return continentes;
    }

    List<String> vizinhos(String nomeTerritorio) {
        return fronteiras.get(nomeTerritorio);
    }

    void ataque(Territorio atacante, Territorio defensor, Dado dado){
        int qtdAtaque = Math.min(3,atacante.getQtdExercito() - 1);
        int qtdDefesa = Math.min(3,defensor.getQtdExercito());
        List<Integer> resultado = dado.lancamentoDados(qtdAtaque, qtdDefesa);
        List<Integer> dadosAtaque = resultado.subList(0, qtdAtaque);
        List<Integer> dadosDefesa = resultado.subList(qtdAtaque, resultado.size());

        int n = Math.min(qtdAtaque, qtdDefesa);

        Collections.sort(dadosAtaque);
        Collections.sort(dadosDefesa);

        int menorTamanho = Math.min(dadosAtaque.size(), dadosDefesa.size());

        for (int i = menorTamanho; i > 0; i--) {
            int valorAtaque = dadosAtaque.get(dadosAtaque.size() - i);
            int valorDefesa = dadosDefesa.get(dadosDefesa.size() - i);

            if (valorAtaque > valorDefesa) {
                defensor.setQtdExercito(defensor.getQtdExercito() - 1);
            } else {
                atacante.setQtdExercito(atacante.getQtdExercito() - 1);
            }
        }

        // Verifica se o defensor foi derrotado
        if (defensor.getQtdExercito() == 0) {
            defensor.setIdJogadorDono(atacante.getIdJogadorDono());
            defensor.setQtdExercito(1); // Move um exército para o território conquistado
            atacante.setQtdExercito(atacante.getQtdExercito() - 1); // Reduz um exército do atacante
            mudanca = defensor.getNome();
            // Atualiza os observadores sobre a mudança
            notifyObservers();
        }

    }


    /**
     * Verifica se todos os territórios de um continente estão presentes em uma lista.
     *
     * @param territoriosJogador Lista de nomes de territórios.
     * @param nomeContinente Nome do continente a ser verificado.
     * @return `true` se todos os territórios do continente estão na lista, `false` caso contrário.
     */
    boolean verificaTerritoriosContinente(List<String> territoriosJogador, String nomeContinente) {
        Continente continente = buscaContinentePais(nomeContinente);
        System.out.println(continente.getNome());
        for (Territorio territorio : continente.getTerritorios()) {
            if (!territoriosJogador.contains(territorio.getNome().toLowerCase())) {
                return false; // Território do continente não encontrado na lista
            }
        }
        return true; // Todos os territórios do continente estão na lista
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public Object get() {
        Object dados[]=new Object[5];
        dados[0] = "MudancaDeDono";
        dados[1] = mudanca;
        return dados;
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.notify(this);
        }
    }

    public void simulaAtaque(String paisAtacante, String paisDefensor, Dado dado, List<Integer> dadosAtaque, List<Integer> dadosDefesa) {
        Territorio atacante = Tabuleiro.buscaTerritorio(paisAtacante);
        Territorio defensor = Tabuleiro.buscaTerritorio(paisDefensor);
        if(atacante.getIdJogadorDono() == defensor.getIdJogadorDono()) {
            System.out.println("Territorios do mesmo jogador");
        }
        else if(fazFronteira(atacante.nome, defensor.nome)){
            this.hack(atacante, defensor, dado, dadosAtaque, dadosDefesa);
        }
        else{
            System.out.println("Territorios não fazem fronteira");
        }
    }

    private void hack(Territorio atacante, Territorio defensor, Dado dado, List<Integer> dadosAtaque, List<Integer> dadosDefesa) {
        int qtdAtaque = Math.min(3,atacante.getQtdExercito() - 1);
        int qtdDefesa = Math.min(3,defensor.getQtdExercito());
        List<Integer> resultado = dado.simulacaoDados(qtdAtaque, qtdDefesa, dadosAtaque, dadosDefesa);
        List<Integer> dadosAtaqueSimulacao = resultado.subList(0, qtdAtaque);
        List<Integer> dadosDefesaSimulacao = resultado.subList(qtdAtaque, resultado.size());

        int n = Math.min(qtdAtaque, qtdDefesa);

        Collections.sort(dadosAtaqueSimulacao);
        Collections.sort(dadosDefesaSimulacao);

        int menorTamanho = Math.min(dadosAtaqueSimulacao.size(), dadosDefesaSimulacao.size());

        for (int i = menorTamanho; i > 0; i--) {
            int valorAtaque = dadosAtaqueSimulacao.get(dadosAtaqueSimulacao.size() - i);
            int valorDefesa = dadosDefesaSimulacao.get(dadosDefesaSimulacao.size() - i);

            if (valorAtaque > valorDefesa) {
                defensor.setQtdExercito(defensor.getQtdExercito() - 1);
            } else {
                atacante.setQtdExercito(atacante.getQtdExercito() - 1);
            }
        }

        // Verifica se o defensor foi derrotado
        if (defensor.getQtdExercito() == 0) {
            defensor.setIdJogadorDono(atacante.getIdJogadorDono());
            defensor.setQtdExercito(1); // Move um exército para o território conquistado
            atacante.setQtdExercito(atacante.getQtdExercito() - 1); // Reduz um exército do atacante
            mudanca = defensor.getNome();
            // Atualiza os observadores sobre a mudança
            notifyObservers();
        }
    }
}