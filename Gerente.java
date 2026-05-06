package chain_of_Responsibility;

public class Gerente extends ManipuladorBase {

    private String nome;

    public Gerente(String nome) {
        this.nome = nome;
    }

    @Override
    public void tratarReclamacao(Reclamacao reclamacao) {
        if (reclamacao.getNivel() <= 2) {
            System.out.println("[Gerente " + nome + "] Reclamação resolvida com autorização especial: \""
                    + reclamacao.getDescricao() + "\"");
        } else {
            System.out.println("[Gerente " + nome + "] Caso grave. Escalando para o Diretor...");
            passarParaProximo(reclamacao);
        }
    }
}