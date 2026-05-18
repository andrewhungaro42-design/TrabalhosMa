package memento;

public class Main {
    public static void main(String[] args) {

        PedidoBuilder   pedido   = new PedidoBuilder();
        HistoricoPedido historico = new HistoricoPedido();

        System.out.println("=== Estado 1: pedido inicial ===");
        pedido.setLanche("X-Bacon");
        pedido.setBebida("Suco de laranja");
        historico.salvar(pedido.salvarEstado());
        pedido.exibirPedido();

        System.out.println("\n=== Estado 2: cliente adiciona extras ===");
        pedido.adicionarExtra("Bacon crocante");
        pedido.adicionarExtra("Ovo");
        pedido.setObservacao("Sem cebola");
        historico.salvar(pedido.salvarEstado());
        pedido.exibirPedido();

        System.out.println("\n=== Estado 3: cliente troca o lanche ===");
        pedido.setLanche("X-Tudo");
        pedido.setBebida("Refrigerante");
        historico.salvar(pedido.salvarEstado());
        pedido.exibirPedido();

        System.out.println("\n=== Desfazer: volta para estado 2 ===");
        pedido.restaurarEstado(historico.desfazer());
        pedido.exibirPedido();

        System.out.println("\n=== Desfazer: volta para estado 1 ===");
        pedido.restaurarEstado(historico.desfazer());
        pedido.exibirPedido();

        System.out.println("\n=== Desfazer sem histórico disponível ===");
        PedidoMemento resultado = historico.desfazer();
        if (resultado == null) {
            System.out.println("  Não há mais versões anteriores.");
        }
    }
}