package kesov.todolistWithJDBC.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mySql")
@ComponentScan("kesov.todolistWithJDBC.repository.mySql")
public class mySqlRepositoryConfiguration {
}
