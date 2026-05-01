package strategy;

public class PagamentoDinheiro implements EstrategiaPagamento {

    private double valorEntregue;

    public PagamentoDinheiro(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    @Override
    public void pagar(double valor) {
        if (valorEntregue < valor) {
            System.out.println("  [Dinheiro] ERRO: Valor entregue insuficiente.");
            return;
        }
        double troco = calcularTroco(valor);
        System.out.println("  [Dinheiro] Pagamento aceito. Troco: R$" + String.format("%.2f", troco));
    }

    private double calcularTroco(double valor) {
        return valorEntregue - valor;
    }

    @Override
    public String getNome() {
        return "Dinheiro";
    }
}