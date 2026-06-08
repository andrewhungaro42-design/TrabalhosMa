package adapter;

public class StripeAdapter implements ProcessadorPagamento {

    private final StripeGateway stripe;

    public StripeAdapter(StripeGateway stripe) {
        this.stripe = stripe;
    }

    @Override
    public String processarPagamento(double valor, String descricao) {
        int centavos = (int) Math.round(valor * 100);
        return stripe.charge(centavos, "brl");
    }

    @Override
    public String verificarStatus(String transacaoId) {
        String status = stripe.retrieveCharge(transacaoId);
        return traduzirStatusStripe(status);
    }

    private String traduzirStatusStripe(String statusStripe) {
        return switch (statusStripe) {
            case "succeeded"  -> "APROVADO";
            case "pending"    -> "PENDENTE";
            case "failed"     -> "RECUSADO";
            default           -> "DESCONHECIDO";
        };
    }
}