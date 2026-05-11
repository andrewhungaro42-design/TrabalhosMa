package TemplateMathode;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class PreparoLancheTest {

    private ByteArrayOutputStream saida;

    @BeforeEach
    void setUp() {
        saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    // CT01 - Sequência de passos é respeitada no Clássico
    @Test
    @DisplayName("CT01 - LancheClassico deve executar todos os passos na ordem correta")
    void testSequenciaClassico() {
        new LancheClassico().prepararLanche();
        String out = saida.toString();

        int iPao     = out.indexOf("[Pão]");
        int iCarne   = out.indexOf("[Carne]");
        int iMolho   = out.indexOf("[Molho]");
        int iExtras  = out.indexOf("[Extras]");
        int iEmbrulho = out.indexOf("[Embrulho]");

        assertTrue(iPao     < iCarne,    "Pão deve vir antes da carne");
        assertTrue(iCarne   < iMolho,    "Carne deve vir antes do molho");
        assertTrue(iMolho   < iExtras,   "Molho deve vir antes dos extras");
        assertTrue(iExtras  < iEmbrulho, "Extras devem vir antes do embrulho");
    }

    // CT02 - Hook adicionarMolho não é chamado no Vegetariano
    @Test
    @DisplayName("CT02 - LancheVegetariano não deve adicionar molho (hook vazio)")
    void testVegetarianoSemMolho() {
        new LancheVegetariano().prepararLanche();
        assertFalse(saida.toString().contains("[Molho]"));
    }

    // CT03 - Hook adicionarMolho é chamado no Clássico
    @Test
    @DisplayName("CT03 - LancheClassico deve adicionar molho (hook sobrescrito)")
    void testClassicoComMolho() {
        new LancheClassico().prepararLanche();
        assertTrue(saida.toString().contains("[Molho]"));
    }

    // CT04 - LancheGourmet sobrescreve embrulhar()
    @Test
    @DisplayName("CT04 - LancheGourmet deve usar embrulho personalizado")
    void testGourmetEmbrulhoPersonalizado() {
        new LancheGourmet().prepararLanche();
        assertTrue(saida.toString().contains("tábua de madeira"));
    }

    // CT05 - LancheClassico usa embrulho padrão da base
    @Test
    @DisplayName("CT05 - LancheClassico deve usar embrulho padrão da classe base")
    void testClassicoEmbrulhoPadrao() {
        new LancheClassico().prepararLanche();
        assertTrue(saida.toString().contains("papel manteiga"));
    }

    // CT06 - getNomeLanche retorna o nome correto de cada subclasse
    @Test
    @DisplayName("CT06 - Cada subclasse deve retornar seu nome de lanche")
    void testNomesLanches() {
        new LancheClassico().prepararLanche();
        assertTrue(saida.toString().contains("Lanche Clássico"));

        saida.reset();
        new LancheVegetariano().prepararLanche();
        assertTrue(saida.toString().contains("Lanche Vegetariano"));

        saida.reset();
        new LancheGourmet().prepararLanche();
        assertTrue(saida.toString().contains("Lanche Gourmet"));
    }

    // CT07 - prepararLanche() não lança exceção em nenhuma subclasse
    @Test
    @DisplayName("CT07 - prepararLanche() não deve lançar exceção em nenhuma subclasse")
    void testNaoLancaExcecao() {
        assertDoesNotThrow(() -> new LancheClassico().prepararLanche());
        assertDoesNotThrow(() -> new LancheVegetariano().prepararLanche());
        assertDoesNotThrow(() -> new LancheGourmet().prepararLanche());
    }

    // CT08 - LancheGourmet executa molho especial
    @Test
    @DisplayName("CT08 - LancheGourmet deve aplicar molho especial")
    void testGourmetMolhoEspecial() {
        new LancheGourmet().prepararLanche();
        assertTrue(saida.toString().contains("[Molho]"));
        assertTrue(saida.toString().contains("Aioli"));
    }
}