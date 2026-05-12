package Façade;


public class Estoque {

    public boolean verificarDisponibilidade(String item) {
        System.out.println("[Estoque] Verificando disponibilidade de: " + item);
        // Simulação: tudo disponível exceto "Bacon Especial"
        boolean disponivel = !item.equalsIgnoreCase("Bacon Especial");
        System.out.println("[Estoque] " + item + (disponivel ? " disponível." : " INDISPONÍVEL!"));
        return disponivel;
    }

    public void reservarIngrediente(String item) {
        System.out.println("[Estoque] Reservando ingrediente: " + item);
    }

    public void liberarIngrediente(String item) {
        System.out.println("[Estoque] Liberando ingrediente: " + item);
    }
}