package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    private Pedido            pedido;
    private GerenciadorPedido gerenciador;

    @BeforeEach
    void setUp() {
        pedido      = new Pedido(1);
        gerenciador = new GerenciadorPedido();
    }

    // CT01 - executarComando adiciona item ao pedido
    @Test
    @DisplayName("CT01 - executarComando deve adicionar item ao pedido")
    void testAdicionarItem() {
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Bacon"));

        assertEquals(1, pedido.getItens().size());
        assertEquals("X-Bacon", pedido.getItens().get(0));
    }

    // CT02 - desfazer remove o item adicionado
    @Test
    @DisplayName("CT02 - desfazer deve remover item adicionado")
    void testDesfazerAdicao() {
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Frango"));
        gerenciador.desfazer();

        assertEquals(0, pedido.getItens().size());
    }

    // CT03 - historico cresce a cada comando executado
    @Test
    @DisplayName("CT03 - historico deve crescer a cada comando executado")
    void testTamanhoHistorico() {
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Bacon"));
        gerenciador.executarComando(new AdicionarItemComando(pedido, "Batata"));
        gerenciador.executarComando(new AdicionarItemComando(pedido, "Suco"));

        assertEquals(3, gerenciador.tamanhoHistorico());
    }

    // CT04 - desfazer diminui o historico
    @Test
    @DisplayName("CT04 - desfazer deve diminuir o tamanho do historico")
    void testDesfazerDiminuiHistorico() {
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Bacon"));
        gerenciador.executarComando(new AdicionarItemComando(pedido, "Batata"));
        gerenciador.desfazer();

        assertEquals(1, gerenciador.tamanhoHistorico());
    }

    // CT05 - AplicarDescontoComando aplica desconto corretamente
    @Test
    @DisplayName("CT05 - executarComando deve aplicar desconto ao pedido")
    void testAplicarDesconto() {
        gerenciador.executarComando(new AplicarDescontoComando(pedido, 15.0));

        assertEquals(15.0, pedido.getDesconto());
    }

    // CT06 - desfazer AplicarDesconto restaura desconto anterior
    @Test
    @DisplayName("CT06 - desfazer deve restaurar desconto anterior")
    void testDesfazerDesconto() {
        gerenciador.executarComando(new AplicarDescontoComando(pedido, 10.0));
        gerenciador.executarComando(new AplicarDescontoComando(pedido, 20.0));
        gerenciador.desfazer();

        assertEquals(10.0, pedido.getDesconto());
    }

    // CT07 - reexecutar reaplicar o comando desfeito
    @Test
    @DisplayName("CT07 - reexecutar deve reaplicar o ultimo comando desfeito")
    void testReexecutar() {
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Tudo"));
        gerenciador.desfazer();

        assertEquals(0, pedido.getItens().size());

        gerenciador.reexecutar();

        assertEquals(1, pedido.getItens().size());
        assertEquals("X-Tudo", pedido.getItens().get(0));
    }

    // CT08 - RemoverItemComando remove item corretamente
    @Test
    @DisplayName("CT08 - executarComando deve remover item do pedido")
    void testRemoverItem() {
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Bacon"));
        gerenciador.executarComando(new AdicionarItemComando(pedido, "Batata"));
        gerenciador.executarComando(new RemoverItemComando(pedido, "X-Bacon"));

        assertEquals(1, pedido.getItens().size());
        assertEquals("Batata", pedido.getItens().get(0));
    }

    // CT09 - desfazer RemoverItem restaura o item
    @Test
    @DisplayName("CT09 - desfazer remocao deve restaurar o item")
    void testDesfazerRemocao() {
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Bacon"));
        gerenciador.executarComando(new RemoverItemComando(pedido, "X-Bacon"));
        gerenciador.desfazer();

        assertEquals(1, pedido.getItens().size());
        assertEquals("X-Bacon", pedido.getItens().get(0));
    }

    // CT10 - executar novo comando apos desfazer limpa a pilha de refeitos
    @Test
    @DisplayName("CT10 - novo comando apos desfazer deve limpar pilha de refeitos")
    void testNovoComandoLimpaRefeitos() {
        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Bacon"));
        gerenciador.desfazer();

        assertEquals(1, gerenciador.tamanhoRefeitos());

        gerenciador.executarComando(new AdicionarItemComando(pedido, "X-Frango"));

        assertEquals(0, gerenciador.tamanhoRefeitos());
    }
}