package lab.common.util.exceptions;

import java.io.IOException;

public class DisconnectInitException extends IOException {
    public DisconnectInitException(String message) {
        super(message);
    }
}
