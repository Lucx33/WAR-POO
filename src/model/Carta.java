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

    public static List<Carta> criarBaralho() {
        String[] territorios = {"africadosul", "angola", "argelia", "egito", "nigeria", "somalia", "alasca", "calgary",
                "california", "groelandia", "mexico", "novayork", "quebec", "texas", "vancouver", "arabiasaudita", "bangladesh", "cazaquistao",
                "china", "coreiadonorte","coreiadosul", "estonia", "india", "ira", "iraque", "japao", "jordania", "letonia",
                "mongolia", "paquistao", "russia", "siberia", "siria", "tailandia", "turquia", "argentina", "brasil", "peru",
                "venezuela", "espanha", "franca", "italia", "polonia", "reinounido", "romenia", "suecia", "ucrania", "australia",
                "indonesia", "novazelandia", "perth", "c", "c"};
        String[] formas = {"Triângulo", "Quadrado", "Círculo", "Triângulo", "Círculo", "Quadrado", "Triângulo", "Círculo",
                "Quadrado", "Círculo", "Quadrado", "Quadrado", "Círculo", "Triângulo", "Triângulo", "Círculo", "Círculo", "Círculo",
                "Quadrado", "Quadrado", "Triângulo", "Círculo", "Triângulo", "Quadrado", "Triângulo", "Círculo", "Quadrado", "Quadrado",
                "Triângulo", "Círculo", "Triângulo", "Quadrado", "Quadrado", "Triângulo", "Triângulo", "Quadrado", "Círculo", "Triângulo",
                "Triângulo", "Círculo", "Triângulo", "Quadrado", "Triângulo", "Círculo", "Triângulo", "Quadrado", "Círculo", "Triângulo",
                "Triângulo", "Quadrado", "Círculo", "?", "?"};

        List<Carta> baralho = new ArrayList<>();
        for (int i = 0; i < territorios.length; i++) {
            baralho.add(new Carta(territorios[i], formas[i]));
        }
        return baralho;
    }

    public String getTerritorio() {
        return territorio;
    }

    public String getFormaGeometrica() {
        return formaGeometrica;
    }

    public static List<Carta> shuf(List<Carta> baralho) {
        Collections.shuffle(baralho);
        return baralho;
    }
}

