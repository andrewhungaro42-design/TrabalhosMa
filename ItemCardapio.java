package Visitor;

public interface ItemCardapio {
    void accept(VisitanteCardapio visitante);
    String getNome();
    double getPreco();
}