package Iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CardapioConcreto implements Cardapio {

    private final List<ItemCardapio> itens = new ArrayList<>();


    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public int totalItens() {
        return itens.size();
    }

    public List<ItemCardapio> getItens() {
        return Collections.unmodifiableList(itens);
    }


    @Override
    public IteradorCardapio criarIterador() {
        return new CardapioIterador(itens);
    }

    public IteradorCardapio criarIteradorCategoria(String categoria) {
        return new IteradorCategoria(itens, categoria);
    }

    public IteradorCardapio criarIteradorOrdenado() {
        return new IteradorOrdenado(itens);
    }
}