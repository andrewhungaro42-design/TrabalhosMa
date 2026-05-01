package state;

public interface EstadoPedido {
    void confirmar(PedidoHamburgueria pedido);
    void preparar(PedidoHamburgueria pedido);
    void entregar(PedidoHamburgueria pedido);
    void cancelar(PedidoHamburgueria pedido);
}