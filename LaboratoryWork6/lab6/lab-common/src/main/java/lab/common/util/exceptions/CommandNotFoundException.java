package lab.common.util.exceptions;

import java.io.IOException;

public class CommandNotFoundException extends IOException {

    public CommandNotFoundException(String message) {
        super(message);
    }
}
