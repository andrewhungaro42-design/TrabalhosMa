package TemplateMathode;

public class LancheGourmet extends PreparoLanche {

    @Override
    protected String getNomeLanche() {
        return "Lanche Gourmet";
    }

    @Override
    protected void prepararPao() {
        System.out.println("  [Pão] Tostando brioche artesanal com manteiga trufada.");
    }

    @Override
    protected void grelhaCarne() {
        System.out.println("  [Carne] Selando blend Angus 200g com osso tutano no cast iron.");
    }

    @Override
    protected void adicionarMolho() {
        System.out.println("  [Molho] Aioli de ervas finas e maionese de parmesão.");
    }

    @Override
    protected void adicionarExtras() {
        System.out.println("  [Extras] Cebola caramelizada, rúcula, tomate confitado e queijo brie.");
    }

    @Override
    protected void embrulhar() {
        System.out.println("  [Embrulho] Servindo em tábua de madeira com guardanapo de tecido.");
    }
}