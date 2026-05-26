package prototype;

import java.util.HashMap;
import java.util.Map;

public class RegistroPrototipos {

    private final Map<String, Prototipavel> prototipos = new HashMap<>();

    public void registrar(String chave, Prototipavel prototipo) {
        prototipos.put(chave, prototipo);
    }

    public Prototipavel clonar(String chave) {
        Prototipavel proto = prototipos.get(chave);
        if (proto == null) {
            throw new IllegalArgumentException("Protótipo não encontrado: " + chave);
        }
        return proto.clonar();
    }

    public boolean contemChave(String chave) {
        return prototipos.containsKey(chave);
    }

    public int totalRegistrados() {
        return prototipos.size();
    }

    public void listar() {
        System.out.println("=== Protótipos registrados ===");
        prototipos.forEach((chave, proto) -> {
            System.out.print("  [" + chave + "] ");
            ((HamburguerPrototipo) proto).exibir();
        });
    }
}