package kesov.todolistWithJDBC.configuration;

import kesov.todolistWithJDBC.repository.mySql.TodoRepositoryMySql;
import kesov.todolistWithJDBC.service.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class businessConfiguration {

    @Bean
    public TodoService todoService(TodoRepositoryMySql repositoryMySql){
        return new TodoService(repositoryMySql);
    }
}
