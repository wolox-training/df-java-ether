package wolox.training.exceptions;

public class BookIdMismatchException extends RuntimeException{
    public BookIdMismatchException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public BookIdMismatchException() {

    }
}
