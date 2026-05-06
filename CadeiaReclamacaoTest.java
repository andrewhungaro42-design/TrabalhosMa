package chain_of_Responsibility;

public class CadeiaReclamacaoTest {
}
import org.junit.jupiter.api.*;
        import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class CadeiaReclamacaoTest {

    private Atendente atendente;
    private Gerente   gerente;
    private Diretor   diretor;
    private ByteArrayOutputStream saida;

    @BeforeEach
    void setUp() {
        atendente = new Atendente("Carlos");
        gerente   = new Gerente("Ana");
        diretor   = new Diretor("Roberto");

        atendente.setProximo(gerente);
        gerente.setProximo(diretor);

        saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    // CT01 - Reclamação nível 1 é tratada pelo Atendente
    @Test
    @DisplayName("CT01 - Reclamação nível 1 deve ser tratada pelo Atendente")
    void testNivel1TratadoPeloAtendente() {
        atendente.tratarReclamacao(new Reclamacao("Lanche frio", 1));
        assertTrue(saida.toString().contains("Atendente Carlos"));
    }

    // CT02 - Reclamação nível 2 é tratada pelo Gerente
    @Test
    @DisplayName("CT02 - Reclamação nível 2 deve ser escalada ao Gerente")
    void testNivel2TratadoPeloGerente() {
        atendente.tratarReclamacao(new Reclamacao("Cobrança errada", 2));
        String output = saida.toString();
        assertTrue(output.contains("Atendente Carlos"));
        assertTrue(output.contains("Gerente Ana"));
    }

    // CT03 - Reclamação nível 3 é tratada pelo Diretor
    @Test
    @DisplayName("CT03 - Reclamação nível 3 deve ser escalada ao Diretor")
    void testNivel3TratadoPeloDiretor() {
        atendente.tratarReclamacao(new Reclamacao("Intoxicação alimentar", 3));
        String output = saida.toString();
        assertTrue(output.contains("Gerente Ana"));
        assertTrue(output.contains("Diretor Roberto"));
    }

    // CT04 - Reclamação sem handler exibe mensagem adequada
    @Test
    @DisplayName("CT04 - Reclamação sem handler deve informar que não há responsável")
    void testSemHandler() {
        atendente.tratarReclamacao(new Reclamacao("Processo judicial", 4));
        assertTrue(saida.toString().contains("Nenhum handler disponível"));
    }

    // CT05 - Cadeia curta: apenas Atendente, sem próximo
    @Test
    @DisplayName("CT05 - Handler sem próximo definido deve exibir mensagem de cadeia vazia")
    void testHandlerSemProximo() {
        Atendente sozinho = new Atendente("João");
        sozinho.tratarReclamacao(new Reclamacao("Cobrança errada", 2));
        assertTrue(saida.toString().contains("Nenhum handler disponível"));
    }

    // CT06 - setProximo pode ser alterado dinamicamente
    @Test
    @DisplayName("CT06 - Deve ser possível reconfigurar a cadeia em tempo de execução")
    void testReconfiguraCadeia() {
        Diretor diretorNovo = new Diretor("Fernanda");
        gerente.setProximo(diretorNovo);

        atendente.tratarReclamacao(new Reclamacao("Caso grave", 3));
        assertTrue(saida.toString().contains("Diretor Fernanda"));
    }

    // CT07 - Diretor trata sem passar adiante quando nível <= 3
    @Test
    @DisplayName("CT07 - Diretor deve resolver nível 3 sem passar adiante")
    void testDiretorResolveSemEscalar() {
        diretor.tratarReclamacao(new Reclamacao("Grave", 3));
        String output = saida.toString();
        assertTrue(output.contains("Diretor Roberto"));
        assertFalse(output.contains("Nenhum handler disponível"));
    }

    // CT08 - Reclamação nível 1 não chega ao Gerente
    @Test
    @DisplayName("CT08 - Reclamação nível 1 não deve chegar ao Gerente")
    void testNivel1NaoChegaAoGerente() {
        atendente.tratarReclamacao(new Reclamacao("Lanche frio", 1));
        assertFalse(saida.toString().contains("Gerente Ana"));
    }
}