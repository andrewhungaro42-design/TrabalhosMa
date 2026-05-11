package Mediator;

public class SetorCozinha extends SetorBase {

    public SetorCozinha(Mediador mediador) {
        super("Cozinha", mediador);
    }

    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("  [Cozinha] Mensagem recebida: " + mensagem);
    }

    public void pedidoPronto(int numeroPedido) {
        String msg = "Pedido #" + numeroPedido + " pronto para retirada!";
        System.out.println("[Cozinha] Notificando: " + msg);
        enviarPara(msg, "Entrega");
    }
}