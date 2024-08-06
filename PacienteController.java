import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador responsável pela interação entre a interface gráfica de usuário e o modelo de dados do paciente.
 */
public class PacienteController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField idadeField;

    @FXML
    private TextField sexoField;  // Agora sexo é um texto, portanto TextField é apropriado.

    @FXML
    private TableView<Paciente> pacienteTable;

    @FXML
    private TableColumn<Paciente, Integer> idColumn;

    @FXML
    private TableColumn<Paciente, String> nomeColumn;

    @FXML
    private TableColumn<Paciente, Integer> idadeColumn;

    @FXML
    private TableColumn<Paciente, String> sexoColumn;  // Atualizar para String

    @FXML
    private TableColumn<Paciente, String> resultadosColumn;

    private PacienteRepository pacienteRepository;

    /**
     * Método chamado automaticamente pelo sistema quando o controlador é carregado.
     * Inicializa as colunas da tabela e carrega os pacientes do banco de dados
     */
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        idadeColumn.setCellValueFactory(new PropertyValueFactory<>("idade"));
        sexoColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));  // Atualizar para String
        resultadosColumn.setCellValueFactory(new PropertyValueFactory<>("resultados"));

        try {
            String databasePath = System.getProperty("user.dir") + "/pacientes.db";
            Database db = new Database(databasePath);
            pacienteRepository = new PacienteRepository(db.getConnection());
            carregarPacientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona um novo paciente à tabela e ao banco de dados com base nos dados fornecidos pelos campos de texto.
     */
    @FXML
    public void adicionarPaciente() {
        try {
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            String sexo = sexoField.getText();  // Atualizar para String

            Paciente paciente = new Paciente(nome, idade, sexo);  // Atualizar para String
            pacienteRepository.create(paciente);
            pacienteTable.getItems().add(paciente);

            nomeField.clear();
            idadeField.clear();
            sexoField.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega todos os pacientes do banco de dados e os exibe na tabela.
     */
    private void carregarPacientes() {
        try {
            pacienteTable.getItems().clear();
            pacienteTable.getItems().addAll(pacienteRepository.obterTodosPacientes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
