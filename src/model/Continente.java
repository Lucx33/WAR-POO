package model;

import java.util.ArrayList;
import java.util.List;

class Continente {
    String nome;
    String nomeCurto;
    List<Territorio> territorios;
    Jogador jogadorDono;  // Pode ser nulo se o continente não tiver dono

    int qtdExer; // Quantidade de exercicitos caso o continente tenha dono
    /* AmeSul = 2, Eur = 5, Asia = 7, AmeNor = 5, Afr = 3, Oce = 2*/
    Continente(String nome) {
        this.nome = nome;
        this.nomeCurto = getNomeCurto(nome);
        this.territorios = new ArrayList<>();
        this.jogadorDono = null;  // Inicialmente, o continente não tem dono
    }

    String getNome() {
        return nome;
    }

    void adicionarTerritorio(Territorio territorio) {
        territorios.add(territorio);
    }

    List<Territorio> getTerritorios() {
        return territorios;
    }

    Jogador getJogadorDono() {
        return jogadorDono;
    }

    void setJogadorDono(Jogador jogadorDono) {
        this.jogadorDono = jogadorDono;
    }
    
    int getBonusExercitos(String nomeContinete) {
    	switch (nomeContinete) {
    	 case "America do Norte":
    	        return 5;
    	    case "America do Sul":
    	        return 2;
    	    case "Africa":
    	        return 3;
    	    case "Europa":
    	        return 5;
    	    case "Asia":
    	        return 7;
    	    case "Oceania":
    	        return 2;
    	    default:
    	        return 0;       
    	}
    }

    String getNomeCurto(String nomeContinete) {
        return switch (nomeContinete) {
            case "America do Norte" -> "an";
            case "America do Sul" -> "asl";
            case "Africa" -> "af";
            case "Europa" -> "eu";
            case "Asia" -> "as";
            case "Oceania" -> "oc";
            default -> null;
        };
    }
    
    boolean contemTerritorio(String nomeTerritorio) {
        for (Territorio territorio : territorios) {
            if (territorio.getNome().equalsIgnoreCase(nomeTerritorio)) {
                return true;
            }
        }
        return false;
    }

    String getNomeCurto() {
        return nomeCurto;
    }
   
}