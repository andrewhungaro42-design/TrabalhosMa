package Composite;

public class IngredienteSimples implements ItemCardapio {

    private final String nome;
    private final double preco;

    public IngredienteSimples(String nome, double preco) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do ingrediente não pode ser vazio.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }


    @Override
    public void exibir(int nivel) {
        String recuo = "  ".repeat(nivel) + "└─ ";
        System.out.printf("%s%s - R$ %.2f%n", recuo, nome, preco);
    }

    @Override
    public String toString() {
        return String.format("IngredienteSimples{nome='%s', preco=%.2f}", nome, preco);
    }
}
