package prototype;


import java.util.List;

public class MainPrototype {

    public static void main(String[] args) {

        RegistroPrototipos registro = new RegistroPrototipos();

        registro.registrar("classico", new HamburguerPrototipo(
                "X-Burguer Clássico", 22.90, "Lanche",
                List.of("Pão brioche", "Carne 150g", "Queijo cheddar", "Alface", "Tomate", "Maionese")
        ));

        registro.registrar("vegano", new HamburguerPrototipo(
                "Vegano Verde", 27.50, "Lanche",
                List.of("Pão integral", "Hambúrguer de grão", "Alface", "Tomate", "Abacate", "Mostarda")
        ));

        registro.registrar("duplo", new HamburguerPrototipo(
                "X-Duplo", 35.90, "Lanche",
                List.of("Pão brioche", "Carne 150g", "Carne 150g", "Queijo cheddar", "Queijo cheddar", "Bacon", "Maionese")
        ));

        System.out.println("=== PEDIDOS DO DIA ===\n");

        HamburguerPrototipo pedido1 = (HamburguerPrototipo) registro.clonar("classico");
        pedido1.exibir();

        System.out.println();

        HamburguerPrototipo pedido2 = new PersonalizacaoHamburguer(registro.clonar("classico"))
                .semIngrediente("Maionese")
                .comExtras("Bacon", "Ovo")
                .renomear("X-Burguer Especial")
                .criar();
        pedido2.exibir();

        System.out.println();

        HamburguerPrototipo pedido3 = new PersonalizacaoHamburguer(registro.clonar("vegano"))
                .comDesconto(10)
                .renomear("Vegano Promoção")
                .criar();
        pedido3.exibir();

        System.out.println();

        HamburguerPrototipo original = (HamburguerPrototipo) registro.clonar("classico");
        System.out.println("Original intacto: " + original.getNome() + " - R$ " + original.getPreco());
    }
}