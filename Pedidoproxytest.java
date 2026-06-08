package proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Testes - Padrão Proxy: Hamburgueria")
class PedidoProxyTest {

    private PedidoProxy proxySaldoSuficiente;
    private PedidoProxy proxySaldoInsuficiente;
    private PedidoProxy proxySaldoExato;

    @BeforeEach
    void setUp() {
        proxySaldoSuficiente   = new PedidoProxy("João", 100.00);
        proxySaldoInsuficiente = new PedidoProxy("Maria", 10.00);
        proxySaldoExato        = new PedidoProxy("Carlos", 32.90);
    }

    // ---------------------------------------------------------------
    // 1. Pedido autorizado com saldo suficiente
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve autorizar pedido quando saldo for suficiente")
    void deveAutorizarPedidoComSaldoSuficiente() {
        proxySaldoSuficiente.realizarPedido("X-Bacon", 32.90);

        assertTrue(proxySaldoSuficiente.getUltimaMensagem().contains("autorizado"),
                "Mensagem deveria indicar autorização do pedido");
    }

    // ---------------------------------------------------------------
    // 2. Status atualizado após pedido autorizado
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Status deve ser atualizado após pedido autorizado")
    void deveAtualizarStatusAposPedidoAutorizado() {
        proxySaldoSuficiente.realizarPedido("X-Salada", 28.00);

        String status = proxySaldoSuficiente.consultarStatus();
        assertTrue(status.contains("X-Salada"),
                "Status deveria conter o item pedido");
    }

    // ---------------------------------------------------------------
    // 3. Pedido negado com saldo insuficiente
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve negar pedido quando saldo for insuficiente")
    void deveNegarPedidoComSaldoInsuficiente() {
        proxySaldoInsuficiente.realizarPedido("Combo Duplo", 45.00);

        assertTrue(proxySaldoInsuficiente.getUltimaMensagem().contains("negado"),
                "Mensagem deveria indicar negação do pedido");
    }

    // ---------------------------------------------------------------
    // 4. Saldo não deve ser descontado quando pedido for negado
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Saldo não deve ser alterado após pedido negado")
    void saldoNaoDeveSerAlteradoAposPedidoNegado() {
        double saldoAntes = proxySaldoInsuficiente.getSaldoCliente();
        proxySaldoInsuficiente.realizarPedido("X-Tudo", 50.00);

        assertEquals(saldoAntes, proxySaldoInsuficiente.getSaldoCliente(), 0.001,
                "Saldo não deve ser descontado quando pedido é negado");
    }

    // ---------------------------------------------------------------
    // 5. Saldo deve ser descontado corretamente após pedido autorizado
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Saldo deve ser descontado corretamente após pedido autorizado")
    void saldoDeveSerDescontadoAposPedidoAutorizado() {
        proxySaldoSuficiente.realizarPedido("X-Bacon", 32.90);

        assertEquals(67.10, proxySaldoSuficiente.getSaldoCliente(), 0.001,
                "Saldo deveria ser 100.00 - 32.90 = 67.10");
    }

    // ---------------------------------------------------------------
    // 6. Pedido com saldo exato deve ser autorizado
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve autorizar pedido quando saldo for exatamente igual ao preço")
    void deveAutorizarPedidoComSaldoExato() {
        proxySaldoExato.realizarPedido("X-Bacon", 32.90);

        assertTrue(proxySaldoExato.getUltimaMensagem().contains("autorizado"),
                "Pedido com saldo exato deveria ser autorizado");
        assertEquals(0.0, proxySaldoExato.getSaldoCliente(), 0.001,
                "Saldo deveria ser zero após pedido com valor exato");
    }

    // ---------------------------------------------------------------
    // 7. Múltiplos pedidos – saldo acumulado
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve processar múltiplos pedidos descontando o saldo corretamente")
    void deveProcessarMultiplosPedidos() {
        proxySaldoSuficiente.realizarPedido("X-Salada", 25.00);   // saldo: 75.00
        proxySaldoSuficiente.realizarPedido("Milk Shake", 18.00); // saldo: 57.00
        proxySaldoSuficiente.realizarPedido("Sundae", 60.00);     // negado (57.00 < 60.00)

        assertEquals(57.00, proxySaldoSuficiente.getSaldoCliente(), 0.001,
                "Saldo deveria ser 57.00 após dois pedidos autorizados e um negado");
    }

    // ---------------------------------------------------------------
    // 8. Status não deve mudar quando pedido for negado
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Status não deve ser alterado quando pedido for negado")
    void statusNaoDeveAlterarQuandoPedidoNegado() {
        String statusAntes = proxySaldoInsuficiente.consultarStatus();
        proxySaldoInsuficiente.realizarPedido("Combo Triplo", 99.00);

        assertEquals(statusAntes, proxySaldoInsuficiente.consultarStatus(),
                "Status não deveria mudar após pedido negado");
    }

    // ---------------------------------------------------------------
    // 9. Preço inválido (zero ou negativo) deve ser rejeitado
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve rejeitar pedido com preço inválido (zero ou negativo)")
    void deveRejeitarPedidoComPrecoInvalido() {
        proxySaldoSuficiente.realizarPedido("Item Grátis", 0.0);

        assertTrue(proxySaldoSuficiente.getUltimaMensagem().contains("inválido"),
                "Mensagem deveria indicar preço inválido");
    }

    // ---------------------------------------------------------------
    // 10. Proxy implementa a interface Pedido (verificação de polimorfismo)
    // ---------------------------------------------------------------
    @Test
    @DisplayName("PedidoProxy deve ser instância de Pedido (polimorfismo)")
    void proxyDeveImplementarInterfacePedido() {
        assertInstanceOf(Pedido.class, proxySaldoSuficiente,
                "PedidoProxy deveria implementar a interface Pedido");
    }
}