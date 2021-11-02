package kesov.todolistWithJDBC.web;

import kesov.todolistWithJDBC.service.exceptions.ResponseException;
import kesov.todolistWithJDBC.service.exceptions.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseException handle(TaskNotFoundException e, WebRequest request){
        logger.info("Unable to process request {}:{}",request,e.getMessage(),e);
        return new ResponseException(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
