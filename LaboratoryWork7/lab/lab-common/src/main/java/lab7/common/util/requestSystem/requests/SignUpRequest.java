package lab7.common.util.requestSystem.requests;

import javafx.util.Pair;

import java.io.Serializable;

public class SignUpRequest implements Request, Serializable {

    private static final RequestType type = RequestType.SIGN_UP;
    private final Pair<String, String> loginData;

    public SignUpRequest(Pair<String, String> loginData) {
        this.loginData = loginData;
    }

    public Pair<String, String> getPair() {
        return loginData;
    }

    @Override
    public RequestType getType() {
        return type;
    }
}
