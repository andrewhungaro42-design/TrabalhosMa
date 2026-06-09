package Interpreter;

import java.util.ArrayList;
import java.util.List;


public class Contexto {

    private List<String> itensPedido = new ArrayList<>();
    private List<String> restricoes   = new ArrayList<>();
    private List<String> log          = new ArrayList<>();

    public void adicionarItem(String item) {
        itensPedido.add(item);
        log.add("+ Adicionado: " + item);
    }

    public void removerItem(String item) {
        boolean removido = itensPedido.remove(item);
        if (removido) {
            log.add("- Removido: " + item);
        } else {
            log.add("! Item não encontrado para remover: " + item);
        }
    }

    public void adicionarRestricao(String restricao) {
        restricoes.add(restricao);
        log.add("* Restrição: SEM " + restricao);
    }

    public List<String> getItensPedido() {
        return itensPedido;
    }

    public List<String> getRestricoes() {
        return restricoes;
    }

    public List<String> getLog() {
        return log;
    }

    public void imprimirResumo() {
        System.out.println("\n===== RESUMO DO PEDIDO =====");
        System.out.println("Itens: " + itensPedido);
        System.out.println("Restrições: " + restricoes);
        System.out.println("--- Log ---");
        log.forEach(System.out::println);
        System.out.println("============================\n");
    }
}