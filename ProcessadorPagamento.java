package adapter;

public interface ProcessadorPagamento {
    String processarPagamento(double valor, String descricao);
    String verificarStatus(String transacaoId);
}