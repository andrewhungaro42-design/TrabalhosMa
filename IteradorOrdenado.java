package Iterator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class IteradorOrdenado implements IteradorCardapio {

    private final List<ItemCardapio> itensOrdenados;
    private int posicao = 0;

    public IteradorOrdenado(List<ItemCardapio> itens) {
        this.itensOrdenados = new ArrayList<>(itens);
        this.itensOrdenados.sort(Comparator.comparingDouble(ItemCardapio::getPreco));
    }

    @Override
    public boolean hasNext() {
        return posicao < itensOrdenados.size();
    }

    @Override
    public ItemCardapio next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("Sem mais itens ordenados.");
        }
        return itensOrdenados.get(posicao++);
    }
}