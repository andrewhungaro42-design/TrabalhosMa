package adapter;

public class PayPalGateway {

    public String enviarTransacao(double amount, String currency, String description) {
        String id = "PP-" + System.currentTimeMillis();
        System.out.println("  [PayPal] Transacao enviada: " + description
                + " | " + currency + " " + amount
                + " | ID: " + id);
        return id;
    }

    public String consultarTransacao(String transactionId) {
        System.out.println("  [PayPal] Consultando transacao: " + transactionId);
        return "APPROVED";
    }
}