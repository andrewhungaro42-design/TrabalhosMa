package memento;

import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder {

    private String lanche;
    private String bebida;
    private List<String> extras;
    private String observacao;

    public PedidoBuilder() {
        this.extras = new ArrayList<>();
    }

    public void setLanche(String lanche)         { this.lanche = lanche; }
    public void setBebida(String bebida)         { this.bebida = bebida; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public void adicionarExtra(String extra) {
        this.extras.add(extra);
    }

    public void removerExtra(String extra) {
        this.extras.remove(extra);
    }

    // Cria um memento com o estado atual
    public PedidoMemento salvarEstado() {
        System.out.println("  [Memento] Estado salvo.");
        return new PedidoMemento(lanche, bebida, extras, observacao);
    }

    // Restaura o estado a partir de um memento
    public void restaurarEstado(PedidoMemento memento) {
        this.lanche     = memento.getLanche();
        this.bebida     = memento.getBebida();
        this.extras     = memento.getExtras();
        this.observacao = memento.getObservacao();
        System.out.println("  [Memento] Estado restaurado.");
    }

    public void exibirPedido() {
        System.out.println("  Pedido atual:");
        System.out.println("    Lanche:     " + (lanche     != null ? lanche     : "-"));
        System.out.println("    Bebida:     " + (bebida     != null ? bebida     : "-"));
        System.out.println("    Extras:     " + (extras.isEmpty() ? "-" : extras));
        System.out.println("    Observação: " + (observacao != null ? observacao : "-"));
    }
}