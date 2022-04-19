package wolox.training.exceptions;

public class UserIdMismatchException extends RuntimeException {

    public UserIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdMismatchException() {

    }
}