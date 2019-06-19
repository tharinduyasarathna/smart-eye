package lk.tharindu.backend.exception;

public class BodyContentNotValidException extends RuntimeException {
    public BodyContentNotValidException(String message) {
        super(message);
    }
}
