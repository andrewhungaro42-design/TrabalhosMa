package chain_of_Responsibility;

public class Main {
    public static void main(String[] args) {

        // Montando a cadeia: Atendente -> Gerente -> Diretor
        Atendente atendente = new Atendente("Carlos");
        Gerente   gerente   = new Gerente("Ana");
        Diretor   diretor   = new Diretor("Roberto");

        atendente.setProximo(gerente);
        gerente.setProximo(diretor);

        // Reclamações de diferentes níveis
        Reclamacao r1 = new Reclamacao("Lanche frio", 1);
        Reclamacao r2 = new Reclamacao("Cobrado valor errado", 2);
        Reclamacao r3 = new Reclamacao("Intoxicação alimentar", 3);
        Reclamacao r4 = new Reclamacao("Processo judicial", 4);

        System.out.println("=== Reclamação nível 1 ===");
        atendente.tratarReclamacao(r1);

        System.out.println("\n=== Reclamação nível 2 ===");
        atendente.tratarReclamacao(r2);

        System.out.println("\n=== Reclamação nível 3 ===");
        atendente.tratarReclamacao(r3);

        System.out.println("\n=== Reclamação nível 4 (sem handler) ===");
        atendente.tratarReclamacao(r4);
    }
}