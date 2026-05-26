package prototype;


import java.util.Arrays;

public class PersonalizacaoHamburguer {

    private final HamburguerPrototipo clone;

    public PersonalizacaoHamburguer(Prototipavel prototipo) {
        this.clone = (HamburguerPrototipo) prototipo.clonar();
    }

    public PersonalizacaoHamburguer comExtras(String... ingredientes) {
        Arrays.stream(ingredientes).forEach(clone::adicionarIngrediente);
        return this;
    }

    public PersonalizacaoHamburguer semIngrediente(String ingrediente) {
        clone.removerIngrediente(ingrediente);
        return this;
    }

    public PersonalizacaoHamburguer renomear(String novoNome) {
        clone.setNome(novoNome);
        return this;
    }

    public PersonalizacaoHamburguer comDesconto(double percentual) {
        clone.setPreco(clone.getPreco() * (1 - percentual / 100));
        return this;
    }

    public HamburguerPrototipo criar() {
        return clone;
    }
}