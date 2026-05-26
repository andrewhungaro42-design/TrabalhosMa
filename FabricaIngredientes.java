package Flyweight;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class FabricaIngredientes {


    private final Map<String, Ingrediente> cache = new HashMap<>();

    public Ingrediente getIngrediente(String nome, int calorias, double precoUnitario) {
        if (!cache.containsKey(nome)) {
            cache.put(nome, new Ingredienteconcreto(nome, calorias, precoUnitario));
        } else {
            System.out.printf("[Flyweight] Reutilizando ingrediente existente: %s%n", nome);
        }
        return cache.get(nome);
    }


    public int getTotalIngredientesCriados() {
        return cache.size();
    }


    public void listarIngredientes() {
        System.out.println("\n=== Ingredientes no cache (Flyweights) ===");
        cache.forEach((nome, ing) ->
                System.out.printf("  - %s  |  instância: %s%n", nome, ing)
        );
    }


    public Collection<Ingrediente> getCache() {
        return cache.values();
    }


    public boolean existeNoCache(String nome) {
        return cache.containsKey(nome);
    }
}
