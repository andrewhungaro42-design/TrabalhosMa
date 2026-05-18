package memento;

import java.util.ArrayList;
import java.util.List;

public class PedidoMemento {

    private final String lanche;
    private final String bebida;
    private final List<String> extras;
    private final String observacao;

    public PedidoMemento(String lanche, String bebida,
                         List<String> extras, String observacao) {
        this.lanche     = lanche;
        this.bebida     = bebida;
        this.extras     = new ArrayList<>(extras); // cópia defensiva
        this.observacao = observacao;
    }

    public String       getLanche()     { return lanche; }
    public String       getBebida()     { return bebida; }
    public List<String> getExtras()     { return new ArrayList<>(extras); }
    public String       getObservacao() { return observacao; }
}