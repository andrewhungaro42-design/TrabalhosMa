package Decorator;

public class Salada extends HamburguerDecorator {

    public Salada(Hamburguer hamburguer) {
        super(hamburguer);
    }

    public float getPrecoItem() {
        return 1.0f;
    }

    public String getNomeEstrutura() {
        return "Saladinha";
    }
}
