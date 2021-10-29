package kesov.todolistWithJDBC.service.exceptions;

public class TaskNotFoundException extends BaseApplicationException{
    public TaskNotFoundException(Integer id) {
        super("Task by id "+id+" was not found");
    }
}
