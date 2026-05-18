package Visitor;

public class Lanche implements ItemCardapio {

    private String nome;
    private int    calorias;
    private double preco;

    public Lanche(String nome, int calorias, double preco) {
        this.nome     = nome;
        this.calorias = calorias;
        this.preco    = preco;
    }

    @Override
    public void accept(VisitanteCardapio visitante) {
        visitante.visitarLanche(this);
    }

    public int    getCalorias() { return calorias; }
    @Override public String getNome()  { return nome; }
    @Override public double getPreco() { return preco; }
}