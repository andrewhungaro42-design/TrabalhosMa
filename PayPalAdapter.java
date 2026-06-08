package adapter;

public class PayPalAdapter implements ProcessadorPagamento {

    private final PayPalGateway paypal;

    public PayPalAdapter(PayPalGateway paypal) {
        this.paypal = paypal;
    }

    @Override
    public String processarPagamento(double valor, String descricao) {
        return paypal.enviarTransacao(valor, "BRL", descricao);
    }

    @Override
    public String verificarStatus(String transacaoId) {
        String status = paypal.consultarTransacao(transacaoId);
        return traduzirStatusPayPal(status);
    }

    private String traduzirStatusPayPal(String statusPayPal) {
        return switch (statusPayPal) {
            case "APPROVED"  -> "APROVADO";
            case "PENDING"   -> "PENDENTE";
            case "DENIED"    -> "RECUSADO";
            default          -> "DESCONHECIDO";
        };
    }
}