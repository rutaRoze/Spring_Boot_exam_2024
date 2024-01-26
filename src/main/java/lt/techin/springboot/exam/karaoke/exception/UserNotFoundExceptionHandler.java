package lt.techin.springboot.exam.karaoke.exception;

import jakarta.validation.ConstraintViolationException;
import lt.techin.springboot.exam.karaoke.modal.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<ErrorResponse> handle(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .cause(exception.getConstraintViolations().toString())
                        .build()
                );
    }

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<ErrorResponse> handle(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({NoEntriesFoundException.class})
    protected ResponseEntity<ErrorResponse> handle(NoEntriesFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .build()
                );
    }

}
