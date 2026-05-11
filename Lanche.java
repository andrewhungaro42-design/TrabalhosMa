package builder;

import java.util.ArrayList;
import java.util.List;

public class Lanche {

    private String pao;
    private String carne;
    private String queijo;
    private String molho;
    private List<String> extras;

    public Lanche() {
        this.extras = new ArrayList<>();
    }

    public void setPao(String pao)       { this.pao    = pao; }
    public void setCarne(String carne)   { this.carne  = carne; }
    public void setQueijo(String queijo) { this.queijo = queijo; }
    public void setMolho(String molho)   { this.molho  = molho; }
    public void adicionarExtra(String extra) { this.extras.add(extra); }

    public String getPao()    { return pao; }
    public String getCarne()  { return carne; }
    public String getQueijo() { return queijo; }
    public String getMolho()  { return molho; }
    public List<String> getExtras() { return extras; }

    @Override
    public String toString() {
        return "Lanche{"
                + "\n  pao='"    + pao    + '\''
                + "\n  carne='"  + carne  + '\''
                + "\n  queijo='" + queijo + '\''
                + "\n  molho='"  + molho  + '\''
                + "\n  extras="  + extras
                + "\n}";
    }
}