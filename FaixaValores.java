/**
 * Representa uma faixa de valores com uma unidade de medida.
 * <p>
 * Esta classe é usada para definir uma faixa de valores para um determinado parâmetro
 * laboratorial, incluindo um valor mínimo, um valor máximo e a unidade de medida associada.
 * </p>
 */
public class FaixaValores {
    private double minimo;
    private double maximo;
    private String unidade;

    /**
     * Construtor para criar uma faixa de valores.
     * 
     * @param minimo O valor mínimo da faixa. Deve ser um número positivo ou zero.
     * @param maximo O valor máximo da faixa. Deve ser maior ou igual ao valor mínimo.
     * @param unidade A unidade de medida da faixa, como "g/dL", "%", "fL", etc.
     */
    public FaixaValores(double minimo, double maximo, String unidade) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.unidade = unidade;
    }

    /**
     * Obtém o valor mínimo da faixa.
     * 
     * @return O valor mínimo da faixa.
     */
    public double getMinimo() {
        return minimo;
    }

    /**
     * Obtém o valor máximo da faixa.
     * 
     * @return O valor máximo da faixa.
     */
    public double getMaximo() {
        return maximo;
    }

    /**
     * Obtém a unidade de medida da faixa.
     * 
     * @return A unidade de medida da faixa.
     */
    public String getUnidade() {
        return unidade;
    }
}
