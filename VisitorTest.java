package Visitor;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {

    private Lanche    lanche;
    private Bebida    bebida;
    private Sobremesa sobremesa;

    @BeforeEach
    void setUp() {
        lanche    = new Lanche("X-Bacon", 680, 32.90);
        bebida    = new Bebida("Suco", 400, 9.00);
        sobremesa = new Sobremesa("Brownie", 320, 12.00);
    }

    // CT01 - CalculadoraCaloria soma calorias do lanche
    @Test
    @DisplayName("CT01 - CalculadoraCaloria deve contabilizar calorias do lanche")
    void testCaloriaLanche() {
        CalculadoraCaloria calc = new CalculadoraCaloria();
        lanche.accept(calc);
        assertEquals(680, calc.getTotalCalorias());
    }

    // CT02 - CalculadoraCaloria estima calorias da bebida (ml * 0.4)
    @Test
    @DisplayName("CT02 - CalculadoraCaloria deve estimar calorias da bebida")
    void testCaloriaBebida() {
        CalculadoraCaloria calc = new CalculadoraCaloria();
        bebida.accept(calc);
        assertEquals((int)(400 * 0.4), calc.getTotalCalorias());
    }

    // CT03 - CalculadoraCaloria acumula todos os itens
    @Test
    @DisplayName("CT03 - CalculadoraCaloria deve acumular calorias de todos os itens")
    void testCaloriaTotal() {
        CalculadoraCaloria calc = new CalculadoraCaloria();
        lanche.accept(calc);
        bebida.accept(calc);
        sobremesa.accept(calc);

        int esperado = 680 + (int)(400 * 0.4) + 320;
        assertEquals(esperado, calc.getTotalCalorias());
    }

    // CT04 - CalculadoraDesconto aplica 10% no lanche
    @Test
    @DisplayName("CT04 - CalculadoraDesconto deve aplicar 10% no lanche")
    void testDescontoLanche() {
        CalculadoraDesconto desc = new CalculadoraDesconto();
        lanche.accept(desc);
        assertEquals(32.90 * 0.10, desc.getTotalDesconto(), 0.001);
    }

    // CT05 - CalculadoraDesconto aplica 15% na sobremesa
    @Test
    @DisplayName("CT05 - CalculadoraDesconto deve aplicar 15% na sobremesa")
    void testDescontoSobremesa() {
        CalculadoraDesconto desc = new CalculadoraDesconto();
        sobremesa.accept(desc);
        assertEquals(12.00 * 0.15, desc.getTotalDesconto(), 0.001);
    }

    // CT06 - CalculadoraDesconto acumula descontos de múltiplos itens
    @Test
    @DisplayName("CT06 - CalculadoraDesconto deve acumular descontos corretamente")
    void testDescontoAcumulado() {
        CalculadoraDesconto desc = new CalculadoraDesconto();
        lanche.accept(desc);
        bebida.accept(desc);
        sobremesa.accept(desc);

        double esperado = (32.90 * 0.10) + (9.00 * 0.05) + (12.00 * 0.15);
        assertEquals(esperado, desc.getTotalDesconto(), 0.001);
    }

    // CT07 - accept() delega corretamente sem lançar exceção
    @Test
    @DisplayName("CT07 - accept() não deve lançar exceção para nenhum tipo de item")
    void testAcceptNaoLancaExcecao() {
        CalculadoraCaloria calc = new CalculadoraCaloria();
        assertDoesNotThrow(() -> lanche.accept(calc));
        assertDoesNotThrow(() -> bebida.accept(calc));
        assertDoesNotThrow(() -> sobremesa.accept(calc));
    }

    // CT08 - Dois visitantes distintos operam sobre os mesmos itens sem interferência
    @Test
    @DisplayName("CT08 - Dois visitantes diferentes não devem interferir entre si")
    void testVisitantesIndependentes() {
        CalculadoraCaloria  calc = new CalculadoraCaloria();
        CalculadoraDesconto desc = new CalculadoraDesconto();

        lanche.accept(calc);
        lanche.accept(desc);

        assertEquals(680,          calc.getTotalCalorias());
        assertEquals(32.90 * 0.10, desc.getTotalDesconto(), 0.001);
    }
}