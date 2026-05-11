package TemplateMathode;

public class LancheVegetariano extends PreparoLanche {

    @Override
    protected String getNomeLanche() {
        return "Lanche Vegetariano";
    }

    @Override
    protected void prepararPao() {
        System.out.println("  [Pão] Aquecendo pão integral sem lactose.");
    }

    @Override
    protected void grelhaCarne() {
        System.out.println("  [Carne] Grelhando hambúrguer de grão-de-bico e beterraba.");
    }

    @Override
    protected void adicionarExtras() {
        System.out.println("  [Extras] Rúcula, tomate seco, cenoura ralada e homus.");
    }

    // Não sobrescreve adicionarMolho() → usa o hook vazio da base
}