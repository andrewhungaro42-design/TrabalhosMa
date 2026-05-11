package builder;

public class Cozinheiro {

    private LancheBuilder builder;

    public Cozinheiro(LancheBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(LancheBuilder builder) {
        this.builder = builder;
    }

    public Lanche construirLancheSimples() {
        return builder
                .adicionarPao("Tradicional")
                .adicionarCarne("Bovina 120g")
                .build();
    }

    public Lanche construirLancheCompleto() {
        return builder
                .adicionarPao("Brioche")
                .adicionarCarne("Angus 180g")
                .adicionarQueijo("Cheddar")
                .adicionarMolho("Barbecue")
                .adicionarExtra("Bacon crocante")
                .adicionarExtra("Ovo estrelado")
                .build();
    }

    public Lanche construirLancheVegetariano() {
        return builder
                .adicionarPao("Integral")
                .adicionarCarne("Hamburguer de grão-de-bico")
                .adicionarQueijo("Provolone")
                .adicionarMolho("Pesto")
                .adicionarExtra("Rúcula")
                .adicionarExtra("Tomate seco")
                .build();
    }
}