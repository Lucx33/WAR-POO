package model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Tabuleiro {

    Map<String, List<String>> fronteiras = new HashMap<>();
    static List <Continente> continentes = new ArrayList<>();


    public Tabuleiro() {
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
    }

    private void initFronteiras() {
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
        fronteiras.put("franca", List.of("espanha", "italia", "suecia", "argelia"));
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
        fronteiras.put("arabiasaudita", List.of("jordania", "iraque", "somalia"));
        fronteiras.put("bangladesh", List.of("coreiadosul", "india", "tailandia", "indonesia"));
        fronteiras.put("china", List.of("turquia", "cazaquistao", "mongolia", "coreiadonorte", "coreiadosul", "paquistao", "india"));
        fronteiras.put("coreiadonorte", List.of("japao", "china", "coreiadosul"));
        fronteiras.put("coreiadosul", List.of("china", "coreiadonorte", "india", "bangladesh", "tailandia"));
        fronteiras.put("india", List.of("paquistao", "china", "coreiadosul", "bangladesh"));
        fronteiras.put("ira", List.of("iraque", "siria", "paquistao"));
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
        for(Continente continente : continentes){
            for(Territorio territorio : continente.getTerritorios()){
                if(territorio.getNome().equals(nome)){
                    return territorio;
                }
            }
        }
        return null;
    }


}