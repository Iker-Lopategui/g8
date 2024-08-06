import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Representa um paciente no banco de dados.
 */
@DatabaseTable(tableName = "paciente")
public class Paciente {

    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(canBeNull = false)
    private String nome;
    
    @DatabaseField(canBeNull = false)
    private int idade;
    
    @DatabaseField(canBeNull = false)
    private String categoriaReferencia; // Categoria de referência escolhida
    
    @DatabaseField(canBeNull = true)
    private String resultados;

    // Construtor padrão obrigatório para ORMLite
    public Paciente() {
    }
    
    /**
     * Construtor com parâmetros para inicializar um paciente.
     *
     * @param nome  Nome do paciente
     * @param idade Idade do paciente
     * @param categoriaReferencia Categoria de referência escolhida
     */
    public Paciente(String nome, int idade, String categoriaReferencia) {
        this.nome = nome;
        this.idade = idade;
        this.categoriaReferencia = categoriaReferencia;
    }
    
    // Getters e Setters
    
    /**
     * Obtém o ID do paciente.
     *
     * @return ID do paciente
     */
    public int getId() {
        return id;
    }
    
    /**
     * Define o ID do paciente.
     *
     * @param id ID do paciente
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Obtém o nome do paciente.
     *
     * @return Nome do paciente
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Define o nome do paciente.
     *
     * @param nome Nome do paciente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Obtém a idade do paciente.
     *
     * @return Idade do paciente
     */
    public int getIdade() {
        return idade;
    }
    
    /**
     * Define a idade do paciente.
     *
     * @param idade Idade do paciente
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    /**
     * Obtém a categoria de referência do paciente.
     *
     * @return Categoria de referência escolhida
     */
    public String getCategoriaReferencia() {
        return categoriaReferencia;
    }
    
    /**
     * Define a categoria de referência do paciente.
     *
     * @param categoriaReferencia Categoria de referência escolhida
     */
    public void setCategoriaReferencia(String categoriaReferencia) {
        this.categoriaReferencia = categoriaReferencia;
    }
    
    /**
     * Obtém os resultados do hemograma do paciente.
     *
     * @return Resultados do hemograma
     */
    public String getResultados() {
        return resultados;
    }

    /**
     * Define os resultados do hemograma do paciente.
     *
     * @param resultados Resultados do hemograma
     */
    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", categoriaReferencia='" + categoriaReferencia + '\'' +
                ", resultados='" + resultados + '\'' +
                '}';
    }
}
