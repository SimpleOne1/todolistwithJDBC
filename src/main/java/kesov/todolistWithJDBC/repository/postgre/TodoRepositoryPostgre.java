package kesov.todolistWithJDBC.repository.postgre;

import kesov.todolistWithJDBC.repository.TodoRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TodoRepositoryPostgre extends TodoRepo {
    public TodoRepositoryPostgre(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
}
