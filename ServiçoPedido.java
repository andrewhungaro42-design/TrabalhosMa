package factory;

public class ServiçoPedido implements IServico {

    public String executar() {
        return "Pedido efetivado";
    }

    public String cancelar() {
        return "Pedido cancelado";
    }
}
