package kesov.todolistWithJDBC.repository.mySql;
import kesov.todolistWithJDBC.repository.TodoRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepositoryMySql extends TodoRepo {
    public TodoRepositoryMySql(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
}

