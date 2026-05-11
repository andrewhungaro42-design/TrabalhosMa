package TemplateMathode;

public abstract class PreparoLanche {

    // Template Method — final: nenhuma subclasse pode alterar a sequência
    public final void prepararLanche() {
        System.out.println("\n===== Iniciando preparo: " + getNomeLanche() + " =====");
        prepararPao();
        grelhaCarne();
        adicionarMolho();   // hook — opcional
        adicionarExtras();
        embrulhar();
        System.out.println("===== " + getNomeLanche() + " pronto! =====");
    }

    // Passos abstratos — obrigatórios nas subclasses
    protected abstract void prepararPao();
    protected abstract void grelhaCarne();
    protected abstract void adicionarExtras();
    protected abstract String getNomeLanche();

    // Passo concreto — igual para todos os lanches
    protected void embrulhar() {
        System.out.println("  [Embrulho] Embrulhando no papel manteiga e selando.");
    }

    // Hook — subclasses podem sobrescrever; padrão é não fazer nada
    protected void adicionarMolho() {
        // padrão vazio
    }
}