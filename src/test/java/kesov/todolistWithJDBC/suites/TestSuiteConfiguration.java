package kesov.todolistWithJDBC.suites;

import kesov.todolistWithJDBC.TodolistWithJdbcApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TodolistWithJdbcApplication.class)
public class TestSuiteConfiguration {
}
