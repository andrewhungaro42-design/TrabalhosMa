package Interpreter;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        System.out.println("=== Hamburgueria Interpreter Burguer ===\n");

        Parser  parser  = new Parser();
        Contexto contexto = new Contexto();

        List<String> comandos = List.of(
                "ADICIONAR X-Bacon SEM cebola SEM maionese",
                "ADICIONAR Milk-Shake",
                "ADICIONAR Fritas",
                "REMOVER Fritas",
                "ADICIONAR Onion-Rings SEM sal"
        );

        for (String cmd : comandos) {
            System.out.println("Processando: \"" + cmd + "\"");
            Expression expr = parser.parse(cmd);
            expr.interpret(contexto);
        }

        contexto.imprimirResumo();
    }
}