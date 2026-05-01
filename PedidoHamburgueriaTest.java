package strategy;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PedidoHamburgueriaTest {

    // CT01 - Estratégia é atribuída corretamente
    @Test
    @DisplayName("CT01 - setEstrategia deve atribuir a estratégia ao pedido")
    void testSetEstrategia() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(1, 50.00);
        EstrategiaPagamento pix = new PagamentoPix("teste@pix.com");

        pedido.setEstrategia(pix);

        assertNotNull(pedido.getEstrategia());
        assertInstanceOf(PagamentoPix.class, pedido.getEstrategia());
    }

    // CT02 - Pagamento com dinheiro suficiente não lança exceção
    @Test
    @DisplayName("CT02 - Pagar com dinheiro suficiente não deve lançar exceção")
    void testPagamentoDinheiroSuficiente() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(2, 40.00);
        pedido.setEstrategia(new PagamentoDinheiro(50.00));

        assertDoesNotThrow(() -> pedido.processarPagamento());
    }

    // CT03 - Pagamento sem estratégia não lança exceção
    @Test
    @DisplayName("CT03 - Processar pagamento sem estratégia não deve lançar exceção")
    void testSemEstrategiaNaoLancaExcecao() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(3, 30.00);

        assertDoesNotThrow(() -> pedido.processarPagamento());
    }

    // CT04 - Troca de estratégia em tempo de execução
    @Test
    @DisplayName("CT04 - Deve ser possível trocar a estratégia em tempo de execução")
    void testTrocaDeEstrategia() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(4, 60.00);

        pedido.setEstrategia(new PagamentoCartao("Visa", 2));
        assertInstanceOf(PagamentoCartao.class, pedido.getEstrategia());

        pedido.setEstrategia(new PagamentoPix("cliente@pix.com"));
        assertInstanceOf(PagamentoPix.class, pedido.getEstrategia());
    }

    // CT05 - getNome retorna o nome correto de cada estratégia
    @Test
    @DisplayName("CT05 - getNome deve retornar o nome correto de cada estratégia")
    void testGetNomeEstrategias() {
        assertEquals("Dinheiro",        new PagamentoDinheiro(100).getNome());
        assertEquals("Cartão Visa",     new PagamentoCartao("Visa", 1).getNome());
        assertEquals("Pix",             new PagamentoPix("chave@pix.com").getNome());
    }

    // CT06 - Pagamento com cartão em parcelas não lança exceção
    @Test
    @DisplayName("CT06 - Pagamento com cartão parcelado não deve lançar exceção")
    void testPagamentoCartaoParcelado() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(6, 120.00);
        pedido.setEstrategia(new PagamentoCartao("Mastercard", 3));

        assertDoesNotThrow(() -> pedido.processarPagamento());
    }

    // CT07 - Valor total do pedido é preservado corretamente
    @Test
    @DisplayName("CT07 - getValorTotal deve retornar o valor correto do pedido")
    void testValorTotalPedido() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(7, 75.50);

        assertEquals(75.50, pedido.getValorTotal(), 0.001);
    }

    // CT08 - Pagamento Pix não lança exceção
    @Test
    @DisplayName("CT08 - Pagamento via Pix não deve lançar exceção")
    void testPagamentoPix() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(8, 35.00);
        pedido.setEstrategia(new PagamentoPix("hamburgueria@empresa.com"));

        assertDoesNotThrow(() -> pedido.processarPagamento());
    }
}