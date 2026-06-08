package command;


public class AplicarDescontoComando implements Comando {

    private final Pedido pedido;
    private final double novoDesconto;
    private double      descontoAnterior;

    public AplicarDescontoComando(Pedido pedido, double novoDesconto) {
        this.pedido       = pedido;
        this.novoDesconto = novoDesconto;
    }

    @Override
    public void executar() {
        descontoAnterior = pedido.getDesconto();
        pedido.setDesconto(novoDesconto);
    }

    @Override
    public void desfazer() {
        pedido.setDesconto(descontoAnterior);
        System.out.println("  [Desfazer] Desconto restaurado para " + descontoAnterior + "%");
    }
}