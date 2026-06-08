package proxy;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Hamburgueria Proxy Burguer ===\n");

        Pedido pedidoJoao = new PedidoProxy("João", 50.00);
        pedidoJoao.realizarPedido("X-Bacon", 32.90);
        System.out.println("Status: " + pedidoJoao.consultarStatus());

        System.out.println();

        Pedido pedidoMaria = new PedidoProxy("Maria", 15.00);
        pedidoMaria.realizarPedido("Combo Duplo", 45.00);
        System.out.println("Status: " + pedidoMaria.consultarStatus());

        System.out.println();

        PedidoProxy pedidoCarlos = new PedidoProxy("Carlos", 60.00);
        pedidoCarlos.realizarPedido("X-Salada", 25.00);
        pedidoCarlos.realizarPedido("Milk Shake", 18.00);
        pedidoCarlos.realizarPedido("Sundae", 22.00); // deve ser negado (saldo = 17.00)
        System.out.println("Saldo final de Carlos: R$ " + String.format("%.2f", pedidoCarlos.getSaldoCliente()));
    }
}
