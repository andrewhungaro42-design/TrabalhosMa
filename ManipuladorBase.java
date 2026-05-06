package chain_of_Responsibility;

public abstract class ManipuladorBase implements ManipuladorReclamacao {

    private ManipuladorReclamacao proximo;

    @Override
    public void setProximo(ManipuladorReclamacao proximo) {
        this.proximo = proximo;
    }

    protected void passarParaProximo(Reclamacao reclamacao) {
        if (proximo != null) {
            proximo.tratarReclamacao(reclamacao);
        } else {
            System.out.println("  [Cadeia] Nenhum handler disponível para tratar: \""
                    + reclamacao.getDescricao() + "\"");
        }
    }
}