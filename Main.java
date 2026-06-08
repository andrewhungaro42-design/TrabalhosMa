package command;


public class Main {
    public static void main(String[] args) {

        Pedido              pedido     = new Pedido(42);
        GerenciadorPedido   gerenciador = new GerenciadorPedido();

        System.out.println("=== Executando comandos ===");
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Bacon"));
        gerenciador.executarComando(new AdicionarItemComando(pedido, "Batata Grande"));
        gerenciador.executarComando(new AdicionarItemComando(pedido, "Suco de Laranja"));
        gerenciador.executarComando(new AplicarDescontoComando(pedido, 10.0));
        pedido.exibir();

        System.out.println("\n=== Desfazendo desconto ===");
        gerenciador.desfazer();
        pedido.exibir();

        System.out.println("\n=== Desfazendo adicao do suco ===");
        gerenciador.desfazer();
        pedido.exibir();

        System.out.println("\n=== Reexecutando adicao do suco ===");
        gerenciador.reexecutar();
        pedido.exibir();

        System.out.println("\n=== Removendo item ===");
        gerenciador.executarComando(new RemoverItemComando(pedido, "Batata Grande"));
        pedido.exibir();

        System.out.println("\n=== Desfazendo remocao ===");
        gerenciador.desfazer();
        pedido.exibir();
    }
}