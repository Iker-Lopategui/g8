import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositório para gerenciar operações CRUD (Create, Read, Update, Delete) de pacientes no banco de dados.
 * Utiliza a biblioteca ORMLite para interagir com o banco de dados.
 */
public class PacienteRepository {
    // Objeto Dao para operações com a tabela de pacientes
    private Dao<Paciente, Integer> pacienteDao;
    
    // Cache para armazenar pacientes e evitar consultas repetidas ao banco de dados
    private Map<Integer, Paciente> cache;
    
    // Fonte de conexão com o banco de dados
    private ConnectionSource connectionSource;
    
    // Registro de operações realizadas no repositório
    private StringBuilder log;

    /**
     * Construtor da classe PacienteRepository.
     * Inicializa o DAO, cria a tabela se não existir e configura o cache e o log.
     * @param connectionSource Fonte de conexão com o banco de dados.
     * @throws Exception Se ocorrer um erro ao inicializar o DAO ou criar a tabela.
     */
    public PacienteRepository(ConnectionSource connectionSource) throws Exception {
        this.connectionSource = connectionSource;
        pacienteDao = DaoManager.createDao(connectionSource, Paciente.class);
        TableUtils.createTableIfNotExists(connectionSource, Paciente.class);
        cache = new HashMap<>();
        log = new StringBuilder();
    }

    /**
     * Adiciona um novo paciente ao banco de dados e ao cache.
     * @param paciente O paciente a ser adicionado.
     * @throws Exception Se ocorrer um erro ao adicionar o paciente.
     */
    public void create(Paciente paciente) throws Exception {
        pacienteDao.create(paciente);
        cache.put(paciente.getId(), paciente);
        log.append("Created: ").append(paciente).append("\n");
    }

    /**
     * Obtém um paciente do banco de dados usando o ID. Utiliza o cache para melhorar a performance.
     * @param id O ID do paciente a ser obtido.
     * @return O paciente correspondente ao ID, ou null se não for encontrado.
     * @throws Exception Se ocorrer um erro ao consultar o banco de dados.
     */
    public Paciente read(int id) throws Exception {
        if (cache.containsKey(id)) {
            return cache.get(id);
        } else {
            Paciente paciente = pacienteDao.queryForId(id);
            if (paciente != null) {
                cache.put(id, paciente);
            }
            return paciente;
        }
    }

    /**
     * Atualiza as informações de um paciente no banco de dados e no cache.
     * @param paciente O paciente com informações atualizadas.
     * @throws Exception Se ocorrer um erro ao atualizar o paciente.
     */
    public void update(Paciente paciente) throws Exception {
        pacienteDao.update(paciente);
        cache.put(paciente.getId(), paciente);
        log.append("Updated: ").append(paciente).append("\n");
    }

    /**
     * Remove um paciente do banco de dados e do cache usando o ID.
     * @param id O ID do paciente a ser removido.
     * @throws Exception Se ocorrer um erro ao remover o paciente.
     */
    public void delete(int id) throws Exception {
        Paciente paciente = pacienteDao.queryForId(id);
        if (paciente != null) {
            pacienteDao.delete(paciente);
            cache.remove(id);
            log.append("Deleted: ").append(paciente).append("\n");
        }
    }

    /**
     * Retorna o log das operações realizadas no repositório.
     * @return Uma string contendo o log das operações.
     */
    public String getLog() {
        return log.toString();
    }

    /**
     * Fecha a conexão com o banco de dados.
     * @throws Exception Se ocorrer um erro ao fechar a conexão.
     */
    public void close() throws Exception {
        connectionSource.close();
    }

    /**
     * Obtém todos os pacientes armazenados no banco de dados.
     * @return Uma lista de pacientes.
     * @throws Exception Se ocorrer um erro ao consultar o banco de dados.
     */
    public List<Paciente> obterTodosPacientes() throws Exception {
        return pacienteDao.queryForAll();
    }
}
