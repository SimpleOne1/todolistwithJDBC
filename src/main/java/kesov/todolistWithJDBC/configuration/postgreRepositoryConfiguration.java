package kesov.todolistWithJDBC.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("postgre")
@ComponentScan("kesov.todolistWithJDBC.repository.postgre")
public class postgreRepositoryConfiguration {
}
