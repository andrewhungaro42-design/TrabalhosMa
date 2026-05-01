package strategy;

public class PedidoHamburgueria {

    private EstrategiaPagamento estrategia;
    private int numeroPedido;
    private double valorTotal;

    public PedidoHamburgueria(int numeroPedido, double valorTotal) {
        this.numeroPedido = numeroPedido;
        this.valorTotal = valorTotal;
    }

    public void setEstrategia(EstrategiaPagamento estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaPagamento getEstrategia() {
        return estrategia;
    }

    public void processarPagamento() {
        if (estrategia == null) {
            System.out.println("[Pedido #" + numeroPedido + "] ERRO: Nenhuma forma de pagamento definida.");
            return;
        }
        System.out.println("[Pedido #" + numeroPedido + "] Processando R$" + String.format("%.2f", valorTotal)
                + " via " + estrategia.getNome());
        estrategia.pagar(valorTotal);
    }

    public int getNumeroPedido() { return numeroPedido; }
    public double getValorTotal() { return valorTotal; }
}