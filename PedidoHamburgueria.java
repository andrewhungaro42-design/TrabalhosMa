package state;

public class PedidoHamburgueria {

    private EstadoPedido estadoAtual;
    private int numeroPedido;

    public PedidoHamburgueria(int numeroPedido) {
        this.numeroPedido = numeroPedido;
        this.estadoAtual = new AguardandoState();
        System.out.println("[Pedido #" + numeroPedido + "] Criado. Estado inicial: AGUARDANDO");
    }

    public void setEstado(EstadoPedido novoEstado) {
        this.estadoAtual = novoEstado;
    }

    public EstadoPedido getEstado() {
        return estadoAtual;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void confirmar() {
        estadoAtual.confirmar(this);
    }

    public void preparar() {
        estadoAtual.preparar(this);
    }

    public void entregar() {
        estadoAtual.entregar(this);
    }

    public void cancelar() {
        estadoAtual.cancelar(this);
    }
}