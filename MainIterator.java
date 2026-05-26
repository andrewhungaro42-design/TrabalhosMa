package Iterator;

public class MainIterator {

    public static void main(String[] args) {

        CardapioConcreto cardapio = new CardapioConcreto();

        cardapio.adicionarItem(new ItemCardapio("X-Burguer",        "Clássico com cheddar",      22.90, "Lanche"));
        cardapio.adicionarItem(new ItemCardapio("X-Bacon",          "Duplo com bacon crocante",  29.90, "Lanche"));
        cardapio.adicionarItem(new ItemCardapio("Vegano Verde",      "Grão-de-bico e abacate",   27.50, "Lanche"));
        cardapio.adicionarItem(new ItemCardapio("Coca-Cola 350ml",   "Gelada",                    7.00, "Bebida"));
        cardapio.adicionarItem(new ItemCardapio("Suco de Laranja",   "Natural 500ml",             9.50, "Bebida"));
        cardapio.adicionarItem(new ItemCardapio("Batata Frita P",    "Crocante e sequinha",      12.00, "Acompanhamento"));
        cardapio.adicionarItem(new ItemCardapio("Batata Frita G",    "Porção generosa",          18.00, "Acompanhamento"));
        cardapio.adicionarItem(new ItemCardapio("Milk-shake Choc.",  "Cremoso 400ml",            16.00, "Sobremesa"));
        cardapio.adicionarItem(new ItemCardapio("Brownie",           "Com sorvete de creme",     14.50, "Sobremesa"));

        System.out.println("=== CARDÁPIO COMPLETO ===");
        IteradorCardapio todos = cardapio.criarIterador();
        while (todos.hasNext()) {
            System.out.println("  " + todos.next());
        }

        System.out.println("\n=== APENAS LANCHES ===");
        IteradorCardapio lanches = cardapio.criarIteradorCategoria("Lanche");
        while (lanches.hasNext()) {
            System.out.println("  " + lanches.next());
        }

        System.out.println("\n=== APENAS BEBIDAS ===");
        IteradorCardapio bebidas = cardapio.criarIteradorCategoria("Bebida");
        while (bebidas.hasNext()) {
            System.out.println("  " + bebidas.next());
        }

        System.out.println("\n=== CARDÁPIO ORDENADO POR PREÇO (menor → maior) ===");
        IteradorCardapio ordenado = cardapio.criarIteradorOrdenado();
        while (ordenado.hasNext()) {
            System.out.println("  " + ordenado.next());
        }
    }
}