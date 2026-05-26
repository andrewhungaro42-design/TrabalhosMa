package Flyweight;

import java.util.ArrayList;
import java.util.List;


public class Hamburguer {

    private final String               nome;
    private final List<ItemHamburguer> itens = new ArrayList<>();
    private final FabricaIngredientes  fabrica;

    public Hamburguer(String nome, FabricaIngredientes fabrica) {
        this.nome    = nome;
        this.fabrica = fabrica;
    }

    public void adicionarIngrediente(String nomeIngrediente,
                                     int    calorias,
                                     double precoUnitario,
                                     int    quantidade) {
        Ingrediente ing = fabrica.getIngrediente(nomeIngrediente, calorias, precoUnitario);
        itens.add(new ItemHamburguer(ing, quantidade));
    }


    public void exibir() {
        System.out.printf("%n🍔 %s%n", nome);
        System.out.println("─".repeat(65));
        itens.forEach(ItemHamburguer::exibir);
        System.out.printf("%-45s R$ %.2f%n", "  TOTAL", calcularPrecoTotal());
        System.out.println("─".repeat(65));
    }


    public double calcularPrecoTotal() {
        return itens.stream()
                .mapToDouble(ItemHamburguer::calcularPreco)
                .sum();
    }

    public String getNome() {
        return nome;
    }

    public List<ItemHamburguer> getItens() {
        return itens;
    }
}
