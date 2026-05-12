package Façade;


public class Pagamento {

    public boolean processarPagamento(String cliente, double valor) {
        System.out.printf("[Pagamento] Processando R$ %.2f para o cliente: %s%n", valor, cliente);
        // Simulação: pagamento sempre aprovado
        System.out.println("[Pagamento] Pagamento aprovado!");
        return true;
    }

    public void estornarPagamento(String cliente, double valor) {
        System.out.printf("[Pagamento] Estornando R$ %.2f para: %s%n", valor, cliente);
    }

    public void emitirNotaFiscal(String cliente, double valor) {
        System.out.printf("[Pagamento] Nota fiscal emitida para %s — Valor: R$ %.2f%n", cliente, valor);
    }
}