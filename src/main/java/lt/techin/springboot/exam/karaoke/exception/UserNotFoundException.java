package lt.techin.springboot.exam.karaoke.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String uuid) {
        super("User not found by uuid - " + uuid);
    }
}
