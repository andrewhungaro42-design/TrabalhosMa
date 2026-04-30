package Decorator;

public class Bacon extends HamburguerDecorator {

    public Bacon(Hamburguer hamburguer) {
        super(hamburguer);
    }

    public float getPrecoItem() {
        return 2.5f;
    }

    public String getNomeEstrutura() {
        return "Bacon";
    }
}