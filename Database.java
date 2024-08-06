import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados.
 * Utiliza a biblioteca ORMLite para conectar a um banco de dados SQLite.
 */
public class Database {
    // Caminho para o arquivo do banco de dados
    private String databasePath = null;
    
    // Objeto que representa a conexão com o banco de dados
    private JdbcConnectionSource connection = null;

    /**
     * Construtor da classe Database.
     * @param databasePath O caminho do arquivo do banco de dados SQLite.
     */
    public Database(String databasePath) {
        this.databasePath = databasePath;
    }

    /**
     * Obtém a conexão com o banco de dados. Se a conexão ainda não tiver sido estabelecida,
     * ela será criada. Caso contrário, a conexão existente será retornada.
     * @return Um objeto JdbcConnectionSource que representa a conexão com o banco de dados.
     * @throws SQLException Se o caminho do banco de dados for nulo ou se houver erro ao estabelecer a conexão.
     */
    public JdbcConnectionSource getConnection() throws SQLException {
        if (databasePath == null) {
            throw new SQLException("Caminho do banco de dados é nulo");
        }
        if (connection == null) {
            try {
                connection = new JdbcConnectionSource("jdbc:sqlite:" + databasePath);
                System.out.println("Banco de dados aberto com sucesso");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }

    /**
     * Fecha a conexão com o banco de dados, se estiver aberta.
     * Após fechar, o objeto de conexão é definido como nulo
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                this.connection = null;
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
