

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A classe de teste FaixaValoresTest.
 *
 * @author  (seu nome)
 * @version (um número da versão ou uma data)
 */
public class FaixaValoresTest
{
    /**
     * Construtor default para a classe de teste FaixaValoresTest
     */
    public FaixaValoresTest()
    {
    }

    /**
     * Define a 'fixture' do teste.
     *
     * Chamado antes de cada método de caso de teste.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Desfaz a 'fixture' do teste.
     *
     * Chamado após cada método de teste de caso.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void TesteFaixaValores()
    {
        FaixaValores faixaVal1 = new FaixaValores(100, 1000, "ml");
        assertEquals(1000, faixaVal1.getMax(), 0.1);
        assertEquals(100, faixaVal1.getMin(), 0.1);
        assertEquals("ml", faixaVal1.getUnidade());
    }
}

