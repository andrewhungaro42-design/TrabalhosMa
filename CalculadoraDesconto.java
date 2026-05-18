package Visitor;

public class CalculadoraDesconto implements VisitanteCardapio {

    private double totalDesconto = 0.0;
    private static final double DESCONTO_LANCHE    = 0.10; // 10%
    private static final double DESCONTO_BEBIDA    = 0.05; // 5%
    private static final double DESCONTO_SOBREMESA = 0.15; // 15%

    @Override
    public void visitarLanche(Lanche lanche) {
        double desconto = lanche.getPreco() * DESCONTO_LANCHE;
        totalDesconto += desconto;
        System.out.printf("  [Desconto] %s: -R$ %.2f (10%%)%n",
                lanche.getNome(), desconto);
    }

    @Override
    public void visitarBebida(Bebida bebida) {
        double desconto = bebida.getPreco() * DESCONTO_BEBIDA;
        totalDesconto += desconto;
        System.out.printf("  [Desconto] %s: -R$ %.2f (5%%)%n",
                bebida.getNome(), desconto);
    }

    @Override
    public void visitarSobremesa(Sobremesa sobremesa) {
        double desconto = sobremesa.getPreco() * DESCONTO_SOBREMESA;
        totalDesconto += desconto;
        System.out.printf("  [Desconto] %s: -R$ %.2f (15%%)%n",
                sobremesa.getNome(), desconto);
    }

    public double getTotalDesconto() { return totalDesconto; }

    public void exibirTotal() {
        System.out.printf("  Total de desconto: R$ %.2f%n", totalDesconto);
    }
}