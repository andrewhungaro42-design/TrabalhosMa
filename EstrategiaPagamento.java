package strategy;

public interface EstrategiaPagamento {
    void pagar(double valor);
    String getNome();
}