package chechi.nino.bootcamp.exception;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(String email, String message) {
        super(message);
    }
}
