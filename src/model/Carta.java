package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Carta{
    final String territorio;
    final String formaGeometrica;

    Carta(String territorio, String formaGeometrica) {
        this.territorio = territorio;
        this.formaGeometrica = formaGeometrica;
    }

    static List<Carta> shuf(List<Carta> baralho) {
        Collections.shuffle(baralho);
        return baralho;
    }

    static List<Carta> criarBaralho() {
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

    static Carta comprarCarta(List<Carta> baralho) {
        return baralho.remove(0);
    }

    static void addBaralho(List<Carta> baralho, Carta carta) {
        baralho.add(carta);
    }

    static boolean verificaCartas(List<Carta> cartasJogador) {

        int circuloCount = 0;
        int trianguloCount = 0;
        int quadradoCount = 0;

        for (Carta carta : cartasJogador) {
            switch (carta.getFormaGeometrica()) {
                case "Círculo":
                    circuloCount++;
                    break;
                case "Triângulo":
                    trianguloCount++;
                    break;
                case "Quadrado":
                    quadradoCount++;
                    break;
            }
        }

        if (circuloCount == 3 || trianguloCount == 3 || quadradoCount == 3) {
            return true;
        } else return circuloCount > 0 && trianguloCount > 0 && quadradoCount > 0;
    }

    String getTerritorio() {
        return territorio;
    }

    String getFormaGeometrica() {
        return formaGeometrica;
    }
}

