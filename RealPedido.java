package proxy;

public class RealPedido implements Pedido {

    private String statusAtual;

    public RealPedido() {
        this.statusAtual = "Nenhum pedido realizado.";
    }

    @Override
    public void realizarPedido(String item, double preco) {
        System.out.println("[Cozinha] Preparando: " + item + " - R$ " + String.format("%.2f", preco));
        this.statusAtual = "Pedido em preparo: " + item + " (R$ " + String.format("%.2f", preco) + ")";
    }

    @Override
    public String consultarStatus() {
        return statusAtual;
    }
}
