package proxy;

public class PedidoProxy implements Pedido {

    private RealPedido realPedido;
    private String nomeCliente;
    private double saldoCliente;
    private String ultimaMensagem;

    public PedidoProxy(String nomeCliente, double saldoCliente) {
        this.nomeCliente = nomeCliente;
        this.saldoCliente = saldoCliente;
        this.realPedido = new RealPedido();
        this.ultimaMensagem = "";
    }

    @Override
    public void realizarPedido(String item, double preco) {
        System.out.println("[Proxy] Verificando pedido de " + nomeCliente + "...");

        if (preco <= 0) {
            ultimaMensagem = "Erro: preço inválido para o item '" + item + "'.";
            System.out.println("[Proxy] " + ultimaMensagem);
            return;
        }

        if (saldoCliente >= preco) {
            saldoCliente -= preco;
            ultimaMensagem = "Pedido autorizado para " + nomeCliente + ".";
            System.out.println("[Proxy] " + ultimaMensagem + " Saldo restante: R$ " + String.format("%.2f", saldoCliente));
            realPedido.realizarPedido(item, preco);
        } else {
            ultimaMensagem = "Pedido negado: saldo insuficiente. Saldo: R$ "
                    + String.format("%.2f", saldoCliente)
                    + ", Preço: R$ " + String.format("%.2f", preco);
            System.out.println("[Proxy] " + ultimaMensagem);
        }
    }

    @Override
    public String consultarStatus() {
        return realPedido.consultarStatus();
    }

    public double getSaldoCliente() {
        return saldoCliente;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }
}
