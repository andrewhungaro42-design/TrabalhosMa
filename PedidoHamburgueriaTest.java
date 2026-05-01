package state;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PedidoHamburgueriaTest {

    // CT01 - Estado inicial é AguardandoState
    @Test
    @DisplayName("CT01 - Pedido criado deve estar no estado AguardandoState")
    void testEstadoInicial() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(1);
        assertInstanceOf(AguardandoState.class, pedido.getEstado());
    }

    // CT02 - Fluxo completo: Aguardando -> Confirmado -> EmPreparo -> Entregue
    @Test
    @DisplayName("CT02 - Fluxo completo do pedido deve transitar corretamente")
    void testFluxoCompleto() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(2);

        pedido.confirmar();
        assertInstanceOf(ConfirmadoState.class, pedido.getEstado());

        pedido.preparar();
        assertInstanceOf(EmPreparoState.class, pedido.getEstado());

        pedido.entregar();
        assertInstanceOf(EntregueState.class, pedido.getEstado());
    }

    // CT03 - Cancelar em Aguardando leva ao CanceladoState
    @Test
    @DisplayName("CT03 - Cancelar pedido aguardando deve ir para CanceladoState")
    void testCancelarAguardando() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(3);
        pedido.cancelar();
        assertInstanceOf(CanceladoState.class, pedido.getEstado());
    }

    // CT04 - Cancelar em EmPreparo leva ao CanceladoState
    @Test
    @DisplayName("CT04 - Cancelar pedido em preparo deve ir para CanceladoState")
    void testCancelarEmPreparo() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(4);
        pedido.confirmar();
        pedido.preparar();
        pedido.cancelar();
        assertInstanceOf(CanceladoState.class, pedido.getEstado());
    }

    // CT05 - Operação inválida não muda o estado
    @Test
    @DisplayName("CT05 - Entregar pedido aguardando não deve mudar o estado")
    void testOperacaoInvalidaNaoMudaEstado() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(5);
        pedido.entregar(); // inválido
        assertInstanceOf(AguardandoState.class, pedido.getEstado());
    }

    // CT06 - Pedido entregue não pode ser cancelado
    @Test
    @DisplayName("CT06 - Cancelar pedido entregue não deve mudar o estado")
    void testNaoCancelarEntregue() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(6);
        pedido.confirmar();
        pedido.preparar();
        pedido.entregar();
        pedido.cancelar(); // inválido
        assertInstanceOf(EntregueState.class, pedido.getEstado());
    }

    // CT07 - Pedido cancelado permanece cancelado
    @Test
    @DisplayName("CT07 - Confirmar pedido cancelado não deve mudar o estado")
    void testCanceladoPermaneceCancelado() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(7);
        pedido.cancelar();
        pedido.confirmar(); // inválido
        assertInstanceOf(CanceladoState.class, pedido.getEstado());
    }

    // CT08 - Confirmar duas vezes não avança além de ConfirmadoState
    @Test
    @DisplayName("CT08 - Confirmar duas vezes não deve alterar o estado")
    void testConfirmarDuasVezes() {
        PedidoHamburgueria pedido = new PedidoHamburgueria(8);
        pedido.confirmar();
        pedido.confirmar(); // inválido
        assertInstanceOf(ConfirmadoState.class, pedido.getEstado());
    }
}