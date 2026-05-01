package state;

public class ConfirmadoState implements EstadoPedido {

    @Override
    public void confirmar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido já foi confirmado.");
    }

    @Override
    public void preparar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] Cozinha iniciou o preparo!");
        pedido.setEstado(new EmPreparoState());
    }

    @Override
    public void entregar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido ainda não foi preparado.");
    }

    @Override
    public void cancelar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] Pedido cancelado após confirmação.");
        pedido.setEstado(new CanceladoState());
    }
}