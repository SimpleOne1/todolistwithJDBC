package kesov.todolistWithJDBC.web;

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
        return service.getAll();
    }


    @PostMapping
    public Todo saveAndEdit(@RequestBody Todo todo) {
        return service.saveTask(todo);
    }

    @DeleteMapping
    public void delete(@RequestParam(value="id") Integer id){
        if(id==null){
            service.deleteAll();
        }
        else {
            service.delete(id);
        }
    }

    @DeleteMapping("{todoId}")
    public void delete(@PathVariable(value="todoId")int id){
        service.delete(id);
    }


}
