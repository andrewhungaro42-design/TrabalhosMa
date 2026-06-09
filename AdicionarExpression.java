package Interpreter;


public class AdicionarExpression implements Expression {

    private final String item;

    public AdicionarExpression(String item) {
        this.item = item;
    }

    @Override
    public void interpret(Contexto contexto) {
        contexto.adicionarItem(item);
    }
}