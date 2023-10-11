package model;

import java.util.Random;

class Dado {
    private Random random;

    public Dado() {
        random = new Random();
    }

    public int rolar() {
        return random.nextInt(6) + 1;
    }
    
    public int[] DadosAtaque(int qtd_exercitosAtaque){
        int numDados = Math.min(qtd_exercitosAtaque, 3);
        int[] resultadosAtaque = new int[numDados];

        for (int i = 0; i < numDados; i++) {
            resultadosAtaque[i] = rolar();
        }

        return resultadosAtaque;
    }

    public int[] DadosDefesa(int qtd_exercitosDefesa) {
        int numDados = Math.min(qtd_exercitosDefesa, 3);
        int[] resultadosDefesa = new int[numDados];

        for (int i = 0; i < numDados; i++) {
            resultadosDefesa[i] = rolar();
        }

        return resultadosDefesa;
    }
}