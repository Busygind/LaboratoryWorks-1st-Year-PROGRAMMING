package exceptions;

import java.io.IOException;

public class HasNotHomeException extends IOException {

    public HasNotHomeException(String message) {
        super(message);
    }
}
