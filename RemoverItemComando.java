package command;


public class RemoverItemComando implements Comando {

    private final Pedido pedido;
    private final String item;

    public RemoverItemComando(Pedido pedido, String item) {
        this.pedido = pedido;
        this.item   = item;
    }

    @Override
    public void executar() {
        pedido.removerItem(item);
    }

    @Override
    public void desfazer() {
        pedido.adicionarItem(item);
        System.out.println("  [Desfazer] Remocao de '" + item + "' desfeita.");
    }
}