package lt.techin.springboot.exam.karaoke.exception;

public class NoEntriesFoundException extends RuntimeException {

    public NoEntriesFoundException(String message) {
        super(message);
    }
}
