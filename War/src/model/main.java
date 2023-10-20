package model;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        
        // Vamos listar os continentes e seus territórios
        for (Continente continente : tabuleiro.continentes) {
            System.out.println("Continente: " + continente.getNome());
            
            for (Territorio territorio : continente.getTerritorios()) {
                System.out.println("- Território: " + territorio.getNome() + " no Continente: " + territorio.getNome());
            }
        }
        
        // Vamos adicionar algumas fronteiras
        Continente asia = tabuleiro.continentes.get(2);
        Territorio territorio1 = asia.getTerritorios().get(0);
        Territorio territorio2 = asia.getTerritorios().get(1);
        
        System.out.println("");
        
        List <String> vizinhos = tabuleiro.fronteiras.get("letonia");
        
        
        System.out.println(vizinhos);
     


    }
}
