package Decorator;

public class Bife extends HamburguerDecorator {

    public Bife(Hamburguer hamburguer) {
        super(hamburguer);
    }

    public float getPrecoItem() {
        return 3.0f;
    }

    public String getNomeEstrutura() {
        return "Bifinho";
    }
}