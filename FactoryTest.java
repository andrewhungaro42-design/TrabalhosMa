package factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {

    // --- IServico: contrato ---

    @Test
    public void testServicoPagamentoExecutar() {
        IServico servico = new ServiçoPagamento();
        assertEquals("Pagamento efetivado", servico.executar(),
                "ServiçoPagamento.executar() deve retornar 'Pagamento efetivado'");
    }

    @Test
    public void testServicoPagamentoCancelar() {
        IServico servico = new ServiçoPagamento();
        assertEquals("Pagamento cancelado", servico.cancelar(),
                "ServiçoPagamento.cancelar() deve retornar 'Pagamento cancelado'");
    }

    @Test
    public void testServicoPedidoExecutar() {
        IServico servico = new ServiçoPedido();
        assertEquals("Pedido efetivado", servico.executar(),
                "ServiçoPedido.executar() deve retornar 'Pedido efetivado'");
    }

    @Test
    public void testServicoPedidoCancelar() {
        IServico servico = new ServiçoPedido();
        assertEquals("Pedido cancelado", servico.cancelar(),
                "ServiçoPedido.cancelar() deve retornar 'Pedido cancelado'");
    }

    // --- ServicoFactory: lança exceção para serviço inválido ---

    @Test
    public void testFactoryServicoInexistenteException() {
        assertThrows(IllegalArgumentException.class,
                () -> ServicoFactory.obterServico("Inexistente"),
                "Factory deve lançar IllegalArgumentException para serviço inexistente");
    }

    @Test
    public void testFactoryStringVaziaException() {
        assertThrows(IllegalArgumentException.class,
                () -> ServicoFactory.obterServico(""),
                "Factory deve lançar IllegalArgumentException para string vazia");
    }

    // --- Implementações diretas (sem reflexão) são IServico ---

    @Test
    public void testServicosPagamentoEhIServico() {
        assertTrue(new ServiçoPagamento() instanceof IServico,
                "ServiçoPagamento deve implementar IServico");
    }

    @Test
    public void testServicosPedidoEhIServico() {
        assertTrue(new ServiçoPedido() instanceof IServico,
                "ServiçoPedido deve implementar IServico");
    }
}
