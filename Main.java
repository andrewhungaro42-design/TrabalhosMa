package Visitor;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<ItemCardapio> pedido = Arrays.asList(
                new Lanche("X-Bacon",       680, 32.90),
                new Lanche("X-Frango",      520, 28.50),
                new Bebida("Suco de Laranja", 400, 9.00),
                new Bebida("Refrigerante",   350, 7.50),
                new Sobremesa("Milkshake",   450, 18.00),
                new Sobremesa("Brownie",     320, 12.00)
        );

        System.out.println("=== Visitante: Calculadora de Calorias ===");
        CalculadoraCaloria calc = new CalculadoraCaloria();
        for (ItemCardapio item : pedido) item.accept(calc);
        calc.exibirTotal();

        System.out.println("\n=== Visitante: Calculadora de Desconto ===");
        CalculadoraDesconto desconto = new CalculadoraDesconto();
        for (ItemCardapio item : pedido) item.accept(desconto);
        desconto.exibirTotal();

        System.out.println("\n=== Visitante: Relatório Nutricional ===");
        RelatorioNutricional relatorio = new RelatorioNutricional();
        for (ItemCardapio item : pedido) item.accept(relatorio);
        relatorio.exibirRelatorio();
    }
}