package Façade;


public class Cozinha {

    public void iniciarPreparo(String nomePrato) {
        System.out.println("[Cozinha] Iniciando preparo: " + nomePrato);
    }

    public void finalizarPreparo(String nomePrato) {
        System.out.println("[Cozinha] Prato finalizado: " + nomePrato);
    }

    public void cancelarPreparo(String nomePrato) {
        System.out.println("[Cozinha] Preparo cancelado: " + nomePrato);
    }
}