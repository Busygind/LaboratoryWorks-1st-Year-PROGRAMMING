package exceptions;

import java.io.IOException;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException(String message) {
        super(message);
    }
}
