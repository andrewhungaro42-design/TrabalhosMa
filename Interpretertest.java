package Interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes JUnit 5 para o padrão Interpreter aplicado à Hamburgueria.
 *
 * Dependência Maven:
 * <dependency>
 *     <groupId>org.junit.jupiter</groupId>
 *     <artifactId>junit-jupiter</artifactId>
 *     <version>5.10.0</version>
 *     <scope>test</scope>
 * </dependency>
 */
@DisplayName("Testes - Padrão Interpreter: Hamburgueria")
class InterpreterTest {

    private Parser   parser;
    private Contexto contexto;

    @BeforeEach
    void setUp() {
        parser   = new Parser();
        contexto = new Contexto();
    }

    // ---------------------------------------------------------------
    // 1. ADICIONAR item simples
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve adicionar item simples ao contexto")
    void deveAdicionarItemSimples() {
        parser.parse("ADICIONAR X-Bacon").interpret(contexto);

        assertTrue(contexto.getItensPedido().contains("X-Bacon"),
                "X-Bacon deveria estar na lista de itens");
    }

    // ---------------------------------------------------------------
    // 2. ADICIONAR com uma restrição SEM
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve registrar restrição SEM ao adicionar item")
    void deveRegistrarRestricaoSem() {
        parser.parse("ADICIONAR X-Bacon SEM cebola").interpret(contexto);

        assertTrue(contexto.getRestricoes().contains("cebola"),
                "Restrição 'cebola' deveria estar registrada");
    }

    // ---------------------------------------------------------------
    // 3. ADICIONAR com múltiplas restrições SEM
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve registrar múltiplas restrições SEM")
    void deveRegistrarMultiplasRestricoes() {
        parser.parse("ADICIONAR X-Bacon SEM cebola SEM maionese").interpret(contexto);

        assertTrue(contexto.getRestricoes().contains("cebola"),   "Deveria conter 'cebola'");
        assertTrue(contexto.getRestricoes().contains("maionese"), "Deveria conter 'maionese'");
        assertEquals(2, contexto.getRestricoes().size(),           "Deveria ter exatamente 2 restrições");
    }

    // ---------------------------------------------------------------
    // 4. REMOVER item existente
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Deve remover item previamente adicionado")
    void deveRemoverItemExistente() {
        parser.parse("ADICIONAR Fritas").interpret(contexto);
        parser.parse("REMOVER Fritas").interpret(contexto);

        assertFalse(contexto.getItensPedido().contains("Fritas"),
                "Fritas não deveria estar na lista após remoção");
    }

    // ---------------------------------------------------------------
    // 5. REMOVER item inexistente não lança exceção
    // ---------------------------------------------------------------
    @Test
    @DisplayName("REMOVER item inexistente não deve lançar exceção")
    void removerItemInexistenteNaoLancaExcecao() {
        assertDoesNotThrow(() ->
                        parser.parse("REMOVER Suco").interpret(contexto),
                "Remover item inexistente não deveria lançar exceção"
        );
    }

    // ---------------------------------------------------------------
    // 6. Log registra adição corretamente
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Log deve conter registro de item adicionado")
    void logDeveRegistrarAdicao() {
        parser.parse("ADICIONAR Milk-Shake").interpret(contexto);

        assertTrue(contexto.getLog().stream().anyMatch(l -> l.contains("Milk-Shake")),
                "Log deveria conter entrada referente ao Milk-Shake");
    }

    // ---------------------------------------------------------------
    // 7. Log registra remoção corretamente
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Log deve conter registro de item removido")
    void logDeveRegistrarRemocao() {
        parser.parse("ADICIONAR Fritas").interpret(contexto);
        parser.parse("REMOVER Fritas").interpret(contexto);

        long entradas = contexto.getLog().stream()
                .filter(l -> l.contains("Fritas"))
                .count();
        assertTrue(entradas >= 2, "Log deveria ter ao menos 2 entradas para Fritas (add + remove)");
    }

    // ---------------------------------------------------------------
    // 8. Sequência de comandos — estado final correto
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Sequência de comandos deve resultar no estado correto")
    void sequenciaDeComandosDeveResultarEstadoCorreto() {
        List.of(
                "ADICIONAR X-Bacon SEM cebola",
                "ADICIONAR Fritas",
                "ADICIONAR Milk-Shake",
                "REMOVER Fritas"
        ).forEach(cmd -> parser.parse(cmd).interpret(contexto));

        List<String> itens = contexto.getItensPedido();
        assertTrue(itens.contains("X-Bacon"),     "X-Bacon deveria estar no pedido");
        assertTrue(itens.contains("Milk-Shake"),  "Milk-Shake deveria estar no pedido");
        assertFalse(itens.contains("Fritas"),      "Fritas deveria ter sido removida");
        assertEquals(2, itens.size(),              "Pedido deveria ter exatamente 2 itens");
    }

    // ---------------------------------------------------------------
    // 9. Comando desconhecido lança IllegalArgumentException
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Comando desconhecido deve lançar IllegalArgumentException")
    void comandoDesconhecidoDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> parser.parse("CANCELAR X-Bacon"),
                "Comando desconhecido deveria lançar IllegalArgumentException");
    }

    // ---------------------------------------------------------------
    // 10. Comando nulo ou vazio lança IllegalArgumentException
    // ---------------------------------------------------------------
    @Test
    @DisplayName("Comando nulo ou vazio deve lançar IllegalArgumentException")
    void comandoNuloOuVazioDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> parser.parse(null),
                "Comando nulo deveria lançar exceção");

        assertThrows(IllegalArgumentException.class,
                () -> parser.parse("   "),
                "Comando vazio deveria lançar exceção");
    }

    // ---------------------------------------------------------------
    // 11. AdicionarExpression é instância de Expression (polimorfismo)
    // ---------------------------------------------------------------
    @Test
    @DisplayName("AdicionarExpression deve ser instância de Expression")
    void adicionarExpressionDeveImplementarInterface() {
        Expression expr = new AdicionarExpression("X-Salada");
        assertInstanceOf(Expression.class, expr,
                "AdicionarExpression deveria implementar Expression");
    }

    // ---------------------------------------------------------------
    // 12. PedidoExpression sem restrições não adiciona restrições
    // ---------------------------------------------------------------
    @Test
    @DisplayName("PedidoExpression sem restrições não deve poluir lista de restrições")
    void pedidoSemRestricaoNaoDeveAdicionarRestricoes() {
        Expression expr = new PedidoExpression(new AdicionarExpression("Fritas"), null);
        expr.interpret(contexto);

        assertTrue(contexto.getRestricoes().isEmpty(),
                "Lista de restrições deveria estar vazia");
    }
}