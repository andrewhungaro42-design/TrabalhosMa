package TemplateMathode;

public class LancheClassico extends PreparoLanche {

    @Override
    protected String getNomeLanche() {
        return "Lanche Clássico";
    }

    @Override
    protected void prepararPao() {
        System.out.println("  [Pão] Tostando pão tradicional na chapa.");
    }

    @Override
    protected void grelhaCarne() {
        System.out.println("  [Carne] Grelhando hambúrguer bovino 150g ao ponto.");
    }

    @Override
    protected void adicionarMolho() {
        System.out.println("  [Molho] Adicionando ketchup e mostarda.");
    }

    @Override
    protected void adicionarExtras() {
        System.out.println("  [Extras] Alface, tomate e cebola.");
    }
}