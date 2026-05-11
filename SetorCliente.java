package Mediator;

public class SetorCliente extends SetorBase {

    public SetorCliente(Mediador mediador) {
        super("Cliente", mediador);
    }

    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("  [Cliente] Mensagem recebida: " + mensagem);
    }

    public void fazerPedido(String descricao) {
        String msg = "Novo pedido: " + descricao;
        System.out.println("[Cliente] Registrando: " + msg);
        enviarPara(msg, "Caixa");
    }
}