package memento;

import java.util.Stack;

public class HistoricoPedido {

    private final Stack<PedidoMemento> historico = new Stack<>();

    public void salvar(PedidoMemento memento) {
        historico.push(memento);
        System.out.println("  [Histórico] Versão salva. Total: " + historico.size());
    }

    public PedidoMemento desfazer() {
        if (historico.isEmpty()) {
            System.out.println("  [Histórico] Nenhum estado anterior para restaurar.");
            return null;
        }
        PedidoMemento memento = historico.pop();
        System.out.println("  [Histórico] Desfazendo. Versões restantes: " + historico.size());
        return memento;
    }

    public boolean estaVazio() {
        return historico.isEmpty();
    }

    public int tamanho() {
        return historico.size();
    }
}