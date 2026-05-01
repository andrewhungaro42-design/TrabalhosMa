package strategy;

public class Main {
    public static void main(String[] args) {

        PedidoHamburgueria pedido1 = new PedidoHamburgueria(101, 45.90);
        pedido1.setEstrategia(new PagamentoDinheiro(50.00));
        pedido1.processarPagamento();

        System.out.println();

        PedidoHamburgueria pedido2 = new PedidoHamburgueria(102, 89.70);
        pedido2.setEstrategia(new PagamentoCartao("Visa", 3));
        pedido2.processarPagamento();

        System.out.println();

        PedidoHamburgueria pedido3 = new PedidoHamburgueria(103, 32.50);
        pedido3.setEstrategia(new PagamentoPix("hamburgueria@pix.com"));
        pedido3.processarPagamento();

        System.out.println();

        // Troca de estratégia em tempo de execução
        System.out.println("=== Troca de estratégia em tempo de execução ===");
        PedidoHamburgueria pedido4 = new PedidoHamburgueria(104, 55.00);
        pedido4.setEstrategia(new PagamentoCartao("Mastercard", 1));
        pedido4.processarPagamento();
        System.out.println("  Cliente decidiu pagar no Pix...");
        pedido4.setEstrategia(new PagamentoPix("cliente@email.com"));
        pedido4.processarPagamento();

        System.out.println();

        // Sem estratégia definida
        PedidoHamburgueria pedido5 = new PedidoHamburgueria(105, 20.00);
        pedido5.processarPagamento();
    }
}