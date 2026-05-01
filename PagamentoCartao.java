package strategy;

public class PagamentoCartao implements EstrategiaPagamento {

    private String bandeira;
    private int parcelas;

    public PagamentoCartao(String bandeira, int parcelas) {
        this.bandeira = bandeira;
        this.parcelas = parcelas;
    }

    @Override
    public void pagar(double valor) {
        processarBandeira();
        double valorParcela = valor / parcelas;
        System.out.println("  [Cartão " + bandeira + "] Pagamento aprovado em " + parcelas
                + "x de R$" + String.format("%.2f", valorParcela));
    }

    private void processarBandeira() {
        System.out.println("  [Cartão] Verificando bandeira: " + bandeira + "...");
    }

    @Override
    public String getNome() {
        return "Cartão " + bandeira;
    }
}