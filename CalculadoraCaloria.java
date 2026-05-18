package Visitor;

public class CalculadoraCaloria implements VisitanteCardapio {

    private int totalCalorias = 0;

    @Override
    public void visitarLanche(Lanche lanche) {
        totalCalorias += lanche.getCalorias();
        System.out.println("  [Caloria] " + lanche.getNome()
                + ": " + lanche.getCalorias() + " kcal");
    }

    @Override
    public void visitarBebida(Bebida bebida) {
        int cal = (int)(bebida.getMl() * 0.4);
        totalCalorias += cal;
        System.out.println("  [Caloria] " + bebida.getNome()
                + " (" + bebida.getMl() + "ml): ~" + cal + " kcal");
    }

    @Override
    public void visitarSobremesa(Sobremesa sobremesa) {
        totalCalorias += sobremesa.getCalorias();
        System.out.println("  [Caloria] " + sobremesa.getNome()
                + ": " + sobremesa.getCalorias() + " kcal");
    }

    public int getTotalCalorias() { return totalCalorias; }

    public void exibirTotal() {
        System.out.println("  Total calórico do pedido: " + totalCalorias + " kcal");
    }
}