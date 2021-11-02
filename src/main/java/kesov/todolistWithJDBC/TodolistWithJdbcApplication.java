package kesov.todolistWithJDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"kesov.todolistWithJDBC.configuration", "kesov.todolistWithJDBC.web"})
public class TodolistWithJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistWithJdbcApplication.class, args);
	}

}
