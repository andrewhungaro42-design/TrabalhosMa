package bridge;

public class Hamburguer extends LancheBridge {

    public Hamburguer(Preparo preparo) {
        super(preparo);
    }

    public String fazer() {
        return "Hamburguer " + preparo.preparar();
    }
}
