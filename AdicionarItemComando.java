package command;


public class AdicionarItemComando implements Comando {

    private final Pedido pedido;
    private final String item;

    public AdicionarItemComando(Pedido pedido, String item) {
        this.pedido = pedido;
        this.item   = item;
    }

    @Override
    public void executar() {
        pedido.adicionarItem(item);
    }

    @Override
    public void desfazer() {
        pedido.removerItem(item);
        System.out.println("  [Desfazer] Adicao de '" + item + "' desfeita.");
    }
}