package Mediator;

import java.util.HashMap;
import java.util.Map;

public class CentralDePedidos implements Mediador {

    private Map<String, Setor> setores = new HashMap<>();

    @Override
    public void registrarSetor(Setor setor) {
        setores.put(setor.getNome(), setor);
        System.out.println("[Central] Setor registrado: " + setor.getNome());
    }

    @Override
    public void enviarMensagem(String mensagem, Setor remetente) {
        System.out.println("[Central] Mensagem de '" + remetente.getNome()
                + "' para todos: " + mensagem);
        for (Setor setor : setores.values()) {
            if (!setor.getNome().equals(remetente.getNome())) {
                setor.receberMensagem("[De " + remetente.getNome() + "] " + mensagem);
            }
        }
    }

    @Override
    public void enviarParaSetor(String mensagem, String nomeDestino) {
        Setor destino = setores.get(nomeDestino);
        if (destino != null) {
            System.out.println("[Central] Roteando mensagem para: " + nomeDestino);
            destino.receberMensagem("[Central] " + mensagem);
        } else {
            System.out.println("[Central] ERRO: Setor '" + nomeDestino + "' não encontrado.");
        }
    }
}