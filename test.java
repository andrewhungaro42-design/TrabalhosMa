import org.junit.Test;
import static org.junit.Assert.*;

public class LancheTest {

    @Test
    public void deveCriarLancheComQueijoEBacon() {
        Lanche lanche = new LancheSimples();
        lanche = new QueijoDecorator(lanche);
        lanche = new BaconDecorator(lanche);

        String resultado = lanche.emitir();

        assertEquals("Pão + Queijo + Bacon", resultado);
    }

    @Test
    public void deveCriarLancheSoComQueijo() {
        Lanche lanche = new LancheSimples();
        lanche = new QueijoDecorator(lanche);

        assertEquals("Pão + Queijo", lanche.emitir());
    }
}