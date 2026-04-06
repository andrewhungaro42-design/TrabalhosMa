package Singleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {

    @Test
    public void testInstanciaNaoNula() {
        Parametros instancia = Parametros.getInstance();
        assertNotNull(instancia, "A instância do Singleton não deve ser nula");
    }

    @Test
    public void testMesmaInstancia() {
        Parametros instancia1 = Parametros.getInstance();
        Parametros instancia2 = Parametros.getInstance();
        assertSame(instancia1, instancia2, "getInstance() deve retornar sempre a mesma instância");
    }

    @Test
    public void testSetAndGetTipoLanche() {
        Parametros parametros = Parametros.getInstance();
        parametros.settipoLanche("Hamburguer");
        assertEquals("Hamburguer", parametros.gettipoLanche(),
                "O tipo de lanche deve ser retornado corretamente");
    }

    @Test
    public void testAlteracaoRefletidaEmOutraReferencia() {
        Parametros ref1 = Parametros.getInstance();
        Parametros ref2 = Parametros.getInstance();

        ref1.settipoLanche("HotDog");

        assertEquals("HotDog", ref2.gettipoLanche(),
                "Alteração via ref1 deve ser visível via ref2 (mesma instância)");
    }
}
