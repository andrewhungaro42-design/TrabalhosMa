package state;

public class AguardandoState implements EstadoPedido {

    @Override
    public void confirmar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] Pedido confirmado! Indo para preparo.");
        pedido.setEstado(new ConfirmadoState());
    }

    @Override
    public void preparar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Confirme o pedido antes de preparar.");
    }

    @Override
    public void entregar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] ERRO: Pedido ainda não foi confirmado.");
    }

    @Override
    public void cancelar(PedidoHamburgueria pedido) {
        System.out.println("[Pedido #" + pedido.getNumeroPedido() + "] Pedido cancelado antes da confirmação.");
        pedido.setEstado(new CanceladoState());
    }
}