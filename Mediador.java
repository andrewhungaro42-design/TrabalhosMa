package Mediator;

public interface Mediador {
    void registrarSetor(Setor setor);
    void enviarMensagem(String mensagem, Setor remetente);
    void enviarParaSetor(String mensagem, String nomeDestino);
}