package Façade;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Casos de teste para o padrão Facade — HamburgueriaFacade.
 *
 * Usa Mockito para isolar os subsistemas e verificar
 * que a Facade os orquestra corretamente.
 *
 * Cobre:
 *  CT-F01  Pedido bem-sucedido chama todos os subsistemas na ordem correta
 *  CT-F02  Pedido cancelado por item indisponível no estoque
 *  CT-F03  Pedido cancelado por falha no pagamento
 *  CT-F04  Cancelamento chama cozinha, estoque e estorno de pagamento
 *  CT-F05  realizarPedido retorna true em fluxo normal
 *  CT-F06  realizarPedido retorna false quando estoque falha
 *  CT-F07  Nota fiscal emitida apenas em pagamento aprovado
 *  CT-F08  Estoque verificado recursivamente em PratoComposto
 *  CT-F09  Ingredientes reservados após pagamento aprovado
 *  CT-F10  Nenhum preparo iniciado se estoque falhar
 */
@DisplayName("Padrão Facade — HamburgueriaFacade")
class HamburgueriaFacadeTest {

    private Estoque            estoqueM;
    private Cozinha            cozinhaM;
    private Pagamento          pagamentoM;
    private Entrega            entregaM;
    private HamburgueriaFacade facade;

    private ItemCardapio       ingredienteSimples;
    private PratoComposto      pratoComposto;

    @BeforeEach
    void setUp() {
        // Mocks dos subsistemas
        estoqueM   = Mockito.mock(Estoque.class);
        cozinhaM   = Mockito.mock(Cozinha.class);
        pagamentoM = Mockito.mock(Pagamento.class);
        entregaM   = Mockito.mock(Entrega.class);

        facade = new HamburgueriaFacade(estoqueM, cozinhaM, pagamentoM, entregaM);

        // Estrutura Composite para os testes
        ingredienteSimples = new IngredienteSimples("Queijo Cheddar", 2.50);

        pratoComposto = new PratoComposto("Hambúrguer Clássico");
        pratoComposto.adicionar(new IngredienteSimples("Pão de Gergelim", 3.00));
        pratoComposto.adicionar(new IngredienteSimples("Carne 150g",     10.00));
        pratoComposto.adicionar(ingredienteSimples);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F01: Fluxo completo bem-sucedido — ordem de chamadas
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F01: Pedido bem-sucedido aciona todos os subsistemas")
    void ctF01_pedidoBemSucedido_acionaTodosSubsistemas() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(true);
        when(pagamentoM.processarPagamento(anyString(), anyDouble())).thenReturn(true);

        facade.realizarPedido("João", pratoComposto);

        // Estoque verificado para cada ingrediente filho
        verify(estoqueM, atLeastOnce()).verificarDisponibilidade(anyString());
        // Pagamento processado e nota emitida
        verify(pagamentoM).processarPagamento(eq("João"), anyDouble());
        verify(pagamentoM).emitirNotaFiscal(eq("João"), anyDouble());
        // Cozinha iniciou e finalizou o preparo
        verify(cozinhaM).iniciarPreparo(pratoComposto.getNome());
        verify(cozinhaM).finalizarPreparo(pratoComposto.getNome());
        // Entrega despachada e cliente notificado
        verify(entregaM).despacharPedido(eq("João"), eq(pratoComposto.getNome()));
        verify(entregaM).notificarCliente("João");
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F02: Estoque indisponível → pedido não avança
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F02: Pedido cancelado quando item indisponível no estoque")
    void ctF02_estoqueIndisponivel_pedidoCancelado() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(false);

        boolean resultado = facade.realizarPedido("Maria", pratoComposto);

        assertFalse(resultado);
        // Pagamento e cozinha NÃO devem ser chamados
        verify(pagamentoM, never()).processarPagamento(anyString(), anyDouble());
        verify(cozinhaM, never()).iniciarPreparo(anyString());
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F03: Falha no pagamento → cozinha não inicia
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F03: Falha no pagamento impede o início do preparo")
    void ctF03_falhaNoP agamento_cozinhaNaoInicia() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(true);
        when(pagamentoM.processarPagamento(anyString(), anyDouble())).thenReturn(false);

        boolean resultado = facade.realizarPedido("Carlos", pratoComposto);

        assertFalse(resultado);
        verify(cozinhaM, never()).iniciarPreparo(anyString());
        verify(entregaM, never()).despacharPedido(anyString(), anyString());
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F04: Cancelamento aciona cozinha, estoque e estorno
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F04: cancelarPedido aciona cozinha, liberação de estoque e estorno")
    void ctF04_cancelarPedido_acionaSubsistemas() {
        facade.cancelarPedido("Ana", pratoComposto);

        verify(cozinhaM).cancelarPreparo(pratoComposto.getNome());
        verify(estoqueM, atLeastOnce()).liberarIngrediente(anyString());
        verify(pagamentoM).estornarPagamento(eq("Ana"), eq(pratoComposto.getPreco()));
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F05: realizarPedido retorna true no fluxo normal
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F05: realizarPedido retorna true quando tudo é aprovado")
    void ctF05_realizarPedido_retornaTrue_fluxoNormal() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(true);
        when(pagamentoM.processarPagamento(anyString(), anyDouble())).thenReturn(true);

        boolean resultado = facade.realizarPedido("Lucas", pratoComposto);

        assertTrue(resultado);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F06: realizarPedido retorna false quando estoque falha
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F06: realizarPedido retorna false quando estoque indisponível")
    void ctF06_realizarPedido_retornaFalse_estoqueIndisponivel() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(false);

        boolean resultado = facade.realizarPedido("Pedro", pratoComposto);

        assertFalse(resultado);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F07: Nota fiscal emitida somente após pagamento aprovado
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F07: Nota fiscal emitida apenas quando pagamento aprovado")
    void ctF07_notaFiscal_emitidaAposAprovacao() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(true);
        when(pagamentoM.processarPagamento(anyString(), anyDouble())).thenReturn(true);

        facade.realizarPedido("Beatriz", pratoComposto);

        verify(pagamentoM, times(1)).emitirNotaFiscal(eq("Beatriz"), anyDouble());
    }

    @Test
    @DisplayName("CT-F07b: Nota fiscal NÃO emitida quando pagamento falha")
    void ctF07b_notaFiscal_naoEmitidaQuandoPagamentoFalha() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(true);
        when(pagamentoM.processarPagamento(anyString(), anyDouble())).thenReturn(false);

        facade.realizarPedido("Rafael", pratoComposto);

        verify(pagamentoM, never()).emitirNotaFiscal(anyString(), anyDouble());
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F08: Estoque verificado recursivamente nos filhos do Composite
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F08: Estoque é verificado para cada ingrediente do PratoComposto")
    void ctF08_estoqueVerificadoRecursivamente() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(true);
        when(pagamentoM.processarPagamento(anyString(), anyDouble())).thenReturn(true);

        facade.realizarPedido("Sofia", pratoComposto);

        // pratoComposto tem 3 filhos folha → deve chamar 3 vezes
        verify(estoqueM, times(3)).verificarDisponibilidade(anyString());
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F09: Ingredientes reservados após pagamento aprovado
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F09: Ingredientes reservados para cada folha do Composite")
    void ctF09_ingredientesReservadosAposPagamento() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(true);
        when(pagamentoM.processarPagamento(anyString(), anyDouble())).thenReturn(true);

        facade.realizarPedido("Fernanda", pratoComposto);

        // 3 folhas → 3 reservas
        verify(estoqueM, times(3)).reservarIngrediente(anyString());
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-F10: Cozinha não é acionada se o estoque falhar
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-F10: Cozinha não é acionada quando estoque indisponível")
    void ctF10_cozinhaNaoAcionada_quandoEstoqueIndisponivel() {
        when(estoqueM.verificarDisponibilidade(anyString())).thenReturn(false);

        facade.realizarPedido("Rodrigo", pratoComposto);

        verify(cozinhaM, never()).iniciarPreparo(anyString());
        verify(cozinhaM, never()).finalizarPreparo(anyString());
    }
}