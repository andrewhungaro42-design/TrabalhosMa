package observer;

public class SetorCozinha implements Observer {

    private String nome;

    public SetorCozinha(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String status) {
        switch (status) {
            case "CONFIRMADO":
                System.out.println("[" + nome + "] Novo pedido confirmado! Iniciando preparo.");
                break;
            case "EM_PREPARO":
                System.out.println("[" + nome + "] Pedido em preparo na cozinha.");
                break;
            case "PRONTO":
                System.out.println("[" + nome + "] Pedido pronto! Enviando para retirada.");
                break;
            default:
                System.out.println("[" + nome + "] Status recebido: " + status);
        }
    }
}