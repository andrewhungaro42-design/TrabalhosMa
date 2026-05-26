package prototype;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Padrão Prototype — Hamburgueria")
class PrototypeHamburgueriaTest {

    private HamburguerPrototipo classico;
    private RegistroPrototipos registro;

    @BeforeEach
    void setUp() {
        classico = new HamburguerPrototipo(
                "X-Burguer Clássico", 22.90, "Lanche",
                List.of("Pão brioche", "Carne 150g", "Queijo cheddar", "Alface", "Tomate", "Maionese")
        );
        registro = new RegistroPrototipos();
        registro.registrar("classico", classico);
        registro.registrar("vegano", new HamburguerPrototipo(
                "Vegano Verde", 27.50, "Lanche",
                List.of("Pão integral", "Hambúrguer de grão", "Alface", "Tomate", "Abacate")
        ));
    }

    // ── 1. Clone é uma instância nova ──────────────────────────────────────

    @Test
    @DisplayName("clonar() retorna uma instância diferente do original")
    void cloneDeveSerInstanciaDistinta() {
        HamburguerPrototipo clone = classico.clonar();
        assertNotSame(classico, clone);
    }

    @Test
    @DisplayName("clonar() copia corretamente os atributos do original")
    void cloneDeveTerMesmosAtributos() {
        HamburguerPrototipo clone = classico.clonar();
        assertEquals(classico.getNome(), clone.getNome());
        assertEquals(classico.getPreco(), clone.getPreco(), 0.001);
        assertEquals(classico.getCategoria(), clone.getCategoria());
        assertEquals(classico.getIngredientes(), clone.getIngredientes());
    }

    // ── 2. Cópia profunda da lista de ingredientes ─────────────────────────

    @Test
    @DisplayName("Modificar ingredientes do clone não afeta o original")
    void modificarCloneNaoAfetaOriginal() {
        HamburguerPrototipo clone = classico.clonar();
        clone.adicionarIngrediente("Bacon");

        assertFalse(classico.getIngredientes().contains("Bacon"),
                "Original não deve conter ingrediente adicionado ao clone");
    }

    @Test
    @DisplayName("Remover ingrediente do clone não afeta o original")
    void removerDoCloneNaoAfetaOriginal() {
        HamburguerPrototipo clone = classico.clonar();
        clone.removerIngrediente("Alface");

        assertTrue(classico.getIngredientes().contains("Alface"),
                "Original deve manter o ingrediente removido do clone");
    }

    @Test
    @DisplayName("Renomear clone não altera nome do original")
    void renomearCloneNaoAfetaOriginal() {
        HamburguerPrototipo clone = classico.clonar();
        clone.setNome("X-Especial");

        assertEquals("X-Burguer Clássico", classico.getNome());
    }

    @Test
    @DisplayName("Alterar preço do clone não altera preço do original")
    void alterarPrecoCloneNaoAfetaOriginal() {
        HamburguerPrototipo clone = classico.clonar();
        clone.setPreco(99.99);

        assertEquals(22.90, classico.getPreco(), 0.001);
    }

    // ── 3. RegistroPrototipos ──────────────────────────────────────────────

    @Test
    @DisplayName("clonar() via registro retorna instância distinta do protótipo")
    void registroClonaInstanciaDistinta() {
        Prototipavel clone = registro.clonar("classico");
        assertNotSame(classico, clone);
    }

    @Test
    @DisplayName("Chave inexistente no registro lança IllegalArgumentException")
    void chaveInexistenteLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> registro.clonar("inexistente"));
    }

    @Test
    @DisplayName("contemChave retorna true para chave registrada")
    void contemChaveRetornaTrue() {
        assertTrue(registro.contemChave("classico"));
        assertFalse(registro.contemChave("frango"));
    }

    @Test
    @DisplayName("totalRegistrados reflete a quantidade correta")
    void totalRegistradosCorreto() {
        assertEquals(2, registro.totalRegistrados());
        registro.registrar("duplo", new HamburguerPrototipo("X-Duplo", 35.90, "Lanche", List.of()));
        assertEquals(3, registro.totalRegistrados());
    }

    // ── 4. PersonalizacaoHamburguer ────────────────────────────────────────

    @Test
    @DisplayName("comExtras adiciona ingredientes ao clone sem afetar o original")
    void comExtrasAdicionaAoClone() {
        HamburguerPrototipo personalizado = new PersonalizacaoHamburguer(classico)
                .comExtras("Bacon", "Ovo")
                .criar();

        assertTrue(personalizado.getIngredientes().contains("Bacon"));
        assertTrue(personalizado.getIngredientes().contains("Ovo"));
        assertFalse(classico.getIngredientes().contains("Bacon"));
    }

    @Test
    @DisplayName("semIngrediente remove do clone sem afetar o original")
    void semIngredienteRemoveDoClone() {
        HamburguerPrototipo personalizado = new PersonalizacaoHamburguer(classico)
                .semIngrediente("Maionese")
                .criar();

        assertFalse(personalizado.getIngredientes().contains("Maionese"));
        assertTrue(classico.getIngredientes().contains("Maionese"));
    }

    @Test
    @DisplayName("renomear altera apenas o nome do clone")
    void renomearAlteraApenasClone() {
        HamburguerPrototipo personalizado = new PersonalizacaoHamburguer(classico)
                .renomear("X-Especial da Casa")
                .criar();

        assertEquals("X-Especial da Casa", personalizado.getNome());
        assertEquals("X-Burguer Clássico", classico.getNome());
    }

    @Test
    @DisplayName("comDesconto aplica percentual correto sobre o preço clonado")
    void comDescontoAplicaPercentualCorreto() {
        HamburguerPrototipo personalizado = new PersonalizacaoHamburguer(classico)
                .comDesconto(10)
                .criar();

        assertEquals(22.90 * 0.90, personalizado.getPreco(), 0.001);
        assertEquals(22.90, classico.getPreco(), 0.001);
    }

    @Test
    @DisplayName("Encadeamento fluente de personalizações funciona corretamente")
    void encadeamentoFluenteFunciona() {
        HamburguerPrototipo pedido = new PersonalizacaoHamburguer(classico)
                .semIngrediente("Alface")
                .comExtras("Bacon", "Ovo")
                .renomear("X-Completo")
                .comDesconto(5)
                .criar();

        assertFalse(pedido.getIngredientes().contains("Alface"));
        assertTrue(pedido.getIngredientes().contains("Bacon"));
        assertTrue(pedido.getIngredientes().contains("Ovo"));
        assertEquals("X-Completo", pedido.getNome());
        assertEquals(22.90 * 0.95, pedido.getPreco(), 0.001);

        assertEquals("X-Burguer Clássico", classico.getNome());
        assertTrue(classico.getIngredientes().contains("Alface"));
    }

    // ── 5. Múltiplos clones independentes ─────────────────────────────────

    @Test
    @DisplayName("Dois clones do mesmo protótipo são independentes entre si")
    void doisClonesIndependentes() {
        HamburguerPrototipo clone1 = classico.clonar();
        HamburguerPrototipo clone2 = classico.clonar();

        clone1.adicionarIngrediente("Bacon");

        assertFalse(clone2.getIngredientes().contains("Bacon"),
                "clone2 não deve ser afetado por mudança no clone1");
    }
}