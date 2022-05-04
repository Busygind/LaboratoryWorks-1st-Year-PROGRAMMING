package lab7.common.util.requestSystem.responses;

import javafx.util.Pair;
import java.io.Serializable;

public class SignInResponse implements Response, Serializable {

    private static final ResponseType type = ResponseType.SIGN_IN;
    private final String username;
    private final String password;
    private final Pair<String, String> loginData;
    private final boolean successfulAuthorization;

    public SignInResponse(boolean successfulAuthorization, Pair<String, String> loginData) {
        this.successfulAuthorization = successfulAuthorization;
        this.username = loginData.getKey();
        this.password = loginData.getValue();
        this.loginData = loginData;
    }

    public boolean isSuccessfulAuthorization() {
        return successfulAuthorization;
    }

    public String getUsername() {
        return username;
    }

    public Pair<String, String> getPair() {
        return loginData;
    }

    @Override
    public ResponseType getType() {
        return type;
    }
}
