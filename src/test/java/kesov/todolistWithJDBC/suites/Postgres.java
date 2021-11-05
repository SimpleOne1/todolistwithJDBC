package kesov.todolistWithJDBC.suites;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.HashMap;
import java.util.Map;

public class Postgres {

    public static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:13.4");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            Startables.deepStart(CONTAINER).join();
            Map<String,Object> map = new HashMap<>();
            map.put("spring.datasource.url",CONTAINER.getJdbcUrl());
            map.put("spring.datasource.username",CONTAINER.getUsername());
            map.put("spring.datasource.password", CONTAINER.getPassword());
            context.getEnvironment()
                    .getPropertySources()
                    .addFirst(new MapPropertySource(
                            "testcontainers",map)
                    );
        }
    }
}