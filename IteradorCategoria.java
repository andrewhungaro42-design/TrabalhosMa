package Iterator;


import java.util.List;


public class IteradorCategoria implements IteradorCardapio {

    private final List<ItemCardapio> itens;
    private final String             categoria;
    private int posicao = 0;

    public IteradorCategoria(List<ItemCardapio> itens, String categoria) {
        this.itens     = itens;
        this.categoria = categoria;
        avancarParaProximo(); // já posiciona no primeiro válido
    }

    @Override
    public boolean hasNext() {
        return posicao < itens.size();
    }

    @Override
    public ItemCardapio next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException(
                    "Sem mais itens na categoria: " + categoria);
        }
        ItemCardapio item = itens.get(posicao++);
        avancarParaProximo();
        return item;
    }

    private void avancarParaProximo() {
        while (posicao < itens.size()
                && !itens.get(posicao).getCategoria().equalsIgnoreCase(categoria)) {
            posicao++;
        }
    }
}