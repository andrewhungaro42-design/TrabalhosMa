package Interpreter;


public class SemExpression implements Expression {

    private final String ingrediente;

    public SemExpression(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public void interpret(Contexto contexto) {
        contexto.adicionarRestricao(ingrediente);
    }
}
