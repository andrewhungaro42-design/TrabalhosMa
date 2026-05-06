package chain_of_Responsibility;

public class Atendente extends ManipuladorBase {

    private String nome;

    public Atendente(String nome) {
        this.nome = nome;
    }

    @Override
    public void tratarReclamacao(Reclamacao reclamacao) {
        if (reclamacao.getNivel() <= 1) {
            System.out.println("[Atendente " + nome + "] Reclamação resolvida: \""
                    + reclamacao.getDescricao() + "\"");
        } else {
            System.out.println("[Atendente " + nome + "] Não tenho autoridade. Passando para o Gerente...");
            passarParaProximo(reclamacao);
        }
    }
}