package Decorator;

public class Queijo extends HamburguerDecorator {

    public Queijo(Hamburguer hamburguer) {
        super(hamburguer);
    }

    public float getPrecoItem() {
        return 2.0f;
    }

    public String getNomeEstrutura() {
        return "Queijinho";
    }
}
