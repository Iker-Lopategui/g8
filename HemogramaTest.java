

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A classe de teste HemogramaTest.
 *
 * @author  (seu nome)
 * @version (um número da versão ou uma data)
 */
public class HemogramaTest
{
    /**
     * Construtor default para a classe de teste HemogramaTest
     */
    public HemogramaTest()
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
    public void testeHemograma1()
    {
        Hemograma hemogram1 = new Hemograma();
        hemogram1.adicionarValor("Hemoglobina", 350);
        assertEquals("Hemoglobina", hemogram1.getParametro());
        assertEquals(350, hemogram1.getValor());
        hemogram1.adicionarValor("g", 450);
        assertEquals("g", hemogram1.getParametro());
        assertEquals(450, hemogram1.getValor());
    }
}

