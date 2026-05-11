package Mediator;

public class Main {
    public static void main(String[] args) {

        CentralDePedidos central = new CentralDePedidos();

        SetorCozinha  cozinha  = new SetorCozinha(central);
        SetorCaixa    caixa    = new SetorCaixa(central);
        SetorEntrega  entrega  = new SetorEntrega(central);
        SetorCliente  cliente  = new SetorCliente(central);

        central.registrarSetor(cozinha);
        central.registrarSetor(caixa);
        central.registrarSetor(entrega);
        central.registrarSetor(cliente);

        System.out.println("\n=== Fluxo completo de um pedido ===");
        cliente.fazerPedido("X-Bacon duplo com batata frita");

        System.out.println();
        caixa.pagamentoConfirmado(42);

        System.out.println();
        cozinha.pedidoPronto(42);

        System.out.println();
        entrega.pedidoEntregue(42);

        System.out.println("\n=== Broadcast: alerta geral da cozinha ===");
        cozinha.enviar("Cozinha fechando para limpeza por 10 minutos.");
    }
}