package memento;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MementoTest {

    private PedidoBuilder   pedido;
    private HistoricoPedido historico;

    @BeforeEach
    void setUp() {
        pedido    = new PedidoBuilder();
        historico = new HistoricoPedido();
    }

    // CT01 - Estado salvo pode ser restaurado corretamente
    @Test
    @DisplayName("CT01 - Restaurar memento deve devolver o estado salvo")
    void testRestaurarEstado() {
        pedido.setLanche("X-Bacon");
        pedido.setBebida("Suco");
        PedidoMemento memento = pedido.salvarEstado();

        pedido.setLanche("X-Tudo");
        pedido.setBebida("Refrigerante");

        pedido.restaurarEstado(memento);

        assertEquals("X-Bacon", pedido.salvarEstado().getLanche());
        assertEquals("Suco",    pedido.salvarEstado().getBebida());
    }

    // CT02 - Histórico empilha corretamente
    @Test
    @DisplayName("CT02 - Salvar estados deve incrementar o tamanho do histórico")
    void testHistoricoEmpilha() {
        pedido.setLanche("X-Bacon");
        historico.salvar(pedido.salvarEstado());
        historico.salvar(pedido.salvarEstado());
        historico.salvar(pedido.salvarEstado());

        assertEquals(3, historico.tamanho());
    }

    // CT03 - desfazer retorna o último estado salvo
    @Test
    @DisplayName("CT03 - desfazer deve retornar o estado mais recente")
    void testDesfazerRetornaUltimo() {
        pedido.setLanche("X-Bacon");
        historico.salvar(pedido.salvarEstado());

        pedido.setLanche("X-Frango");
        historico.salvar(pedido.salvarEstado());

        PedidoMemento ultimo = historico.desfazer();
        assertEquals("X-Frango", ultimo.getLanche());
    }

    // CT04 - desfazer vários estados na ordem correta (LIFO)
    @Test
    @DisplayName("CT04 - desfazer deve seguir ordem LIFO")
    void testOrdemLIFO() {
        pedido.setLanche("A"); historico.salvar(pedido.salvarEstado());
        pedido.setLanche("B"); historico.salvar(pedido.salvarEstado());
        pedido.setLanche("C"); historico.salvar(pedido.salvarEstado());

        assertEquals("C", historico.desfazer().getLanche());
        assertEquals("B", historico.desfazer().getLanche());
        assertEquals("A", historico.desfazer().getLanche());
    }

    // CT05 - desfazer sem histórico retorna null sem lançar exceção
    @Test
    @DisplayName("CT05 - desfazer com histórico vazio deve retornar null")
    void testDesfazerVazioRetornaNull() {
        assertNull(historico.desfazer());
    }

    // CT06 - Memento não expõe estado mutável (cópia defensiva)
    @Test
    @DisplayName("CT06 - Modificar lista de extras do Memento não deve afetar o estado salvo")
    void testCopiaDefensiva() {
        pedido.adicionarExtra("Bacon");
        PedidoMemento memento = pedido.salvarEstado();

        memento.getExtras().add("Ovo"); // tenta modificar

        assertEquals(1, memento.getExtras().size());
    }

    // CT07 - Extras são preservados corretamente no memento
    @Test
    @DisplayName("CT07 - Extras devem ser restaurados corretamente")
    void testExtrasRestaurados() {
        pedido.adicionarExtra("Bacon");
        pedido.adicionarExtra("Ovo");
        PedidoMemento memento = pedido.salvarEstado();

        pedido.removerExtra("Bacon");
        pedido.removerExtra("Ovo");

        pedido.restaurarEstado(memento);
        PedidoMemento restaurado = pedido.salvarEstado();

        assertEquals(2, restaurado.getExtras().size());
        assertTrue(restaurado.getExtras().contains("Bacon"));
        assertTrue(restaurado.getExtras().contains("Ovo"));
    }

    // CT08 - estaVazio reflete corretamente o estado do histórico
    @Test
    @DisplayName("CT08 - estaVazio deve retornar true quando histórico está vazio")
    void testEstaVazio() {
        assertTrue(historico.estaVazio());

        historico.salvar(pedido.salvarEstado());
        assertFalse(historico.estaVazio());

        historico.desfazer();
        assertTrue(historico.estaVazio());
    }
}