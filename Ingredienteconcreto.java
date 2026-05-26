package Flyweight;

public class Ingredienteconcreto implements Ingrediente {

    private final String nome;
    private final int    calorias;
    private final double precoUnitario;

    public Ingredienteconcreto(String nome, int calorias, double precoUnitario) {
        this.nome          = nome;
        this.calorias      = calorias;
        this.precoUnitario = precoUnitario;
        System.out.printf("[Flyweight] Criando ingrediente: %s%n", nome);
    }



    @Override
    public void aplicar(int quantidade) {
        System.out.printf(
                "  Ingrediente: %-15s | Qtd: %d | Calorias: %d kcal | Subtotal: R$ %.2f%n",
                nome, quantidade, calorias * quantidade, precoUnitario * quantidade
        );
    }

    @Override
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public int getCalorias() {
        return calorias;
    }
}

