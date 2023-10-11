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

