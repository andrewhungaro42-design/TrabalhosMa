package adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdapterTest {

    private PayPalGateway  paypal;
    private StripeGateway  stripe;
    private PayPalAdapter  paypalAdapter;
    private StripeAdapter  stripeAdapter;

    @BeforeEach
    void setUp() {
        paypal        = new PayPalGateway();
        stripe        = new StripeGateway();
        paypalAdapter = new PayPalAdapter(paypal);
        stripeAdapter = new StripeAdapter(stripe);
    }

    // CT01 - PayPalAdapter.processarPagamento retorna ID nao nulo
    @Test
    @DisplayName("CT01 - PayPalAdapter deve retornar ID de transacao valido")
    void testPayPalRetornaId() {
        String id = paypalAdapter.processarPagamento(50.0, "Pedido teste");
        assertEquals(true, id != null && id.startsWith("PP-"));
    }

    // CT02 - StripeAdapter.processarPagamento retorna ID nao nulo
    @Test
    @DisplayName("CT02 - StripeAdapter deve retornar ID de transacao valido")
    void testStripeRetornaId() {
        String id = stripeAdapter.processarPagamento(50.0, "Pedido teste");
        assertEquals(true, id != null && id.startsWith("ch_"));
    }

    // CT03 - PayPalAdapter traduz status APPROVED para APROVADO
    @Test
    @DisplayName("CT03 - PayPalAdapter deve traduzir status APPROVED para APROVADO")
    void testPayPalTraducaoStatus() {
        String id     = paypalAdapter.processarPagamento(30.0, "Teste status");
        String status = paypalAdapter.verificarStatus(id);
        assertEquals("APROVADO", status);
    }

    // CT04 - StripeAdapter traduz status succeeded para APROVADO
    @Test
    @DisplayName("CT04 - StripeAdapter deve traduzir status succeeded para APROVADO")
    void testStripeTraducaoStatus() {
        String id     = stripeAdapter.processarPagamento(30.0, "Teste status");
        String status = stripeAdapter.verificarStatus(id);
        assertEquals("APROVADO", status);
    }

    // CT05 - SistemaPedido usa PayPalAdapter via interface comum
    @Test
    @DisplayName("CT05 - SistemaPedido deve funcionar com PayPalAdapter")
    void testSistemaComPayPal() {
        SistemaPedido sistema = new SistemaPedido(paypalAdapter);
        String id = sistema.pagar(75.0, "Combo #1");
        assertEquals(true, id.startsWith("PP-"));
    }

    // CT06 - SistemaPedido usa StripeAdapter via interface comum
    @Test
    @DisplayName("CT06 - SistemaPedido deve funcionar com StripeAdapter")
    void testSistemaComStripe() {
        SistemaPedido sistema = new SistemaPedido(stripeAdapter);
        String id = sistema.pagar(75.0, "Combo #1");
        assertEquals(true, id.startsWith("ch_"));
    }

    // CT07 - Troca de processador em tempo de execucao
    @Test
    @DisplayName("CT07 - SistemaPedido deve trocar processador em tempo de execucao")
    void testTrocaProcessador() {
        SistemaPedido sistema = new SistemaPedido(paypalAdapter);
        String idPayPal = sistema.pagar(40.0, "Pedido A");
        assertEquals(true, idPayPal.startsWith("PP-"));

        sistema.setProcessador(stripeAdapter);
        String idStripe = sistema.pagar(40.0, "Pedido B");
        assertEquals(true, idStripe.startsWith("ch_"));
    }

    // CT08 - consultarPagamento retorna status APROVADO para PayPal
    @Test
    @DisplayName("CT08 - consultarPagamento deve retornar APROVADO via PayPalAdapter")
    void testConsultarPagamentoPayPal() {
        SistemaPedido sistema = new SistemaPedido(paypalAdapter);
        String id     = sistema.pagar(60.0, "Pedido C");
        String status = sistema.consultarPagamento(id);
        assertEquals("APROVADO", status);
    }

    // CT09 - consultarPagamento retorna status APROVADO para Stripe
    @Test
    @DisplayName("CT09 - consultarPagamento deve retornar APROVADO via StripeAdapter")
    void testConsultarPagamentoStripe() {
        SistemaPedido sistema = new SistemaPedido(stripeAdapter);
        String id     = sistema.pagar(60.0, "Pedido D");
        String status = sistema.consultarPagamento(id);
        assertEquals("APROVADO", status);
    }

    // CT10 - Ambos adapters implementam a mesma interface Target
    @Test
    @DisplayName("CT10 - Ambos adapters devem ser atribuiveis a ProcessadorPagamento")
    void testPolimorfismo() {
        ProcessadorPagamento p1 = new PayPalAdapter(new PayPalGateway());
        ProcessadorPagamento p2 = new StripeAdapter(new StripeGateway());

        assertEquals(true, p1 instanceof ProcessadorPagamento);
        assertEquals(true, p2 instanceof ProcessadorPagamento);
    }
}