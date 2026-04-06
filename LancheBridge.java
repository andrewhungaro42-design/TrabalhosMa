package bridge;

public abstract class LancheBridge {
    protected Preparo preparo;

    public LancheBridge(Preparo preparo) {
        this.preparo = preparo;
    }

    public abstract String fazer();
}
