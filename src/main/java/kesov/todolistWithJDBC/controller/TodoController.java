package kesov.todolistWithJDBC.controller;

import kesov.todolistWithJDBC.service.TodoService;
import kesov.todolistWithJDBC.task.Todo;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping()
    @ResponseBody
    public List<Todo> getAll(){
        System.out.println(service.getAll());
        return service.getAll();
    }

    @GetMapping("{taskId}")
    public Todo get(@PathVariable(value="taskId")int id){
        return service.get(id);
    }

    @PostMapping
    public Todo save(@RequestBody Todo todo){
        return service.saveTask(todo);
    }
    @PostMapping("{taskId}")
    public boolean update(@PathVariable(value="taskId")int id,@RequestBody Todo todo){
        return service.edit(id, todo);
    }
    @DeleteMapping("{taskId}")
    public boolean delete(@PathVariable(value="taskId")int id){
        return service.delete(id);
    }

}
