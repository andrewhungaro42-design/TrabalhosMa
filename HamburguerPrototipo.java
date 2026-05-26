package prototype;

import java.util.ArrayList;
import java.util.List;

public class HamburguerPrototipo implements Prototipavel {

    private String nome;
    private List<String> ingredientes;
    private double preco;
    private String categoria;

    public HamburguerPrototipo(String nome, double preco, String categoria, List<String> ingredientes) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.ingredientes = new ArrayList<>(ingredientes);
    }

    @Override
    public HamburguerPrototipo clonar() {
        return new HamburguerPrototipo(this.nome, this.preco, this.categoria, this.ingredientes);
    }

    public void adicionarIngrediente(String ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void removerIngrediente(String ingrediente) {
        ingredientes.remove(ingrediente);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome()              { return nome; }
    public double getPreco()             { return preco; }
    public String getCategoria()         { return categoria; }
    public List<String> getIngredientes(){ return new ArrayList<>(ingredientes); }

    public void exibir() {
        System.out.printf("%-25s | %-15s | R$ %.2f%n", nome, categoria, preco);
        ingredientes.forEach(i -> System.out.println("    - " + i));
    }
}