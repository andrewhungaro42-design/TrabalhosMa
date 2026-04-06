package abstrac;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FabricaLancheBasico implements FabricaAbstrata {

    @Override
    public Igredientes createIgredientes() {
        return new ListadeIgredientes();
    }

    @Override
    public Valor createValor() {
        return () -> "R$ 15,00";
    }
}

class FabricaLanchePremium implements FabricaAbstrata {

    @Override
    public Igredientes createIgredientes() {
        return () -> "Lista de Ingredientes Premium";
    }

    @Override
    public Valor createValor() {
        return () -> "R$ 35,00";
    }
}

public class AbstractFactoryTest {

    @Test
    public void testLancheBasicoEmiteIngredientes() {
        FabricaAbstrata fabrica = new FabricaLancheBasico();
        Lanche lanche = new Lanche(fabrica);
        assertEquals("Lista de Igredientes", lanche.emitirIgredientes(),
                "Fábrica básica deve emitir lista padrão de ingredientes");
    }

    @Test
    public void testLancheBasicoEmiteValor() {
        FabricaAbstrata fabrica = new FabricaLancheBasico();
        Lanche lanche = new Lanche(fabrica);
        assertEquals("R$ 15,00", lanche.emitirValor(),
                "Fábrica básica deve emitir o valor correto");
    }

    @Test
    public void testLanchePremiumEmiteIngredientesDiferentes() {
        FabricaAbstrata fabrica = new FabricaLanchePremium();
        Lanche lanche = new Lanche(fabrica);
        assertEquals("Lista de Ingredientes Premium", lanche.emitirIgredientes(),
                "Fábrica premium deve emitir ingredientes premium");
    }

    @Test
    public void testLanchePremiumEmiteValorMaior() {
        FabricaAbstrata fabrica = new FabricaLanchePremium();
        Lanche lanche = new Lanche(fabrica);
        assertEquals("R$ 35,00", lanche.emitirValor(),
                "Fábrica premium deve emitir valor premium");
    }

    @Test
    public void testFabricaCriaIngredientesNaoNulos() {
        FabricaAbstrata fabrica = new FabricaLancheBasico();
        assertNotNull(fabrica.createIgredientes(), "createIgredientes() não deve retornar null");
        assertNotNull(fabrica.createValor(), "createValor() não deve retornar null");
    }

    @Test
    public void testListaDeIngredientesEmiteCorretamente() {
        Igredientes lista = new ListadeIgredientes();
        assertEquals("Lista de Igredientes", lista.emitir(),
                "ListadeIgredientes.emitir() deve retornar o texto correto");
    }
}
