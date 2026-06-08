package command;

import java.util.Stack;

public class GerenciadorPedido {

    private final Stack<Comando> historico  = new Stack<>();
    private final Stack<Comando> refeitos   = new Stack<>();

    public void executarComando(Comando comando) {
        comando.executar();
        historico.push(comando);
        refeitos.clear();
        System.out.println("  [Gerenciador] Comando executado. Historico: " + historico.size());
    }

    public void desfazer() {
        if (historico.isEmpty()) {
            System.out.println("  [Gerenciador] Nada para desfazer.");
            return;
        }
        Comando comando = historico.pop();
        comando.desfazer();
        refeitos.push(comando);
        System.out.println("  [Gerenciador] Desfeito. Historico: " + historico.size());
    }

    public void reexecutar() {
        if (refeitos.isEmpty()) {
            System.out.println("  [Gerenciador] Nada para reexecutar.");
            return;
        }
        Comando comando = refeitos.pop();
        comando.executar();
        historico.push(comando);
        System.out.println("  [Gerenciador] Reexecutado. Historico: " + historico.size());
    }

    public int tamanhoHistorico() { return historico.size(); }
    public int tamanhoRefeitos()  { return refeitos.size(); }
    public boolean historicoVazio() { return historico.isEmpty(); }
}