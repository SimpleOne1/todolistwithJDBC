package kesov.todolistWithJDBC.repository;

import kesov.todolistWithJDBC.task.Todo;

import java.util.Collection;

public interface TodoRepository {
    public int save();
    public Todo get(int id);
    public Collection<Todo> getAll();
    public boolean delete(int id);
    public boolean delete();
}
