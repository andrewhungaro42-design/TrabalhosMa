package observer;

public class SetorEntrega implements Observer {

    private String nome;

    public SetorEntrega(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String status) {
        switch (status) {
            case "PRONTO":
                System.out.println("[" + nome + "] Pedido pronto! Preparando para entrega.");
                break;
            case "SAIU_PARA_ENTREGA":
                System.out.println("[" + nome + "] Pedido saiu para entrega ao cliente!");
                break;
            case "ENTREGUE":
                System.out.println("[" + nome + "] Entrega confirmada. Pedido encerrado.");
                break;
            default:
                System.out.println("[" + nome + "] Status recebido: " + status);
        }
    }
}