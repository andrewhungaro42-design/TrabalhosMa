package Mediator;

public abstract class SetorBase implements Setor {

    protected Mediador mediador;
    protected String nome;

    public SetorBase(String nome, Mediador mediador) {
        this.nome     = nome;
        this.mediador = mediador;
    }

    public void enviar(String mensagem) {
        mediador.enviarMensagem(mensagem, this);
    }

    public void enviarPara(String mensagem, String destino) {
        mediador.enviarParaSetor(mensagem, destino);
    }

    @Override
    public String getNome() {
        return nome;
    }
}