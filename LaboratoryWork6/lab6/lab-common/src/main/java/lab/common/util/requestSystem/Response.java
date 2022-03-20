package lab.common.util.requestSystem;

import java.io.Serializable;

public class Response implements Serializable {

    private final String message;
    private final boolean isPositive;

    public Response(String message, boolean isPositive) {
        this.isPositive = isPositive;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean getPositive() {
        return isPositive;
    }
}
