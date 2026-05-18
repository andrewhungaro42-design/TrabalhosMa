package Visitor;

public interface VisitanteCardapio {
    void visitarLanche(Lanche lanche);
    void visitarBebida(Bebida bebida);
    void visitarSobremesa(Sobremesa sobremesa);
}