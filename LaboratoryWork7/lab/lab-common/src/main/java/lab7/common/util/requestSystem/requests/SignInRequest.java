package lab7.common.util.requestSystem.requests;

import java.io.Serializable;

public class SignInRequest implements Request, Serializable {

    private static final RequestType type = RequestType.SIGN_IN;
    private final String login;
    private final String password;

    public SignInRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public RequestType getType() {
        return type;
    }
}
