package state;

public class EntregueState implements EstadoPedido {

    @Override
    public void confirmar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido já foi entregue.");
    }

    @Override
    public void preparar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido já foi entregue.");
    }

    @Override
    public void entregar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido já foi entregue ao cliente.");
    }

    @Override
    public void cancelar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Não é possível cancelar um pedido já entregue.");
    }
}