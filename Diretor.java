package chain_of_Responsibility;

public class Diretor extends ManipuladorBase {

    private String nome;

    public Diretor(String nome) {
        this.nome = nome;
    }

    @Override
    public void tratarReclamacao(Reclamacao reclamacao) {
        if (reclamacao.getNivel() <= 3) {
            System.out.println("[Diretor " + nome + "] Reclamação tratada com prioridade máxima: \""
                    + reclamacao.getDescricao() + "\"");
        } else {
            System.out.println("[Diretor " + nome + "] Reclamação fora dos parâmetros conhecidos. Escalando...");
            passarParaProximo(reclamacao);
        }
    }
}