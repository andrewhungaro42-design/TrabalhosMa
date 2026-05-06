package chain_of_Responsibility;

public class Reclamacao {
    private String descricao;
    private int nivel; // 1 = simples, 2 = intermediária, 3 = grave

    public Reclamacao(String descricao, int nivel) {
        this.descricao = descricao;
        this.nivel = nivel;
    }

    public String getDescricao() { return descricao; }
    public int getNivel()        { return nivel; }
}

