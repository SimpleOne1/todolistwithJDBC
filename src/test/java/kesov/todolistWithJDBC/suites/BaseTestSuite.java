package kesov.todolistWithJDBC.suites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import kesov.todolistWithJDBC.repository.TodoRepo;
import kesov.todolistWithJDBC.task.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = TestSuiteConfiguration.class)
@AutoConfigureMockMvc
@ContextConfiguration(initializers = Postgres.Initializer.class)
@ActiveProfiles("postgre")
@Transactional
public abstract class BaseTestSuite {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new ParameterNamesModule());

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected TodoRepo todoRepo;

    protected Todo createTodo(){
        Todo todo = new Todo();
        todo.setId(null);
        todo.setText("Description-text");
        return todo;
    }

    protected Todo saveTodo(){
        Todo todo = new Todo();
        todo.setId(null);
        todo.setText("Description-text");
        todo.setId(todoRepo.save(todo));
        return todo;
    }


    public MockMvc getMockMvc() {
        return mockMvc;
    }
    public static String asJson(Object object) throws Exception {
        return OBJECT_MAPPER.writer().withDefaultPrettyPrinter().writeValueAsString(object);
    }
    public static <T> T fromResponse(MvcResult mvcResult, Class<T> type) throws Exception {
        return OBJECT_MAPPER.readerFor(type).readValue(mvcResult.getResponse().getContentAsString());
    }
}
