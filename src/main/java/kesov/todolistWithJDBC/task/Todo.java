package kesov.todolistWithJDBC.task;

import javax.validation.constraints.NotNull;


public class Todo {

    private Integer id;

    @NotNull
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
