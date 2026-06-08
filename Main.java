package adapter;


public class Main {
    public static void main(String[] args) {

        System.out.println("=== Pagamento via PayPal ===");
        PayPalGateway   paypal        = new PayPalGateway();
        PayPalAdapter   paypalAdapter = new PayPalAdapter(paypal);
        SistemaPedido   sistema       = new SistemaPedido(paypalAdapter);

        String idPayPal = sistema.pagar(89.90, "Combo Familia #42");
        sistema.consultarPagamento(idPayPal);

        System.out.println("\n=== Trocando para Stripe em tempo de execucao ===");
        StripeGateway  stripe        = new StripeGateway();
        StripeAdapter  stripeAdapter = new StripeAdapter(stripe);
        sistema.setProcessador(stripeAdapter);

        String idStripe = sistema.pagar(45.50, "X-Bacon + Batata #43");
        sistema.consultarPagamento(idStripe);
    }
}
