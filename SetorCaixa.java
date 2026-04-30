package observer;

public class SetorCaixa implements Observer {

    private String nome;

    public SetorCaixa(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String status) {
        switch (status) {
            case "CONFIRMADO":
                System.out.println("[" + nome + "] Pedido confirmado. Aguardando pagamento.");
                break;
            case "PAGO":
                System.out.println("[" + nome + "] Pagamento registrado com sucesso!");
                break;
            case "CANCELADO":
                System.out.println("[" + nome + "] Pedido cancelado. Processando estorno.");
                break;
            default:
                System.out.println("[" + nome + "] Status recebido: " + status);
        }
    }
}