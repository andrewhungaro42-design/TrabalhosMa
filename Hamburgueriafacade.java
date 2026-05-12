package Façade;


public class HamburgueriaFacade {

    private final Estoque   estoque;
    private final Cozinha   cozinha;
    private final Pagamento pagamento;
    private final Entrega   entrega;

    public HamburgueriaFacade() {
        this.estoque   = new Estoque();
        this.cozinha   = new Cozinha();
        this.pagamento = new Pagamento();
        this.entrega   = new Entrega();
    }


    public HamburgueriaFacade(Estoque estoque, Cozinha cozinha,
                              Pagamento pagamento, Entrega entrega) {
        this.estoque   = estoque;
        this.cozinha   = cozinha;
        this.pagamento = pagamento;
        this.entrega   = entrega;
    }


    public boolean realizarPedido(String cliente, ItemCardapio prato) {
        System.out.println("\n========================================");
        System.out.println("  NOVO PEDIDO — " + cliente);
        System.out.println("========================================");

        if (!verificarEstoque(prato)) {
            System.out.println("[Facade] Pedido CANCELADO: item(ns) indisponível(is) no estoque.");
            return false;
        }


        double total = prato.getPreco();
        boolean pago = pagamento.processarPagamento(cliente, total);
        if (!pago) {
            System.out.println("[Facade] Pedido CANCELADO: falha no pagamento.");
            return false;
        }
        pagamento.emitirNotaFiscal(cliente, total);

        reservarIngredientes(prato);
        cozinha.iniciarPreparo(prato.getNome());
        cozinha.finalizarPreparo(prato.getNome());


        entrega.despacharPedido(cliente, prato.getNome());
        entrega.notificarCliente(cliente);

        System.out.println("========================================");
        System.out.printf("  Pedido de '%s' concluído! Total: R$ %.2f%n",
                prato.getNome(), total);
        System.out.println("========================================\n");
        return true;
    }


    public void cancelarPedido(String cliente, ItemCardapio prato) {
        System.out.println("\n[Facade] Cancelando pedido de " + cliente + "...");
        cozinha.cancelarPreparo(prato.getNome());
        liberarIngredientes(prato);
        pagamento.estornarPagamento(cliente, prato.getPreco());
        System.out.println("[Facade] Pedido cancelado e estorno realizado.\n");
    }


    public void exibirCardapio(ItemCardapio item) {
        System.out.println("\n=== Cardápio ===");
        item.exibir(0);
        System.out.printf("Preço total: R$ %.2f%n%n", item.getPreco());
    }

    private boolean verificarEstoque(ItemCardapio item) {
        if (item instanceof PratoComposto composto) {
            for (ItemCardapio filho : composto.getItens()) {
                if (!verificarEstoque(filho)) return false;
            }
            return true;
        }
        return estoque.verificarDisponibilidade(item.getNome());
    }

    private void reservarIngredientes(ItemCardapio item) {
        if (item instanceof PratoComposto composto) {
            for (ItemCardapio filho : composto.getItens()) {
                reservarIngredientes(filho);
            }
        } else {
            estoque.reservarIngrediente(item.getNome());
        }
    }

    private void liberarIngredientes(ItemCardapio item) {
        if (item instanceof PratoComposto composto) {
            for (ItemCardapio filho : composto.getItens()) {
                liberarIngredientes(filho);
            }
        } else {
            estoque.liberarIngrediente(item.getNome());
        }
    }
}