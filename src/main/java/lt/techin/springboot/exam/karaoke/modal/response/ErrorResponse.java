package lt.techin.springboot.exam.karaoke.modal.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String message;
    private String cause;
}
