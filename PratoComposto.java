package Composite;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PratoComposto implements ItemCardapio {

    private final String nome;
    private final List<ItemCardapio> itens;

    public PratoComposto(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do prato não pode ser vazio.");
        }
        this.nome = nome;
        this.itens = new ArrayList<>();
    }


    public void adicionar(ItemCardapio item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        itens.add(item);
    }


    public boolean remover(ItemCardapio item) {
        return itens.remove(item);
    }


    public List<ItemCardapio> getItens() {
        return Collections.unmodifiableList(itens);
    }

    // ---------------------------------------------------------------
    // Implementação de ItemCardapio
    // ---------------------------------------------------------------

    @Override
    public String getNome() {
        return nome;
    }


    @Override
    public double getPreco() {
        return itens.stream()
                    .mapToDouble(ItemCardapio::getPreco)
                    .sum();
    }


    @Override
    public void exibir(int nivel) {
        String recuo = "  ".repeat(nivel);
        System.out.printf("%s%s - R$ %.2f%n", recuo, nome, getPreco());
        for (ItemCardapio item : itens) {
            item.exibir(nivel + 1);
        }
    }

    @Override
    public String toString() {
        return String.format("PratoComposto{nome='%s', itens=%d, preco=%.2f}",
                nome, itens.size(), getPreco());
    }
}
