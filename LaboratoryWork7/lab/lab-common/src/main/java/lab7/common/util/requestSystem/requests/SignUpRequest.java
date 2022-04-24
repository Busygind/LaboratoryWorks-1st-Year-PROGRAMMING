package lab7.common.util.requestSystem.requests;

import java.io.Serializable;

public class SignUpRequest implements Request, Serializable {

    private static final RequestType type = RequestType.SIGN_UP;
    private final String login;
    private final String password;

    public SignUpRequest(String login, String password) {
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
