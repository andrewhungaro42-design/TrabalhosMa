package adapter;

public class StripeGateway {

    public String charge(int amountCents, String currency) {
        String chargeId = "ch_" + System.currentTimeMillis();
        System.out.println("  [Stripe] Cobranca criada: " + amountCents + " centavos "
                + currency + " | charge_id: " + chargeId);
        return chargeId;
    }

    public String retrieveCharge(String chargeId) {
        System.out.println("  [Stripe] Consultando charge: " + chargeId);
        return "succeeded";
    }
}