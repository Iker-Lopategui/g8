import java.util.Map;

/**
 * Classe para realizar um teste de ponta a ponta no sistema de gestão de hemogramas.
 * Este teste simula o processo de adicionar valores ao hemograma de um paciente e
 * comparar esses valores com os valores de referência.
 */
public class PontaAPonta {

    // Variável para armazenar o resultado do teste
    private String resultado;
    
    // Variável para armazenar a referência de valores
    private String referencia;

    /**
     * Construtor da classe PontaAPonta.
     * Inicializa o teste criando um paciente, preenchendo um hemograma e comparando os valores obtidos com os valores de referência.
     */
    public PontaAPonta() {
        String databasePath = System.getProperty("user.dir") + "/pacientes.db";
        PacienteRepository pacienteRepository;
        ValoresReferencia valoresReferencia;
        Database db = new Database(databasePath);
        valoresReferencia = new ValoresReferencia();
        
        // Cria um paciente e um hemograma
        Paciente paciente = new Paciente("Jeovane", 23, "Masculino Adulto (18 a 59 anos)");
        Hemograma hemograma = new Hemograma();

        // Adiciona valores ao hemograma
        hemograma.adicionarValor("Hemoglobina", 15.8);
        hemograma.adicionarValor("Hematócrito", 49);
        hemograma.adicionarValor("Volume Celular Médio", 92);
        hemograma.adicionarValor("Hemoglobina Celular Média", 29);
        hemograma.adicionarValor("Concentração de Hemoglobina Celular", 35);
        hemograma.adicionarValor("Contagem de Glóbulos Vermelhos", 5.5);
        hemograma.adicionarValor("Amplitude de Distribuição dos Eritrócitos", 8);
        hemograma.adicionarValor("Contagem de Glóbulos Brancos", 8000);
        hemograma.adicionarValor("Contagem Diferencial de Glóbulos Brancos - Neutrófilos Bastonetes", 720);
        hemograma.adicionarValor("Contagem Diferencial de Glóbulos Brancos - Neutrófilos Segmentados", 7900);
        hemograma.adicionarValor("Contagem Diferencial de Glóbulos Brancos - Linfócitos", 3500);
        hemograma.adicionarValor("Contagem Diferencial de Glóbulos Brancos - Monócitos", 756);
        hemograma.adicionarValor("Contagem Diferencial de Glóbulos Brancos - Eosinófilos", 475);
        hemograma.adicionarValor("Contagem Diferencial de Glóbulos Brancos - Basófilos", 156);
        hemograma.adicionarValor("Contagem de Plaquetas", 8000);

        // Comparação com os valores de referência
        StringBuilder resultados = new StringBuilder();
        Map<String, FaixaValores> valoresReferenciaUsuario = null;

        try {
            valoresReferenciaUsuario = valoresReferencia.obterValores("Masculino Adulto (18 a 59 anos)");
            if (valoresReferenciaUsuario == null) {
                resultados.append("Erro: Categoria de referência não encontrada.\n");
            } else {
                // Percorre os valores do hemograma e compara com os valores de referência
                for (String parametro : hemograma.getValores().keySet()) {
                    double valor = hemograma.obterValor(parametro);
                    FaixaValores faixaValores = valoresReferenciaUsuario.get(parametro);
                    if (faixaValores == null) {
                        resultados.append(parametro).append(": Valor de referência não encontrado.\n");
                    } else if (valor < faixaValores.getMin()) {
                        resultados.append(parametro).append(" está abaixo do valor de referência: ").append(valor).append(" ")
                                .append(faixaValores.getUnidade()).append(" (Valor de referência: ")
                                .append(faixaValores.getMin()).append(" - ").append(faixaValores.getMax()).append(" ")
                                .append(faixaValores.getUnidade()).append(")\n");
                    } else if (valor > faixaValores.getMax()) {
                        resultados.append(parametro).append(" está acima do valor de referência: ").append(valor).append(" ")
                                .append(faixaValores.getUnidade()).append(" (Valor de referência: ")
                                .append(faixaValores.getMin()).append(" - ").append(faixaValores.getMax()).append(" ")
                                .append(faixaValores.getUnidade()).append(")\n");
                    }
                }
            }
        } catch (Exception e) {
            resultados.append("Erro ao obter valores de referência: ").append(e.getMessage()).append("\n");
            e.printStackTrace();
        }

        // Salvando os resultados no paciente
        paciente.setResultados(resultados.toString());

        // Armazenando o resultado na variável resultado
        resultado = "Resultados armazenados:\n" + paciente.getResultados();
    }

    /**
     * Define a referência de valores.
     * @param referencia A string que representa a referência de valores.
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Retorna a referência de valores.
     * @return A referência de valores fornecida.
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Retorna o resultado do teste.
     * @return Uma string contendo os resultados do teste de ponta a ponta.
     */
    public String getResultado() {
        return resultado;
    }
}
