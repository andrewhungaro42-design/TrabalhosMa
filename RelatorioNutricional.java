package Visitor;

public class RelatorioNutricional implements VisitanteCardapio {

    private StringBuilder relatorio = new StringBuilder();

    @Override
    public void visitarLanche(Lanche lanche) {
        relatorio.append(String.format(
                "  Lanche: %-20s | R$ %5.2f | %4d kcal%n",
                lanche.getNome(), lanche.getPreco(), lanche.getCalorias()));
    }

    @Override
    public void visitarBebida(Bebida bebida) {
        relatorio.append(String.format(
                "  Bebida: %-20s | R$ %5.2f | %4dml%n",
                bebida.getNome(), bebida.getPreco(), bebida.getMl()));
    }

    @Override
    public void visitarSobremesa(Sobremesa sobremesa) {
        relatorio.append(String.format(
                "  Sobremesa: %-17s | R$ %5.2f | %4d kcal%n",
                sobremesa.getNome(), sobremesa.getPreco(), sobremesa.getCalorias()));
    }

    public void exibirRelatorio() {
        System.out.println("  === Relatório Nutricional ===");
        System.out.print(relatorio);
    }
}