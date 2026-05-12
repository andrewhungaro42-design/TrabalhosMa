package Composite;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Padrão Composite — Hamburgueria")
class HamburgueriaCompositeTest {

    // ── Fixtures ──────────────────────────────────────────────────────────
    private IngredienteSimples pao;
    private IngredienteSimples carne;
    private IngredienteSimples queijo;

    @BeforeEach
    void setUp() {
        pao    = new IngredienteSimples("Pão de Gergelim", 3.00);
        carne  = new IngredienteSimples("Carne 150g",     10.00);
        queijo = new IngredienteSimples("Queijo Cheddar",  2.50);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-01: IngredienteSimples — criação e preço básico
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-01: IngredienteSimples retorna nome e preço corretos")
    void ct01_ingredienteSimples_getNomeEPreco() {
        assertEquals("Pão de Gergelim", pao.getNome());
        assertEquals(3.00, pao.getPreco(), 0.001);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-02: Nome inválido (vazio / nulo)
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-02: Nome vazio em IngredienteSimples lança IllegalArgumentException")
    void ct02_ingredienteSimples_nomeVazio_lancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new IngredienteSimples("", 5.00));
    }

    @Test
    @DisplayName("CT-02b: Nome nulo em IngredienteSimples lança IllegalArgumentException")
    void ct02b_ingredienteSimples_nomeNulo_lancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new IngredienteSimples(null, 5.00));
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-03: Preço negativo
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-03: Preço negativo em IngredienteSimples lança IllegalArgumentException")
    void ct03_ingredienteSimples_precoNegativo_lancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new IngredienteSimples("Sal", -1.00));
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-04: PratoComposto vazio → preço zero
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-04: PratoComposto sem itens tem preço 0.0")
    void ct04_pratoComposto_vazio_precoZero() {
        PratoComposto combo = new PratoComposto("Combo Vazio");
        assertEquals(0.0, combo.getPreco(), 0.001);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-05: PratoComposto com um ingrediente
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-05: PratoComposto com um ingrediente tem o preço desse ingrediente")
    void ct05_pratoComposto_umIngrediente_precoCorreto() {
        PratoComposto prato = new PratoComposto("Só Pão");
        prato.adicionar(pao);
        assertEquals(3.00, prato.getPreco(), 0.001);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-06: PratoComposto com múltiplos ingredientes
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-06: PratoComposto soma os preços de todos os ingredientes")
    void ct06_pratoComposto_multiploIngredientes_somaCorreta() {
        PratoComposto hamburguer = new PratoComposto("Hambúrguer Clássico");
        hamburguer.adicionar(pao);    // 3,00
        hamburguer.adicionar(carne);  // 10,00
        hamburguer.adicionar(queijo); // 2,50
        assertEquals(15.50, hamburguer.getPreco(), 0.001);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-07: Composição recursiva (Composite dentro de Composite)
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-07: Preço de combo que contém outro prato composto é calculado recursivamente")
    void ct07_composicaoRecursiva_precoCorreto() {
        PratoComposto hamburguer = new PratoComposto("Hambúrguer Clássico");
        hamburguer.adicionar(pao);    // 3,00
        hamburguer.adicionar(carne);  // 10,00
        hamburguer.adicionar(queijo); // 2,50  → total: 15,50

        IngredienteSimples batata = new IngredienteSimples("Batata Frita", 6.00);
        IngredienteSimples refri  = new IngredienteSimples("Refrigerante", 5.00);

        PratoComposto combo = new PratoComposto("Combo Clássico");
        combo.adicionar(hamburguer); // 15,50
        combo.adicionar(batata);     //  6,00
        combo.adicionar(refri);      //  5,00  → total: 26,50

        assertEquals(26.50, combo.getPreco(), 0.001);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-08: adicionar(null) lança exceção
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-08: adicionar(null) lança IllegalArgumentException")
    void ct08_adicionar_itemNulo_lancaExcecao() {
        PratoComposto prato = new PratoComposto("Prato Teste");
        assertThrows(IllegalArgumentException.class, () -> prato.adicionar(null));
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-09: remoção de item existente
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-09: remover item existente retorna true e atualiza o preço")
    void ct09_remover_itemExistente_atualizaPreco() {
        PratoComposto prato = new PratoComposto("Prato Teste");
        prato.adicionar(pao);    // 3,00
        prato.adicionar(carne);  // 10,00

        boolean removido = prato.remover(pao);

        assertTrue(removido);
        assertEquals(10.00, prato.getPreco(), 0.001);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-10: remoção de item inexistente retorna false
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-10: remover item inexistente retorna false")
    void ct10_remover_itemInexistente_retornaFalse() {
        PratoComposto prato = new PratoComposto("Prato Teste");
        prato.adicionar(pao);

        boolean removido = prato.remover(queijo); // queijo não foi adicionado

        assertFalse(removido);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-11: getItens() é não-modificável
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-11: getItens() retorna lista não-modificável")
    void ct11_getItens_listaNaoModificavel() {
        PratoComposto prato = new PratoComposto("Prato Teste");
        prato.adicionar(pao);

        assertThrows(UnsupportedOperationException.class,
                () -> prato.getItens().add(queijo));
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-12: Polimorfismo — tratar folha e composto da mesma forma
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-12: Folha e composto são tratados uniformemente via interface")
    void ct12_polimorfismo_folhaECompostoPelaInterface() {
        PratoComposto hamburguer = new PratoComposto("Hambúrguer Simples");
        hamburguer.adicionar(pao);
        hamburguer.adicionar(carne);

        // Ambos implementam ItemCardapio — cliente não precisa saber o tipo concreto
        ItemCardapio itemA = pao;
        ItemCardapio itemB = hamburguer;

        assertNotNull(itemA.getNome());
        assertNotNull(itemB.getNome());
        assertTrue(itemA.getPreco() >= 0);
        assertTrue(itemB.getPreco() >= 0);
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-13: Nomes corretos após construção
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-13: getNome() retorna exatamente o nome fornecido")
    void ct13_getNome_retornaCorreto() {
        PratoComposto prato = new PratoComposto("Combo Especial");
        assertEquals("Combo Especial", prato.getNome());
        assertEquals("Pão de Gergelim", pao.getNome());
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-14: PratoComposto com nome vazio lança exceção
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-14: Nome vazio em PratoComposto lança IllegalArgumentException")
    void ct14_pratoComposto_nomeVazio_lancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new PratoComposto(""));
    }

    // ────────────────────────────────────────────────────────────────────
    // CT-15: Hierarquia de três níveis
    // ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("CT-15: Hierarquia de três níveis mantém preço correto")
    void ct15_hierarquiaTresNiveis_precoCorreto() {
        // Nível 3 — folhas
        IngredienteSimples paoPequeno  = new IngredienteSimples("Pão Pequeno", 2.00);
        IngredienteSimples carne80     = new IngredienteSimples("Carne 80g",   6.00);
        IngredienteSimples suco        = new IngredienteSimples("Suco Uva",    4.00);

        // Nível 2 — composto
        PratoComposto miniHamburguer = new PratoComposto("Mini Hambúrguer");
        miniHamburguer.adicionar(paoPequeno); // 2,00
        miniHamburguer.adicionar(carne80);    // 6,00  → 8,00

        PratoComposto comboKids = new PratoComposto("Combo Kids");
        comboKids.adicionar(miniHamburguer); //  8,00
        comboKids.adicionar(suco);           //  4,00  → 12,00

        // Nível 1 — raiz
        IngredienteSimples sorvete = new IngredienteSimples("Sorvete", 7.00);
        PratoComposto comboFamilia = new PratoComposto("Combo Família");
        comboFamilia.adicionar(comboKids); // 12,00
        comboFamilia.adicionar(sorvete);   //  7,00  → 19,00

        assertEquals(8.00,  miniHamburguer.getPreco(), 0.001, "Mini Hambúrguer");
        assertEquals(12.00, comboKids.getPreco(),      0.001, "Combo Kids");
        assertEquals(19.00, comboFamilia.getPreco(),   0.001, "Combo Família");
    }
}
