package builder;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Lanche simples (builder personalizado) ===");
        Cozinheiro cozinheiro = new Cozinheiro(new LanchePersonalizadoBuilder());
        Lanche simples = cozinheiro.construirLancheSimples();
        System.out.println(simples);

        System.out.println("\n=== Lanche completo (builder personalizado) ===");
        cozinheiro.setBuilder(new LanchePersonalizadoBuilder());
        Lanche completo = cozinheiro.construirLancheCompleto();
        System.out.println(completo);

        System.out.println("\n=== Lanche vegetariano (builder personalizado) ===");
        cozinheiro.setBuilder(new LanchePersonalizadoBuilder());
        Lanche vegetariano = cozinheiro.construirLancheVegetariano();
        System.out.println(vegetariano);

        System.out.println("\n=== Lanche padrão da casa (builder padrão) ===");
        cozinheiro.setBuilder(new LanchePadraoBuilder());
        Lanche padrao = cozinheiro.construirLancheSimples();
        System.out.println(padrao);

        System.out.println("\n=== Uso direto (sem director) com encadeamento ===");
        Lanche custom = new LanchePersonalizadoBuilder()
                .adicionarPao("Australiano")
                .adicionarCarne("Duplo Smash 90g")
                .adicionarQueijo("Muçarela")
                .adicionarMolho("Chipotle")
                .adicionarExtra("Picles")
                .build();
        System.out.println(custom);
    }
}