package Interpreter;

import java.util.ArrayList;
import java.util.List;


public class PedidoExpression implements Expression {

    private final AdicionarExpression adicionar;
    private final List<SemExpression> restricoes;

    public PedidoExpression(AdicionarExpression adicionar, List<SemExpression> restricoes) {
        this.adicionar  = adicionar;
        this.restricoes = restricoes != null ? restricoes : new ArrayList<>();
    }

    @Override
    public void interpret(Contexto contexto) {
        adicionar.interpret(contexto);
        for (SemExpression sem : restricoes) {
            sem.interpret(contexto);
        }
    }
}