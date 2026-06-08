package adapter;

public class SistemaPedido {

    private ProcessadorPagamento processador;

    public SistemaPedido(ProcessadorPagamento processador) {
        this.processador = processador;
    }

    public void setProcessador(ProcessadorPagamento processador) {
        this.processador = processador;
    }

    public String pagar(double valor, String descricao) {
        System.out.println("[Sistema] Processando pagamento de R$ "
                + String.format("%.2f", valor) + " - " + descricao);
        String id = processador.processarPagamento(valor, descricao);
        System.out.println("[Sistema] Transacao gerada: " + id);
        return id;
    }

    public String consultarPagamento(String transacaoId) {
        String status = processador.verificarStatus(transacaoId);
        System.out.println("[Sistema] Status da transacao " + transacaoId + ": " + status);
        return status;
    }
}