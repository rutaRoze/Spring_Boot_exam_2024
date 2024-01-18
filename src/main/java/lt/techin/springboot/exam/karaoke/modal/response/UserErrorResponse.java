package lt.techin.springboot.exam.karaoke.modal.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserErrorResponse {

    private String message;
    private String cause;
}
