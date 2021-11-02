package kesov.todolistWithJDBC.repository.postgre;

import kesov.todolistWithJDBC.repository.TodoRepo;
import kesov.todolistWithJDBC.service.exceptions.TaskNotFoundException;
import kesov.todolistWithJDBC.task.Todo;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static org.springframework.jdbc.core.BeanPropertyRowMapper.*;

@Repository
public class TodoRepository extends TodoRepo {
    public TodoRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
}
