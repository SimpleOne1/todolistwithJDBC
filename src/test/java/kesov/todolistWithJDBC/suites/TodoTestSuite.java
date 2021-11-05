package kesov.todolistWithJDBC.suites;

import kesov.todolistWithJDBC.task.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

public class TodoTestSuite extends BaseTestSuite{

    @Test
    public void CreateAndGetTodoTest() throws Exception {
        Todo todo = createTodo();
        String json=asJson(todo);
        MockMvc mockMvc = getMockMvc();
        MvcResult result = getMockMvc().perform(post("/todo").content(json).contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Todo response = fromResponse(result,Todo.class);
        assertThat(response.getId()).isNotNull();
        todo.setId(response.getId());
        assertThat(response).usingRecursiveComparison().isEqualTo(todo);

        MvcResult resultAfterSave = getMockMvc().perform(get("/todo").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Todo[] todosAfterSave = fromResponse(resultAfterSave, Todo[].class);
        Todo onlyTask = todosAfterSave[0];
        assertThat(onlyTask).usingRecursiveComparison().isEqualTo(todo);
    }

    @Test
    public void editTest() throws Exception{
        Todo todo = saveTodo();
        Todo editedTodo = new Todo();
        editedTodo.setId(todo.getId());
        String diffText = "diffText";
        editedTodo.setText(diffText);
        String json=asJson(editedTodo);
        MvcResult result = getMockMvc().perform(post("/todo").content(json).contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Todo response = fromResponse(result,Todo.class);
        assertThat(response.getId()).isEqualTo(todo.getId());
        MvcResult resultAfterEdit = getMockMvc().perform(get("/todo").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Todo[] todosAfterSave = fromResponse(resultAfterEdit, Todo[].class);
        Todo onlyTask = todosAfterSave[0];
        assertThat(onlyTask).usingRecursiveComparison().isEqualTo(editedTodo);
    }

    @Test
    public void deleteTest() throws Exception{
        Todo todo = saveTodo();
        getMockMvc().perform(delete("/todo").param("id",todo.getId().toString()))
                .andExpect(status().isOk())
                .andReturn();
        MvcResult resultAfterDelete = getMockMvc().perform(get("/todo").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Todo[] todosAfterDelete = fromResponse(resultAfterDelete, Todo[].class);
        assertThat(todosAfterDelete.length).isEqualTo(0);
    }

    @Test
    public void getAndDeleteAllTest() throws Exception{
        Todo todo1=saveTodo();
        Todo todo2=saveTodo();
        MvcResult resultAfterSave = getMockMvc().perform(get("/todo").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Todo[] todosAfterSave = fromResponse(resultAfterSave, Todo[].class);
        assertThat(todosAfterSave.length).isEqualTo(2);
        getMockMvc().perform(delete("/todo").param("id",null))
                .andExpect(status().isOk())
                .andReturn();
        MvcResult resultAfterDelete = getMockMvc().perform(get("/todo").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Todo[] todosAfterDelete = fromResponse(resultAfterDelete, Todo[].class);
        assertThat(todosAfterDelete.length).isEqualTo(0);
    }
}
