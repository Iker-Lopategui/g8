import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Map;

/**
 * Classe principal para iniciar a aplicação JavaFX e gerenciar a interface de usuário do sistema de hemograma.
 */
public class Main extends Application {

    private PacienteRepository pacienteRepository;
    private ValoresReferencia valoresReferencia;

    /**
     * Método principal para iniciar a aplicação.
     *
     * @param primaryStage O estágio principal da aplicação
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Caminho para o banco de dados
            String databasePath = System.getProperty("user.dir") + "/pacientes.db";
            Database db = new Database(databasePath);
            pacienteRepository = new PacienteRepository(db.getConnection());
            valoresReferencia = new ValoresReferencia();

            primaryStage.setTitle("Sistema de Hemograma");

            // Criação do GridPane para layout
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(10);
            grid.setHgap(10);

            // Campos de entrada do paciente
            Label nameLabel = new Label("Nome:");
            GridPane.setConstraints(nameLabel, 0, 0);
            TextField nameInput = new TextField();
            GridPane.setConstraints(nameInput, 1, 0);

            Label ageLabel = new Label("Idade:");
            GridPane.setConstraints(ageLabel, 0, 1);
            TextField ageInput = new TextField();
            GridPane.setConstraints(ageInput, 1, 1);

            // Escolha da categoria de referência
            Label categoryLabel = new Label("Categoria de Referência:");
            GridPane.setConstraints(categoryLabel, 0, 2);
            ComboBox<String> categoryChoice = new ComboBox<>();
            categoryChoice.getItems().addAll("Criança (0 a 11 anos)", "Adolescente (12 a 17 anos)",
                    "Masculino Adulto (18 a 59 anos)", "Feminino Adulto (18 a 59 anos)",
                    "Masculino Idoso (60 anos ou mais)", "Feminino Idoso (60 anos ou mais)");
            GridPane.setConstraints(categoryChoice, 1, 2);

            // Campos de entrada do hemograma
            Label hemogramLabel = new Label("DADOS DO HEMOGRAMA:");
            GridPane.setConstraints(hemogramLabel, 0, 3);
            GridPane.setColumnSpan(hemogramLabel, 2);

            // Lista de parâmetros do hemograma
            String[] parametros = {
                "Hemoglobina",
                "Hematócrito",
                "Volume Celular Médio",
                "Hemoglobina Celular Média",
                "Concentração de Hemoglobina Celular",
                "Contagem de Glóbulos Vermelhos",
                "Amplitude de Distribuição dos Eritrócitos",
                "Contagem de Glóbulos Brancos",
                "Contagem Diferencial de Glóbulos Brancos - Neutrófilos Bastonetes",
                "Contagem Diferencial de Glóbulos Brancos - Neutrófilos Segmentados",
                "Contagem Diferencial de Glóbulos Brancos - Linfócitos",
                "Contagem Diferencial de Glóbulos Brancos - Monócitos",
                "Contagem Diferencial de Glóbulos Brancos - Eosinófilos",
                "Contagem Diferencial de Glóbulos Brancos - Basófilos",
                "Contagem de Plaquetas"
            };

            // Campos de entrada para cada parâmetro
            TextField[] hemogramInputs = new TextField[parametros.length];
            for (int i = 0; i < parametros.length; i++) {
                Label label = new Label(parametros[i] + ":");
                GridPane.setConstraints(label, 0, 4 + i);
                TextField input = new TextField();
                GridPane.setConstraints(input, 1, 4 + i);
                grid.getChildren().addAll(label, input);
                hemogramInputs[i] = input;
            }

            // Botão para processar dados
            Button processButton = new Button("Analisar Hemograma");
            GridPane.setConstraints(processButton, 1, 4 + parametros.length);
            GridPane.setColumnSpan(processButton, 2);

            // Área de resultado
            TextArea resultArea = new TextArea();
            resultArea.setEditable(false);
            resultArea.setPrefWidth(600);  // Largura preferida de 600 pixels
            resultArea.setPrefHeight(300); // Altura preferida de 300 pixels
            resultArea.setMaxWidth(800);   // Largura máxima de 800 pixels
            resultArea.setMaxHeight(400);  // Altura máxima de 400 pixels
            GridPane.setConstraints(resultArea, 0, 5 + parametros.length);
            GridPane.setColumnSpan(resultArea, 2);

            // Ação do botão para processar os dados e gerar resultados
            processButton.setOnAction(e -> {
                try {
                    String nome = nameInput.getText();
                    int idade = Integer.parseInt(ageInput.getText());
                    String categoria = categoryChoice.getValue();  // Obtemos a categoria selecionada do ComboBox

                    if (categoria == null) {
                        resultArea.setText("Erro: Categoria de referência não selecionada.");
                        return;
                    }

                    // Criação do paciente com categoria de referência e armazenamento no banco de dados
                    Paciente paciente = new Paciente(nome, idade, categoria);
                    pacienteRepository.create(paciente);

                    Hemograma hemograma = new Hemograma();
                    for (int i = 0; i < parametros.length; i++) {
                        String parametro = parametros[i];
                        double valor = Double.parseDouble(hemogramInputs[i].getText());
                        hemograma.adicionarValor(parametro, valor);
                    }

                    // Comparação com os valores de referência e armazenamento dos resultados
                    StringBuilder resultados = new StringBuilder();
                    Map<String, FaixaValores> valoresReferenciaUsuario = valoresReferencia.obterValores(categoria);
                    if (valoresReferenciaUsuario == null) {
                        resultArea.setText("Erro: Categoria de referência não encontrada.");
                        return;
                    }
                    for (String parametro : hemograma.valores.keySet()) {
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
                            resultados.append(parametro).append(" acima do valor de referência: ").append(valor).append(" ")
                                .append(faixaValores.getUnidade()).append(" (Valor de referência: ")
                                .append(faixaValores.getMin()).append(" - ").append(faixaValores.getMax()).append(" ")
                                .append(faixaValores.getUnidade()).append(")\n");
                        }
                    }
                    // Salvando os resultados no paciente
                    paciente.setResultados(resultados.toString());
                    pacienteRepository.update(paciente);

                    // Exibindo os resultados armazenados e o ID do paciente
                    Paciente pacienteFromDb = pacienteRepository.read(paciente.getId());
                    resultArea.setText("Resultados armazenados para o Paciente ID " + pacienteFromDb.getId() + ":\n" + pacienteFromDb.getResultados());

                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultArea.setText("Erro: " + ex.getMessage());
                }
            });

            // Adicionando todos os elementos ao GridPane
            grid.getChildren().addAll(nameLabel, nameInput, ageLabel, ageInput, categoryLabel, categoryChoice, hemogramLabel, processButton, resultArea);

            // Configuração da cena e exibição da janela
            Scene scene = new Scene(grid, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            // Trate o erro conforme necessário
        }
    }

    /**
     * Método principal para iniciar a aplicação.
     *
     * @param args Argumentos de linha de comando
     */
    public static void main(String[] args) {
        launch(args);
    }
}
