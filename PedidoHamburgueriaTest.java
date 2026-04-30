package observer;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PedidoHamburgueriaTest {

    // Observer fake para capturar as notificações nos testes
    static class ObserverFake implements Observer {
        List<String> statusRecebidos = new ArrayList<>();

        @Override
        public void atualizar(String status) {
            statusRecebidos.add(status);
        }
    }

    // CT01 - Observer é notificado ao mudar status
    @Test
    @DisplayName("CT01 - Observer deve ser notificado ao mudar o status do pedido")
    void testObserverRecebeNotificacao() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(1);
        ObserverFake fake = new ObserverFake();

        pedido.adicionarObserver(fake);
        pedido.setStatus("CONFIRMADO");

        assertEquals(1, fake.statusRecebidos.size());
        assertEquals("CONFIRMADO", fake.statusRecebidos.get(0));
    }

    // CT02 - Múltiplos observers recebem a mesma notificação
    @Test
    @DisplayName("CT02 - Todos os observers registrados devem ser notificados")
    void testMultiplosObserversNotificados() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(2);
        ObserverFake obs1 = new ObserverFake();
        ObserverFake obs2 = new ObserverFake();
        ObserverFake obs3 = new ObserverFake();

        pedido.adicionarObserver(obs1);
        pedido.adicionarObserver(obs2);
        pedido.adicionarObserver(obs3);
        pedido.setStatus("EM_PREPARO");

        assertEquals("EM_PREPARO", obs1.statusRecebidos.get(0));
        assertEquals("EM_PREPARO", obs2.statusRecebidos.get(0));
        assertEquals("EM_PREPARO", obs3.statusRecebidos.get(0));
    }

    // CT03 - Observer removido não recebe mais notificações
    @Test
    @DisplayName("CT03 - Observer removido não deve receber notificações")
    void testObserverRemovidoNaoNotificado() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(3);
        ObserverFake fake = new ObserverFake();

        pedido.adicionarObserver(fake);
        pedido.setStatus("CONFIRMADO");

        pedido.removerObserver(fake);
        pedido.setStatus("PRONTO");

        assertEquals(1, fake.statusRecebidos.size()); // só recebeu CONFIRMADO
    }

    // CT04 - Sequência de status notificada corretamente
    @Test
    @DisplayName("CT04 - Observer deve receber status na ordem correta")
    void testSequenciaDeStatusCorreta() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(4);
        ObserverFake fake = new ObserverFake();

        pedido.adicionarObserver(fake);
        pedido.setStatus("CONFIRMADO");
        pedido.setStatus("EM_PREPARO");
        pedido.setStatus("PRONTO");
        pedido.setStatus("ENTREGUE");

        assertEquals(List.of("CONFIRMADO", "EM_PREPARO", "PRONTO", "ENTREGUE"),
                fake.statusRecebidos);
    }

    // CT05 - Pedido sem observers não lança exceção
    @Test
    @DisplayName("CT05 - Mudar status sem observers não deve lançar exceção")
    void testSemObserversNaoLancaExcecao() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(5);
        assertDoesNotThrow(() -> pedido.setStatus("CONFIRMADO"));
    }

    // CT06 - Status do pedido é atualizado corretamente
    @Test
    @DisplayName("CT06 - getStatus deve retornar o último status definido")
    void testGetStatusAtualizado() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(6);
        pedido.setStatus("PAGO");
        assertEquals("PAGO", pedido.getStatus());

        pedido.setStatus("CANCELADO");
        assertEquals("CANCELADO", pedido.getStatus());
    }
}