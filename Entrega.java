package Façade;


public class Entrega {

    public void despacharPedido(String cliente, String nomePrato) {
        System.out.printf("[Entrega] Despachando '%s' para o cliente: %s%n", nomePrato, cliente);
    }

    public void notificarCliente(String cliente) {
        System.out.println("[Entrega] Notificação enviada para: " + cliente);
    }
}