package builder;

public class LanchePersonalizadoBuilder implements LancheBuilder {

    private Lanche lanche;

    public LanchePersonalizadoBuilder() {
        this.lanche = new Lanche();
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