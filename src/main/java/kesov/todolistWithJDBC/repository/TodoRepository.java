package kesov.todolistWithJDBC.repository;

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
public class TodoRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private static final BeanPropertyRowMapper<Todo> ROW_MAPPER = newInstance(Todo.class);

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tasks")
                .usingGeneratedKeyColumns("id");
    }

    public int save(Todo todo){
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(todo);
        Number newKey=simpleJdbcInsert.executeAndReturnKey(parameterSource);
        todo.setId(newKey.intValue());
        return todo.getId();
    }
    public Todo edit(Integer id, Todo todo){
        int updated = jdbcTemplate.update("UPDATE tasks SET description=? where id=?", todo.getDescription(), id);
        if (updated == 0) {
            throw new TaskNotFoundException(id);
        }
        return todo;
    }
    public Todo get(int id){
        List<Todo> list = jdbcTemplate.query("SELECT * FROM tasks WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(list);
    }
    public Collection<Todo> getAll(){
        return jdbcTemplate.query("SELECT * FROM tasks", ROW_MAPPER);
    }

    public boolean delete(int id){
        int updated = jdbcTemplate.update("DELETE FROM tasks WHERE id=?",id);
        return updated>0;
    }

}
