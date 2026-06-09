package Interpreter;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    /**
     * Analisa o comando e retorna a Expression correspondente.
     * Lança IllegalArgumentException para comandos desconhecidos.
     */
    public Expression parse(String comando) {
        if (comando == null || comando.isBlank()) {
            throw new IllegalArgumentException("Comando vazio ou nulo.");
        }

        String[] tokens = comando.trim().split("\\s+");

        if (tokens[0].equalsIgnoreCase("ADICIONAR")) {
            return parseAdicionar(tokens);
        }

        if (tokens[0].equalsIgnoreCase("REMOVER")) {
            return parseRemover(tokens);
        }

        throw new IllegalArgumentException("Comando desconhecido: " + tokens[0]);
    }


    private PedidoExpression parseAdicionar(String[] tokens) {
        if (tokens.length < 2) {
            throw new IllegalArgumentException("ADICIONAR requer ao menos um item.");
        }

        String item = tokens[1];
        AdicionarExpression adicionar = new AdicionarExpression(item);
        List<SemExpression> restricoes = new ArrayList<>();

        // Percorre os tokens restantes procurando pares "SEM <ingrediente>"
        for (int i = 2; i < tokens.length - 1; i++) {
            if (tokens[i].equalsIgnoreCase("SEM")) {
                restricoes.add(new SemExpression(tokens[i + 1]));
                i++; // pula o ingrediente já consumido
            }
        }

        return new PedidoExpression(adicionar, restricoes);
    }

    private RemoverExpression parseRemover(String[] tokens) {
        if (tokens.length < 2) {
            throw new IllegalArgumentException("REMOVER requer ao menos um item.");
        }
        return new RemoverExpression(tokens[1]);
    }
}
