package builder;

public interface LancheBuilder {
    LancheBuilder adicionarPao(String tipo);
    LancheBuilder adicionarCarne(String tipo);
    LancheBuilder adicionarQueijo(String tipo);
    LancheBuilder adicionarMolho(String tipo);
    LancheBuilder adicionarExtra(String extra);
    Lanche build();
}
