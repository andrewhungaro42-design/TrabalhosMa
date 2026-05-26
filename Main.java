package Flyweight;

public class Main {

    public static void main(String[] args) {

        FabricaIngredientes fabrica = new FabricaIngredientes();


        Hamburguer classico = new Hamburguer("Clássico", fabrica);
        classico.adicionarIngrediente("Carne Bovina",  300, 8.00, 1);
        classico.adicionarIngrediente("Pão Brioche",   220, 3.50, 1);
        classico.adicionarIngrediente("Queijo Cheddar", 110, 2.00, 2);
        classico.adicionarIngrediente("Alface",          5, 0.50, 1);
        classico.adicionarIngrediente("Tomate",         18, 0.80, 2);

        Hamburguer duplo = new Hamburguer("Duplo Especial", fabrica);
        duplo.adicionarIngrediente("Carne Bovina",  300, 8.00, 2);   // reutiliza
        duplo.adicionarIngrediente("Pão Brioche",   220, 3.50, 1);   // reutiliza
        duplo.adicionarIngrediente("Queijo Cheddar", 110, 2.00, 4);  // reutiliza
        duplo.adicionarIngrediente("Bacon",          80, 3.00, 3);
        duplo.adicionarIngrediente("Tomate",         18, 0.80, 2);   // reutiliza

        Hamburguer vegano = new Hamburguer("Vegano Verde", fabrica);
        vegano.adicionarIngrediente("Hambúrguer de Grão", 180, 6.50, 1);
        vegano.adicionarIngrediente("Pão Integral",       200, 3.00, 1);
        vegano.adicionarIngrediente("Alface",               5, 0.50, 2); // reutiliza
        vegano.adicionarIngrediente("Tomate",              18, 0.80, 3); // reutiliza
        vegano.adicionarIngrediente("Abacate",             80, 4.00, 1);

        classico.exibir();
        duplo.exibir();
        vegano.exibir();

        fabrica.listarIngredientes();
        System.out.printf(
                "%nTotal de ingredientes DISTINTOS criados (flyweights): %d%n",
                fabrica.getTotalIngredientesCriados()
        );
        System.out.println("(Sem Flyweight seriam ~15 objetos; com Flyweight são apenas "
                + fabrica.getTotalIngredientesCriados() + ")");
    }
}