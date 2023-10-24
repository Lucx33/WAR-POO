package model;

import java.util.Random;

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

        return resultadosAtaque;
    }

    int[] DadosDefesa(int qtd_exercitosDefesa) {
        int numDados = Math.min(qtd_exercitosDefesa, 3);
        int[] resultadosDefesa = new int[numDados];

        for (int i = 0; i < numDados; i++) {
            resultadosDefesa[i] = rolar();
        }

        return resultadosDefesa;
    }
}