package zet.kedzieri.noted;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<>(String.format("{\"message\": \"%s\"}", message), HttpStatus.BAD_REQUEST);
    }
}