package Mediator;

public class SetorEntrega extends SetorBase {

    public SetorEntrega(Mediador mediador) {
        super("Entrega", mediador);
    }

    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("  [Entrega] Mensagem recebida: " + mensagem);
    }

    public void pedidoEntregue(int numeroPedido) {
        String msg = "Pedido #" + numeroPedido + " entregue ao cliente!";
        System.out.println("[Entrega] Notificando: " + msg);
        enviar(msg);
    }
}