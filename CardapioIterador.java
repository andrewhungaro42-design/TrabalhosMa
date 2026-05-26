package Iterator;


import java.util.List;

public class CardapioIterador implements IteradorCardapio {

    private final List<ItemCardapio> itens;
    private int posicao = 0;

    public CardapioIterador(List<ItemCardapio> itens) {
        this.itens = itens;
    }

    @Override
    public boolean hasNext() {
        return posicao < itens.size();
    }

    @Override
    public ItemCardapio next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("Sem mais itens no cardápio.");
        }
        return itens.get(posicao++);
    }
}