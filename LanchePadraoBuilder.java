package builder;

public class LanchePadraoBuilder implements LancheBuilder {

    private Lanche lanche;

    public LanchePadraoBuilder() {
        this.lanche = new Lanche();
        // valores padrão da casa
        this.lanche.setPao("Brioche");
        this.lanche.setCarne("Angus 150g");
        this.lanche.setQueijo("Cheddar");
        this.lanche.setMolho("Especial da casa");
    }

    @Override
    public LancheBuilder adicionarPao(String tipo) {
        lanche.setPao(tipo);
        return this;
    }

    @Override
    public LancheBuilder adicionarCarne(String tipo) {
        lanche.setCarne(tipo);
        return this;
    }

    @Override
    public LancheBuilder adicionarQueijo(String tipo) {
        lanche.setQueijo(tipo);
        return this;
    }

    @Override
    public LancheBuilder adicionarMolho(String tipo) {
        lanche.setMolho(tipo);
        return this;
    }

    @Override
    public LancheBuilder adicionarExtra(String extra) {
        lanche.adicionarExtra(extra);
        return this;
    }

    @Override
    public Lanche build() {
        return lanche;
    }
}