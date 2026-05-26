package Iterator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

ayName("Padrão Iterator — Hamburgueria")
class IteratorHamburgueriaTest {

    private CardapioConcreto cardapio;

    @BeforeEach
    void setUp() {
        cardapio = new CardapioConcreto();
        cardapio.adicionarItem(new ItemCardapio("X-Burguer",       "Clássico",          22.90, "Lanche"));
        cardapio.adicionarItem(new ItemCardapio("X-Bacon",         "Com bacon",         29.90, "Lanche"));
        cardapio.adicionarItem(new ItemCardapio("Vegano Verde",    "Grão-de-bico",      27.50, "Lanche"));
        cardapio.adicionarItem(new ItemCardapio("Coca-Cola",       "Gelada 350ml",       7.00, "Bebida"));
        cardapio.adicionarItem(new ItemCardapio("Suco de Laranja", "Natural 500ml",      9.50, "Bebida"));
        cardapio.adicionarItem(new ItemCardapio("Batata Frita P",  "Crocante",          12.00, "Acompanhamento"));
        cardapio.adicionarItem(new ItemCardapio("Milk-shake",      "Cremoso 400ml",     16.00, "Sobremesa"));
    }

    // ── 1. CardapioIterador ─────────────────────────────────────────────────

    @Test
    @DisplayName("CardapioIterador percorre todos os itens na ordem de inserção")
    void devePercorrerTodosOsItens() {
        IteradorCardapio it = cardapio.criarIterador();
        List<String> nomes = new ArrayList<>();
        while (it.hasNext()) nomes.add(it.next().getNome());

        assertEquals(7, nomes.size());
        assertEquals("X-Burguer", nomes.get(0));
        assertEquals("Milk-shake", nomes.get(nomes.size() - 1));
    }

    @Test
    @DisplayName("hasNext retorna false após o último elemento")
    void hasNextRetornaFalseAoEsgotar() {
        IteradorCardapio it = cardapio.criarIterador();
        while (it.hasNext()) it.next();
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("next lança NoSuchElementException quando esgotado")
    void nextLancaExcecaoQuandoEsgotado() {
        IteradorCardapio it = cardapio.criarIterador();
        while (it.hasNext()) it.next();
        assertThrows(NoSuchElementException.class, it::next);
    }

    // ── 2. IteradorCategoria ────────────────────────────────────────────────

    @Test
    @DisplayName("IteradorCategoria retorna somente itens da categoria solicitada")
    void deveRetornarApenasItensDaCategoriaLanche() {
        IteradorCardapio it = cardapio.criarIteradorCategoria("Lanche");
        List<ItemCardapio> lanches = new ArrayList<>();
        while (it.hasNext()) lanches.add(it.next());

        assertEquals(3, lanches.size());
        assertTrue(lanches.stream().allMatch(i -> i.getCategoria().equals("Lanche")));
    }

    @Test
    @DisplayName("IteradorCategoria retorna somente bebidas")
    void deveRetornarApenasItensDaCategoriaBebida() {
        IteradorCardapio it = cardapio.criarIteradorCategoria("Bebida");
        List<ItemCardapio> bebidas = new ArrayList<>();
        while (it.hasNext()) bebidas.add(it.next());

        assertEquals(2, bebidas.size());
        bebidas.forEach(b -> assertEquals("Bebida", b.getCategoria()));
    }

    @Test
    @DisplayName("IteradorCategoria com categoria inexistente não retorna itens")
    void categoriaInexistenteNaoRetornaItens() {
        IteradorCardapio it = cardapio.criarIteradorCategoria("Pizza");
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("IteradorCategoria: comparação de categoria é case-insensitive")
    void categoriaEhCaseInsensitive() {
        IteradorCardapio it = cardapio.criarIteradorCategoria("lanche");
        int count = 0;
        while (it.hasNext()) { it.next(); count++; }
        assertEquals(3, count);
    }

    // ── 3. IteradorOrdenado ─────────────────────────────────────────────────

    @Test
    @DisplayName("IteradorOrdenado retorna todos os itens em ordem crescente de preço")
    void deveRetornarItensEmOrdemCrescenteDePreco() {
        IteradorCardapio it = cardapio.criarIteradorOrdenado();
        List<Double> precos = new ArrayList<>();
        while (it.hasNext()) precos.add(it.next().getPreco());

        assertEquals(7, precos.size());
        for (int i = 1; i < precos.size(); i++) {
            assertTrue(precos.get(i) >= precos.get(i - 1),
                    "Esperado ordem crescente, mas " + precos.get(i-1) + " > " + precos.get(i));
        }
    }

    @Test
    @DisplayName("IteradorOrdenado não altera a ordem original da coleção")
    void naoDeveAlterarOrdemOriginalDaColecao() {
        cardapio.criarIteradorOrdenado(); // cria mas não consome

        // A coleção original deve manter a ordem de inserção
        IteradorCardapio original = cardapio.criarIterador();
        assertEquals("X-Burguer", original.next().getNome());
    }

    // ── 4. Casos de borda ───────────────────────────────────────────────────

    @Test
    @DisplayName("Cardápio vazio: iterador base não possui próximo elemento")
    void cardapioVazioNaoPossuiProximoElemento() {
        CardapioConcreto vazio = new CardapioConcreto();
        IteradorCardapio it = vazio.criarIterador();
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Cardápio vazio: iterador categoria não possui próximo elemento")
    void cardapioVazioIteradorCategoriaNaoPossuiProximo() {
        CardapioConcreto vazio = new CardapioConcreto();
        IteradorCardapio it = vazio.criarIteradorCategoria("Lanche");
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Cardápio vazio: iterador ordenado não possui próximo elemento")
    void cardapioVazioIteradorOrdenadoNaoPossuiProximo() {
        CardapioConcreto vazio = new CardapioConcreto();
        IteradorCardapio it = vazio.criarIteradorOrdenado();
        assertFalse(it.hasNext());
    }

    // ── 5. Independência entre iteradores ───────────────────────────────────

    @Test
    @DisplayName("Dois iteradores sobre o mesmo cardápio são independentes")
    void doisIteradoresSaoIndependentes() {
        IteradorCardapio it1 = cardapio.criarIterador();
        IteradorCardapio it2 = cardapio.criarIterador();

        // Avança it1 duas vezes
        it1.next();
        it1.next();

        // it2 ainda começa do início
        assertEquals("X-Burguer", it2.next().getNome(),
                "it2 deve começar do primeiro item, independente do it1");
    }

    @Test
    @DisplayName("Iterador ordenado e iterador padrão retornam os mesmos itens (conjuntos iguais)")
    void iteradoresRetornamMesmosItens() {
        List<String> nomesOrigem  = new ArrayList<>();
        List<String> nomesOrdem   = new ArrayList<>();

        IteradorCardapio it1 = cardapio.criarIterador();
        IteradorCardapio it2 = cardapio.criarIteradorOrdenado();

        while (it1.hasNext()) nomesOrigem.add(it1.next().getNome());
        while (it2.hasNext()) nomesOrdem.add(it2.next().getNome());

        assertEquals(nomesOrigem.size(), nomesOrdem.size());
        assertTrue(nomesOrigem.containsAll(nomesOrdem));
    }
}