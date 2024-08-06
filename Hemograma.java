import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa um hemograma contendo diversos valores de exame.
 */
public class Hemograma {

    // Mapa para armazenar os valores dos exames
    public Map<String, Double> valores;

    /**
     * Construtor da classe Hemograma.
     * Inicializa o mapa de valores.
     */
    public Hemograma() {
        valores = new HashMap<>();
    }

    /**
     * Adiciona um valor ao hemograma.
     * @param parametro O nome do parâmetro do hemograma.
     * @param valor O valor do parâmetro.
     */
    public void adicionarValor(String parametro, double valor) {
        valores.put(parametro, valor);
    }

    /**
     * Obtém o valor de um parâmetro específico do hemograma.
     * @param parametro O nome do parâmetro.
     * @return O valor do parâmetro.
     */
    public double obterValor(String parametro) {
        return valores.getOrDefault(parametro, Double.NaN); // Retorna NaN se o parâmetro não for encontrado
    }

    /**
     * Obtém todos os valores do hemograma.
     * @return Um mapa contendo todos os parâmetros e seus respectivos valores.
     */
    public Map<String, Double> getValores() {
        return valores;
    }
}
