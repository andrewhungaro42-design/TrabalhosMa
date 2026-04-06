package Singleton;

public class Parametros {

    private Parametros() {};
    private static Parametros instance = new Parametros();
    public static Parametros getInstance() {
        return instance;
    }

    private String nomeLanche;
    private String tipoLanche;

    public String getNomeEscola() {
        return nomeLanche;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeLanche = nomeLanche;
    }

    public String gettipoLanche() {
        return tipoLanche;
    }

    public void settipoLanche(String tipoLanche) {
        this.tipoLanche = tipoLanche;
    }
}
