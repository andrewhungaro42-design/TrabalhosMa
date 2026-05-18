package Visitor;

public class Bebida implements ItemCardapio {

    private String nome;
    private int    ml;
    private double preco;

    public Bebida(String nome, int ml, double preco) {
        this.nome  = nome;
        this.ml    = ml;
        this.preco = preco;
    }

    @Override
    public void accept(VisitanteCardapio visitante) {
        visitante.visitarBebida(this);
    }

    public int getMl() { return ml; }
    @Override public String getNome()  { return nome; }
    @Override public double getPreco() { return preco; }
}