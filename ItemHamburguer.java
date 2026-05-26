package Flyweight;

public class ItemHamburguer {

    private final Ingrediente ingrediente;

    private final int quantidade;

    public ItemHamburguer(Ingrediente ingrediente, int quantidade) {
        this.ingrediente = ingrediente;
        this.quantidade  = quantidade;
    }


    public void exibir() {
        ingrediente.aplicar(quantidade);
    }


    public double calcularPreco() {
        return ingrediente.getPrecoUnitario() * quantidade;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
