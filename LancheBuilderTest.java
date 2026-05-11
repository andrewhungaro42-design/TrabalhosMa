package builder;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LancheBuilderTest {

    // CT01 - build() retorna um Lanche não nulo
    @Test
    @DisplayName("CT01 - build() deve retornar um objeto Lanche não nulo")
    void testBuildRetornaLancheNaoNulo() {
        Lanche lanche = new LanchePersonalizadoBuilder().build();
        assertNotNull(lanche);
    }

    // CT02 - Campos configurados corretamente
    @Test
    @DisplayName("CT02 - Os campos devem refletir os valores passados ao builder")
    void testCamposConfigurados() {
        Lanche lanche = new LanchePersonalizadoBuilder()
                .adicionarPao("Brioche")
                .adicionarCarne("Angus")
                .adicionarQueijo("Cheddar")
                .adicionarMolho("Barbecue")
                .build();

        assertEquals("Brioche",  lanche.getPao());
        assertEquals("Angus",    lanche.getCarne());
        assertEquals("Cheddar",  lanche.getQueijo());
        assertEquals("Barbecue", lanche.getMolho());
    }

    // CT03 - Extras são adicionados corretamente
    @Test
    @DisplayName("CT03 - Extras devem ser acumulados na lista")
    void testExtrasAdicionados() {
        Lanche lanche = new LanchePersonalizadoBuilder()
                .adicionarExtra("Bacon")
                .adicionarExtra("Ovo")
                .build();

        assertEquals(2, lanche.getExtras().size());
        assertTrue(lanche.getExtras().contains("Bacon"));
        assertTrue(lanche.getExtras().contains("Ovo"));
    }

    // CT04 - Lanche sem extras tem lista vazia
    @Test
    @DisplayName("CT04 - Lanche sem extras deve ter lista de extras vazia")
    void testSemExtrasListaVazia() {
        Lanche lanche = new LanchePersonalizadoBuilder()
                .adicionarPao("Tradicional")
                .build();

        assertNotNull(lanche.getExtras());
        assertTrue(lanche.getExtras().isEmpty());
    }

    // CT05 - Builder padrão já vem com valores definidos
    @Test
    @DisplayName("CT05 - LanchePadraoBuilder deve ter valores padrão preenchidos")
    void testBuilderPadraoTemValoresPadrao() {
        Lanche lanche = new LanchePadraoBuilder().build();

        assertNotNull(lanche.getPao());
        assertNotNull(lanche.getCarne());
        assertNotNull(lanche.getQueijo());
        assertNotNull(lanche.getMolho());
    }

    // CT06 - Director constrói lanche completo com todos os campos
    @Test
    @DisplayName("CT06 - Director deve produzir lanche completo sem campos nulos")
    void testDirectorLancheCompleto() {
        Cozinheiro cozinheiro = new Cozinheiro(new LanchePersonalizadoBuilder());
        Lanche lanche = cozinheiro.construirLancheCompleto();

        assertNotNull(lanche.getPao());
        assertNotNull(lanche.getCarne());
        assertNotNull(lanche.getQueijo());
        assertNotNull(lanche.getMolho());
        assertFalse(lanche.getExtras().isEmpty());
    }

    // CT07 - Encadeamento fluente retorna o próprio builder
    @Test
    @DisplayName("CT07 - Métodos do builder devem retornar a própria instância (fluent API)")
    void testEncadeamentoFluente() {
        LancheBuilder builder = new LanchePersonalizadoBuilder();

        assertSame(builder, builder.adicionarPao("Brioche"));
        assertSame(builder, builder.adicionarCarne("Angus"));
        assertSame(builder, builder.adicionarQueijo("Cheddar"));
        assertSame(builder, builder.adicionarMolho("Molho"));
        assertSame(builder, builder.adicionarExtra("Bacon"));
    }

    // CT08 - Dois builders independentes geram objetos distintos
    @Test
    @DisplayName("CT08 - Builders distintos devem gerar instâncias de Lanche independentes")
    void testBuildersGeramInstanciasIndependentes() {
        Lanche l1 = new LanchePersonalizadoBuilder().adicionarPao("Brioche").build();
        Lanche l2 = new LanchePersonalizadoBuilder().adicionarPao("Integral").build();

        assertNotSame(l1, l2);
        assertNotEquals(l1.getPao(), l2.getPao());
    }
}