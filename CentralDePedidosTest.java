package Mediator;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class CentralDePedidosTest {

    private CentralDePedidos central;
    private SetorCozinha     cozinha;
    private SetorCaixa       caixa;
    private SetorEntrega     entrega;
    private SetorCliente     cliente;
    private ByteArrayOutputStream saida;

    @BeforeEach
    void setUp() {
        central = new CentralDePedidos();
        cozinha = new SetorCozinha(central);
        caixa   = new SetorCaixa(central);
        entrega = new SetorEntrega(central);
        cliente = new SetorCliente(central);

        central.registrarSetor(cozinha);
        central.registrarSetor(caixa);
        central.registrarSetor(entrega);
        central.registrarSetor(cliente);

        saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    // CT01 - Mensagem direcionada chega ao destino correto
    @Test
    @DisplayName("CT01 - enviarParaSetor deve entregar mensagem apenas ao destino")
    void testMensagemDirecionada() {
        central.enviarParaSetor("Pedido pronto!", "Entrega");
        String out = saida.toString();
        assertTrue(out.contains("[Entrega]"));
        assertFalse(out.contains("[Cozinha] Mensagem recebida"));
        assertFalse(out.contains("[Caixa] Mensagem recebida"));
    }

    // CT02 - Broadcast notifica todos exceto o remetente
    @Test
    @DisplayName("CT02 - enviarMensagem deve notificar todos os setores exceto o remetente")
    void testBroadcastExcluiRemetente() {
        cozinha.enviar("Alerta geral da cozinha.");
        String out = saida.toString();
        assertTrue(out.contains("[Caixa] Mensagem recebida"));
        assertTrue(out.contains("[Entrega] Mensagem recebida"));
        assertTrue(out.contains("[Cliente] Mensagem recebida"));
        assertFalse(out.contains("[Cozinha] Mensagem recebida"));
    }

    // CT03 - Destino inexistente exibe mensagem de erro
    @Test
    @DisplayName("CT03 - Enviar para setor inexistente deve exibir erro")
    void testDestinoInexistente() {
        central.enviarParaSetor("Teste", "SetorFantasma");
        assertTrue(saida.toString().contains("não encontrado"));
    }

    // CT04 - Cliente direciona pedido ao Caixa
    @Test
    @DisplayName("CT04 - fazerPedido do Cliente deve notificar o Caixa")
    void testClienteNotificaCaixa() {
        cliente.fazerPedido("X-Tudo");
        assertTrue(saida.toString().contains("[Caixa] Mensagem recebida"));
    }

    // CT05 - Caixa notifica Cozinha após pagamento
    @Test
    @DisplayName("CT05 - pagamentoConfirmado deve notificar a Cozinha")
    void testCaixaNotificaCozinha() {
        caixa.pagamentoConfirmado(10);
        assertTrue(saida.toString().contains("[Cozinha] Mensagem recebida"));
    }

    // CT06 - Cozinha notifica Entrega quando pedido fica pronto
    @Test
    @DisplayName("CT06 - pedidoPronto deve notificar o setor de Entrega")
    void testCozinhaNotificaEntrega() {
        cozinha.pedidoPronto(10);
        assertTrue(saida.toString().contains("[Entrega] Mensagem recebida"));
    }

    // CT07 - Entrega notifica todos ao confirmar entrega
    @Test
    @DisplayName("CT07 - pedidoEntregue deve fazer broadcast para todos os setores")
    void testEntregaFazBroadcast() {
        entrega.pedidoEntregue(10);
        String out = saida.toString();
        assertTrue(out.contains("[Cozinha] Mensagem recebida"));
        assertTrue(out.contains("[Caixa] Mensagem recebida"));
        assertTrue(out.contains("[Cliente] Mensagem recebida"));
    }

    // CT08 - Registrar setor duplicado sobrescreve o anterior
    @Test
    @DisplayName("CT08 - Registrar mesmo setor duas vezes não deve lançar exceção")
    void testRegistroDuplicado() {
        assertDoesNotThrow(() -> central.registrarSetor(cozinha));
    }
}