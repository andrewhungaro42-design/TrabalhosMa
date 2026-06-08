package command;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<String> itens;
    private double       desconto;
    private int          numeroPedido;

    public Pedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.itens        = new ArrayList<>();
        this.desconto     = 0.0;
    }

    public void adicionarItem(String item) {
        itens.add(item);
        System.out.println("  [Pedido #" + numeroPedido + "] Item adicionado: " + item);
    }

    public void removerItem(String item) {
        if (itens.remove(item)) {
            System.out.println("  [Pedido #" + numeroPedido + "] Item removido: " + item);
        } else {
            System.out.println("  [Pedido #" + numeroPedido + "] Item nao encontrado: " + item);
        }
    }

    public void setDesconto(double percentual) {
        this.desconto = percentual;
        System.out.println("  [Pedido #" + numeroPedido + "] Desconto aplicado: " + percentual + "%");
    }

    public void exibir() {
        System.out.println("  Pedido #" + numeroPedido
                + " | Itens: " + itens
                + " | Desconto: " + desconto + "%");
    }

    public List<String> getItens()    { return new ArrayList<>(itens); }
    public double       getDesconto() { return desconto; }
    public int          getNumeroPedido() { return numeroPedido; }
}