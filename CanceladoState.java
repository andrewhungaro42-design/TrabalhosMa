package state;

public class CanceladoState implements EstadoPedido {

    @Override
    public void confirmar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido cancelado não pode ser confirmado.");
    }

    @Override
    public void preparar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido cancelado não pode ser preparado.");
    }

    @Override
    public void entregar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido cancelado não pode ser entregue.");
    }

    @Override
    public void cancelar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido já está cancelado.");
    }
}