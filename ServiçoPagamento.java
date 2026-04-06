package factory;

public class ServiçoPagamento implements IServico {

    public String executar() {
        return "Pagamento efetivado";
    }

    public String cancelar() {
        return "Pagamento cancelado";
    }
}
