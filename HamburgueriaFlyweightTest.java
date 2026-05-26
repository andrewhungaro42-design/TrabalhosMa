package Flyweight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Padrão Flyweight — Hamburgueria")
class HamburgueriaFlyweightTest {

    private FabricaIngredientes fabrica;

    @BeforeEach
    void setUp() {
        fabrica = new FabricaIngredientes();
    }

    // ── 1. Compartilhamento de instâncias ───────────────────────────────────

    @Test
    @DisplayName("Mesma instância é retornada para o mesmo ingrediente")
    void deveRetornarMesmaInstanciaParaMesmoIngrediente() {
        Ingrediente primeira = fabrica.getIngrediente("Carne Bovina", 300, 8.00);
        Ingrediente segunda  = fabrica.getIngrediente("Carne Bovina", 300, 8.00);

        assertSame(primeira, segunda,
                "O Flyweight deve retornar a mesma instância para o mesmo ingrediente");
    }

    @Test
    @DisplayName("Instâncias diferentes para ingredientes diferentes")
    void deveRetornarInstanciasDiferentesParaIngredientesDiferentes() {
        Ingrediente carne  = fabrica.getIngrediente("Carne Bovina",  300, 8.00);
        Ingrediente queijo = fabrica.getIngrediente("Queijo Cheddar", 110, 2.00);

        assertNotSame(carne, queijo,
                "Ingredientes distintos devem ter instâncias distintas");
    }

    // ── 2. Estado intrínseco ────────────────────────────────────────────────

    @Test
    @DisplayName("Estado intrínseco é preservado corretamente")
    void devePreservarEstadoIntriseco() {
        Ingrediente ing = fabrica.getIngrediente("Bacon", 80, 3.00);

        assertEquals("Bacon", ing.getNome());
        assertEquals(3.00, ing.getPrecoUnitario(), 0.001);
    }

    @Test
    @DisplayName("Flyweight reutilizado mantém estado intrínseco original")
    void flyweightReutilizadoMantemEstadoOriginal() {
        fabrica.getIngrediente("Alface", 5, 0.50);
        // Tenta criar com parâmetros diferentes — deve ignorar e retornar o original
        Ingrediente reutilizado = fabrica.getIngrediente("Alface", 999, 99.00);

        assertEquals("Alface",  reutilizado.getNome());
        assertEquals(0.50, reutilizado.getPrecoUnitario(), 0.001,
                "O estado intrínseco original não deve ser alterado");
    }

    // ── 3. Estado extrínseco isolado ────────────────────────────────────────

    @Test
    @DisplayName("Quantidade (estado extrínseco) é independente em cada ItemHamburguer")
    void estadoExtrinsecoDeverSerIndependente() {
        Ingrediente tomate = fabrica.getIngrediente("Tomate", 18, 0.80);

        ItemHamburguer item1 = new ItemHamburguer(tomate, 1);
        ItemHamburguer item2 = new ItemHamburguer(tomate, 5);

        assertSame(item1.getIngrediente(), item2.getIngrediente(),
                "Ambos os itens devem apontar para o MESMO flyweight");
        assertNotEquals(item1.getQuantidade(), item2.getQuantidade(),
                "Mas com quantidades diferentes (estado extrínseco independente)");
    }

    // ── 4. Cálculo de preços ─────────────────────────────────────────────────

    @Test
    @DisplayName("ItemHamburguer calcula preço corretamente")
    void deveCalcularPrecoDoItemCorretamente() {
        Ingrediente queijo = fabrica.getIngrediente("Queijo Cheddar", 110, 2.00);
        ItemHamburguer item = new ItemHamburguer(queijo, 3);

        assertEquals(6.00, item.calcularPreco(), 0.001,
                "Preço = 3 fatias × R$ 2,00 = R$ 6,00");
    }

    @Test
    @DisplayName("Hamburguer calcula preço total somando todos os itens")
    void deveCalcularPrecoTotalDoHamburguer() {
        Hamburguer burger = new Hamburguer("Teste", fabrica);
        burger.adicionarIngrediente("Pão Brioche",    220, 3.50, 1);  // 3,50
        burger.adicionarIngrediente("Carne Bovina",   300, 8.00, 1);  // 8,00
        burger.adicionarIngrediente("Queijo Cheddar", 110, 2.00, 2);  // 4,00

        assertEquals(15.50, burger.calcularPrecoTotal(), 0.001,
                "Total esperado: R$ 3,50 + R$ 8,00 + R$ 4,00 = R$ 15,50");
    }

    // ── 5. Comportamento da fábrica ─────────────────────────────────────────

    @Test
    @DisplayName("Fábrica rastreia corretamente o total de flyweights criados")
    void fabricaDeveContarFlyweightsCriados() {
        fabrica.getIngrediente("Carne Bovina",  300, 8.00);
        fabrica.getIngrediente("Pão Brioche",   220, 3.50);
        fabrica.getIngrediente("Queijo Cheddar", 110, 2.00);
        fabrica.getIngrediente("Carne Bovina",  300, 8.00); // reutilização

        assertEquals(3, fabrica.getTotalIngredientesCriados(),
                "Apenas 3 flyweights distintos devem existir no cache");
    }

    @Test
    @DisplayName("existeNoCache retorna true para ingrediente já registrado")
    void deveReconhecerIngredienteNoCache() {
        fabrica.getIngrediente("Bacon", 80, 3.00);

        assertTrue(fabrica.existeNoCache("Bacon"));
        assertFalse(fabrica.existeNoCache("Abacate"));
    }

    // ── 6. Cenário integrado ─────────────────────────────────────────────────

    @Test
    @DisplayName("Cenário integrado: múltiplos hamburgueres reutilizam flyweights")
    void cenarioIntegrado() {
        Hamburguer classico = new Hamburguer("Clássico", fabrica);
        classico.adicionarIngrediente("Carne Bovina",   300, 8.00, 1);
        classico.adicionarIngrediente("Pão Brioche",    220, 3.50, 1);
        classico.adicionarIngrediente("Queijo Cheddar", 110, 2.00, 2);

        Hamburguer duplo = new Hamburguer("Duplo", fabrica);
        duplo.adicionarIngrediente("Carne Bovina",   300, 8.00, 2); // reutiliza
        duplo.adicionarIngrediente("Pão Brioche",    220, 3.50, 1); // reutiliza
        duplo.adicionarIngrediente("Queijo Cheddar", 110, 2.00, 4); // reutiliza
        duplo.adicionarIngrediente("Bacon",           80, 3.00, 2); // novo

        // Apenas 4 flyweights distintos para 7 itens no total
        assertEquals(4, fabrica.getTotalIngredientesCriados(),
                "Devem existir somente 4 flyweights para todos os hamburgueres");

        // Verifica que o flyweight "Carne Bovina" é realmente compartilhado
        Ingrediente carneClassico = classico.getItens().get(0).getIngrediente();
        Ingrediente carneDuplo    = duplo.getItens().get(0).getIngrediente();
        assertSame(carneClassico, carneDuplo,
                "O flyweight 'Carne Bovina' deve ser a mesma instância nos dois hamburgueres");

        // Quantidades diferentes (estado extrínseco independente)
        assertEquals(1, classico.getItens().get(0).getQuantidade());
        assertEquals(2, duplo.getItens().get(0).getQuantidade());
    }
}
