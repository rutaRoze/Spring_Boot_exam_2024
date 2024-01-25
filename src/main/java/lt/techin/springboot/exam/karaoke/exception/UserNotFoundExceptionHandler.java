package lt.techin.springboot.exam.karaoke.exception;

import lt.techin.springboot.exam.karaoke.modal.response.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exception) {

        UserErrorResponse errorResponse = new UserErrorResponse();

        errorResponse.setMessage(exception.getMessage());
        errorResponse.setCause(String.valueOf(exception.getCause()));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler({UserNotFoundException.class})
//    public ResponseEntity<UserErrorResponse> handleException(Exception exception) {
//
//        UserErrorResponse errorResponse = new UserErrorResponse();
//
//        errorResponse.setMessage(exception.getMessage());
//        errorResponse.setCause(String.valueOf(exception.getCause()));
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
}
