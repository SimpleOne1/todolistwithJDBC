package kesov.todolistWithJDBC.service;

import kesov.todolistWithJDBC.repository.TodoRepository;
import kesov.todolistWithJDBC.service.exceptions.TaskNotFoundException;
import kesov.todolistWithJDBC.task.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo saveTask(Todo todo){
        int id = repository.save(todo);
        todo.setId(id);
        return todo;
    }

    public List<Todo> getAll(){
        return new ArrayList<>(repository.getAll());
    }

    public Todo get(int id){
       Todo todo = repository.get(id);
       if(todo == null){
           throw new TaskNotFoundException(id);
       }
       return todo;
    }

    public boolean delete(int id){
        Todo todo = repository.get(id);
        if(todo == null){
            return false;
        }
        else{
            repository.delete(id);
            return true;
        }
    }

    public boolean edit(int id, Todo todo){
        Todo todo1 = repository.get(id);
        if(todo1 == null){
            return false;
        }
        else{
            repository.edit(id, todo);
            return true;
        }
    }

}
