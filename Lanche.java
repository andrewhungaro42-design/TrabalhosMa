package abstrac;

public class Lanche {

    private Igredientes igredientes;
    private Valor valor;

    public Lanche (FabricaAbstrata fabrica) {
        this.igredientes = fabrica.createIgredientes();
        this.valor = fabrica.createValor();
    }

    public String emitirIgredientes() {
        return this.igredientes.emitir();
    }

    public String emitirValor() {
        return this.valor.emitir();
    }
}
