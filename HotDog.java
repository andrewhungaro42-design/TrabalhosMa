package bridge;

public class HotDog extends LancheBridge {

    public HotDog(Preparo preparo) {
        super(preparo);
    }

    public String fazer() {
        return "Hot Dog " + preparo.preparar();
    }
}
