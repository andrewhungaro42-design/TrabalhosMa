package chain_of_Responsibility;

public interface ManipuladorReclamacao {
    void setProximo(ManipuladorReclamacao proximo);
    void tratarReclamacao(Reclamacao reclamacao);
}