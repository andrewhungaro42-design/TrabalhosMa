package bridge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BridgeTest {

    @Test
    public void testHamburguerFrito() {
        Preparo preparo = new Frito();
        LancheBridge lanche = new Hamburguer(preparo);
        assertEquals("Hamburguer Frito", lanche.fazer(),
                "Hamburguer com preparo Frito deve retornar 'Hamburguer Frito'");
    }

    @Test
    public void testHamburguerGrellhado() {
        Preparo preparo = new Grelhado();
        LancheBridge lanche = new Hamburguer(preparo);
        assertEquals("Hamburguer Grelhado", lanche.fazer(),
                "Hamburguer com preparo Grelhado deve retornar 'Hamburguer Grelhado'");
    }

    @Test
    public void testHotDogFrito() {
        Preparo preparo = new Frito();
        LancheBridge lanche = new HotDog(preparo);
        assertEquals("Hot Dog Frito", lanche.fazer(),
                "HotDog com preparo Frito deve retornar 'Hot Dog Frito'");
    }

    @Test
    public void testHotDogGrellhado() {
        Preparo preparo = new Grelhado();
        LancheBridge lanche = new HotDog(preparo);
        assertEquals("Hot Dog Grelhado", lanche.fazer(),
                "HotDog com preparo Grelhado deve retornar 'Hot Dog Grelhado'");
    }

    @Test
    public void testPreparoFritoRetornaFrito() {
        Preparo frito = new Frito();
        assertEquals("Frito", frito.preparar(),
                "Preparo Frito deve retornar 'Frito'");
    }

    @Test
    public void testPreparoGrelhadoRetornaGrelhado() {
        Preparo grelhado = new Grelhado();
        assertEquals("Grelhado", grelhado.preparar(),
                "Preparo Grelhado deve retornar 'Grelhado'");
    }
}
