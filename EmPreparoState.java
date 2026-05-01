package state;

public class EmPreparoState implements EstadoPedido {

    @Override
    public void confirmar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido já está em preparo.");
    }

    @Override
    public void preparar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido já está sendo preparado.");
    }

    @Override
    public void entregar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] Pedido pronto! Saiu para entrega.");
        pedido.setEstado(new EntregueState());
    }

    @Override
    public void cancelar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] Pedido cancelado durante o preparo.");
        pedido.setEstado(new CanceladoState());
    }
}