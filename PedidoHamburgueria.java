package observer;

import java.util.ArrayList;
import java.util.List;

public class PedidoHamburgueria implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private int numeroPedido;
    private String statusAtual;

    public PedidoHamburgueria(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.statusAtual = "AGUARDANDO";
    }

    @Override
    public void adicionarObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removerObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservers() {
        for (Observer o : observers) {
            o.atualizar(statusAtual);
        }
    }

    public void setStatus(String novoStatus) {
        this.statusAtual = novoStatus;
        System.out.println("\n[Pedido #" + numeroPedido + "] Status alterado para: " + novoStatus);
        notificarObservers();
    }

    public String getStatus() {
        return statusAtual;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }
}