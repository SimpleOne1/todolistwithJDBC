package kesov.todolistWithJDBC.configuration;

import kesov.todolistWithJDBC.repository.TodoRepo;
import kesov.todolistWithJDBC.repository.mySql.TodoRepositoryMySql;
import kesov.todolistWithJDBC.service.TodoService;
import kesov.todolistWithJDBC.web.TodoController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class businessConfiguration {
    @Bean
    public TodoService todoService(TodoRepo repository){
        return new TodoService(repository);
    }
    @Bean
    public TodoController todoController(TodoService todoService){
        return new TodoController(todoService);
    }
}
