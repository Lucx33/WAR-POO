package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carta{
    private final String territorio;
    private final String formaGeometrica;

    public Carta(String territorio, String formaGeometrica) {
        this.territorio = territorio;
        this.formaGeometrica = formaGeometrica;
    }

    private static List<Carta> criarBaralho() {
        String[] territorios = {"africadosul", "angola", "argelia", "egito", "nigeria", "somalia", "alasca", "calgary",
        "california", "groelandia", "mexico", "novayork", "quebec", "texas", "vancouver", "arabiasaudita", "bangladesh", "cazaquistao",
        "china", "coreiadonorte","coreiadosul", "estonia", "india", "ira", "iraque", "japao", "jordania", "letonia",
        "mongolia", "paquistao", "russia", "siberia", "siria", "tailandia", "turquia", "argentina", "brasil", "peru",
        "venezuela", "espanha", "franca", "italia", "polonia", "reinounido", "romenia", "suecia", "ucrania", "australia",
        "indonesia", "novazelandia", "perth", "c", "c"};
        String[] formasGeometricas = {"Triângulo", "Quadrado", "Círculo", "Triângulo", "Círculo", "Quadrado", "Triângulo", "Círculo",
        "Quadrado", "Círculo", "Quadrado", "Quadrado", "Círculo", "Triângulo", "Triângulo", "Círculo", "Círculo", "Círculo",
        "Quadrado", "Quadrado", "Triângulo", "Círculo", "Triângulo", "Quadrado", "Triângulo", "Círculo", "Quadrado", "Quadrado",
        "Triângulo", "Círculo", "Triângulo", "Quadrado", "Quadrado", "Triângulo", "Triângulo", "Quadrado", "Círculo", "Triângulo",
        "Triângulo", "Círculo", "Triângulo", "Quadrado", "Triângulo", "Círculo", "Triângulo", "Quadrado", "Círculo", "Triângulo",
        "Triângulo", "Quadrado", "Círculo", "?", "?"};

        List<Carta> baralho = new ArrayList<>();
        for (String territorio : territorios) {
            for (String forma : formasGeometricas) {
                baralho.add(new Carta(territorio, forma));
            }
        }
        return baralho;
    }
}

