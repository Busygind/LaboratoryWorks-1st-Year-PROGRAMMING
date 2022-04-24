package lab7.common.util.requestSystem.responses;

import java.io.Serializable;

public class SignUpResponse implements Response, Serializable {

    private static final ResponseType type = ResponseType.SIGN_UP;
    private final String username;
    private final boolean successfulAuthorization;

    public SignUpResponse(boolean successfulAuthorization, String username) {
        this.successfulAuthorization = successfulAuthorization;
        this.username = username;
    }

    public boolean isSuccessfulAuthorization() {
        return successfulAuthorization;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public ResponseType getType() {
        return type;
    }
}
