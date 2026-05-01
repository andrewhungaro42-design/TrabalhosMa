package strategy;

public class PagamentoPix implements EstrategiaPagamento {

    private String chavePix;

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void pagar(double valor) {
        String qrCode = gerarQrCode(valor);
        System.out.println("  [Pix] QR Code gerado: " + qrCode);
        System.out.println("  [Pix] Aguardando confirmação para a chave: " + chavePix);
        System.out.println("  [Pix] Pagamento de R$" + String.format("%.2f", valor) + " confirmado!");
    }

    private String gerarQrCode(double valor) {
        return "QR-" + chavePix.toUpperCase() + "-" + (int)(valor * 100);
    }

    @Override
    public String getNome() {
        return "Pix";
    }
}