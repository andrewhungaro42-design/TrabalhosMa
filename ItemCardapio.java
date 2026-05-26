package Iterator;

public class ItemCardapio {

    private final String nome;
    private final String descricao;
    private final double preco;
    private final String categoria;

    public ItemCardapio(String nome, String descricao, double preco, String categoria) {
        this.nome      = nome;
        this.descricao = descricao;
        this.preco     = preco;
        this.categoria = categoria;
    }

    public String getNome()      { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco()     { return preco; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return String.format("%-25s | %-14s | R$ %5.2f | %s",
                nome, categoria, preco, descricao);
    }
}