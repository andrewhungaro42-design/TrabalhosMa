package Interpreter;


public class RemoverExpression implements Expression {

    private final String item;

    public RemoverExpression(String item) {
        this.item = item;
    }

    @Override
    public void interpret(Contexto contexto) {
        contexto.removerItem(item);
    }
}