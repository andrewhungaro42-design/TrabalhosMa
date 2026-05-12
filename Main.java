package Composite;

public class Main {

    public static void main(String[] args) {

        // ── Folhas (ingredientes simples) ───────────────────────────────────
        ItemCardapio paoGergelim    = new IngredienteSimples("Pão de Gergelim",  3.00);
        ItemCardapio carne150       = new IngredienteSimples("Carne 150g",       10.00);
        ItemCardapio queijo         = new IngredienteSimples("Queijo Cheddar",    2.50);

        ItemCardapio paoPequeno     = new IngredienteSimples("Pão Pequeno",       2.00);
        ItemCardapio carne80        = new IngredienteSimples("Carne 80g",         6.00);
        ItemCardapio sucoLaranja    = new IngredienteSimples("Suco de Laranja",   5.00);

        ItemCardapio sobremesa      = new IngredienteSimples("Sorvete Baunilha",  7.00);

        PratoComposto hamburguerClassico = new PratoComposto("Hambúrguer Clássico");
        hamburguerClassico.adicionar(paoGergelim);
        hamburguerClassico.adicionar(carne150);
        hamburguerClassico.adicionar(queijo);

        PratoComposto miniHamburguer = new PratoComposto("Mini Hambúrguer");
        miniHamburguer.adicionar(paoPequeno);
        miniHamburguer.adicionar(carne80);

        PratoComposto comboKids = new PratoComposto("Combo Kids");
        comboKids.adicionar(miniHamburguer);   // composto dentro de composto!
        comboKids.adicionar(sucoLaranja);

        PratoComposto comboFamilia = new PratoComposto("Combo Família");
        comboFamilia.adicionar(hamburguerClassico);
        comboFamilia.adicionar(comboKids);
        comboFamilia.adicionar(sobremesa);

        System.out.println("=== Cardápio Completo ===");
        comboFamilia.exibir(0);

        System.out.println();
        System.out.printf("Total do Combo Família: R$ %.2f%n", comboFamilia.getPreco());
        System.out.printf("Somente Hambúrguer Clássico: R$ %.2f%n", hamburguerClassico.getPreco());
    }
}
