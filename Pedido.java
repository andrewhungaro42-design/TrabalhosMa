package proxy;

public interface Pedido {
    void realizarPedido(String item, double preco);
    String consultarStatus();
}

