package exceptions;

public class ToManyCitizensException extends RuntimeException {

    public ToManyCitizensException(String message) {
        super(message);
    }
}
