package lab7.common.util.requestSystem.responses;

import javafx.util.Pair;

import java.io.Serializable;

public class SignUpResponse implements Response, Serializable {

    private static final ResponseType type = ResponseType.SIGN_UP;
    private final Pair<String, String> loginData;
    private final boolean successfulAuthorization;

    public SignUpResponse(boolean successfulAuthorization, Pair<String, String> loginData) {
        this.successfulAuthorization = successfulAuthorization;
        this.loginData = loginData;
    }

    public boolean isSuccessfulAuthorization() {
        return successfulAuthorization;
    }

    public Pair<String, String> getPair() {
        return loginData;
    }

    @Override
    public ResponseType getType() {
        return type;
    }
}
