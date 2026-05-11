package Mediator;

public class SetorCaixa extends SetorBase {

    public SetorCaixa(Mediador mediador) {
        super("Caixa", mediador);
    }

    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("  [Caixa] Mensagem recebida: " + mensagem);
    }

    public void pagamentoConfirmado(int numeroPedido) {
        String msg = "Pagamento do pedido #" + numeroPedido + " confirmado. Iniciar preparo!";
        System.out.println("[Caixa] Notificando: " + msg);
        enviarPara(msg, "Cozinha");
    }
}