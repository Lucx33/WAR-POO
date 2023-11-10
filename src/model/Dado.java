package model;

import java.util.Arrays;
import java.util.Random;

import static java.util.Arrays.sort;

class Dado {
    Random random;

    Dado() {
        random = new Random();
    }

    int rolar() {
        return random.nextInt(6) + 1;
    }

    int[] DadosAtaque(int qtd_exercitosAtaque){
        int numDados = Math.min(qtd_exercitosAtaque, 3);
        int[] resultadosAtaque = new int[numDados];

        for (int i = 0; i < numDados; i++) {
            resultadosAtaque[i] = rolar();
        }

        sort(resultadosAtaque);

        return resultadosAtaque;
    }

    int[] DadosDefesa(int qtd_exercitosDefesa) {
        int numDados = Math.min(qtd_exercitosDefesa, 3);
        int[] resultadosDefesa = new int[numDados];

        for (int i = 0; i < numDados; i++) {
            resultadosDefesa[i] = rolar();
        }

        sort(resultadosDefesa);

        return resultadosDefesa;
    }

    int[] DadosLancados(int qtd_exercitos, String Defesa_or_Ataque){
        int numDados;
        if (Defesa_or_Ataque == "Ataque") {
            numDados = Math.min(qtd_exercitos - 1, 3);
        }
        else { //Defesa
            numDados = Math.min(qtd_exercitos, 3);
        }
        int[] resultadosLancados = new int[numDados];

        for (int i = 0; i < numDados; i++) {
            resultadosLancados[i] = rolar();
        }

        sort(resultadosLancados);

        return resultadosLancados;
    }
    void compararResultados(int[] resultadosDefesa, int[] resultadosAtaque) {
        int numDefesa = resultadosDefesa.length;
        int numAtaque = resultadosAtaque.length;
        int n = Math.min(numDefesa, numAtaque);

        Arrays.sort(resultadosDefesa);
        Arrays.sort(resultadosAtaque);

        for (int i = 0; i < n; i++) {
            if (resultadosDefesa[i] >= resultadosAtaque[i]) {
                System.out.println("Defesa ganha!");
            }
            else {
                System.out.println("Ataque ganha!");
            }
        }
    }
}