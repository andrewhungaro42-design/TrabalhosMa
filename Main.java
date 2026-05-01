package state;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Fluxo normal ===");
        PedidoHamburgueria pedido1 = new PedidoHamburgueria(101);
        pedido1.confirmar();
        pedido1.preparar();
        pedido1.entregar();

        System.out.println("\n=== Tentativa de operação inválida ===");
        PedidoHamburgueria pedido2 = new PedidoHamburgueria(102);
        pedido2.entregar();   // inválido: ainda aguardando
        pedido2.confirmar();
        pedido2.confirmar();  // inválido: já confirmado

        System.out.println("\n=== Cancelamento em estados diferentes ===");
        PedidoHamburgueria pedido3 = new PedidoHamburgueria(103);
        pedido3.cancelar();   // cancela em aguardando

        PedidoHamburgueria pedido4 = new PedidoHamburgueria(104);
        pedido4.confirmar();
        pedido4.preparar();
        pedido4.cancelar();   // cancela em preparo
        pedido4.confirmar();  // inválido: já cancelado
    }
}