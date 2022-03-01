package exceptions;

public class NullObjectException extends IllegalArgumentException {
    public NullObjectException(String message) {
        super(message);
    }
}
