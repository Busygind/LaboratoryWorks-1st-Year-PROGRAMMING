package lab7.client.usersController;

import javafx.util.Pair;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.responses.Response;
import lab7.common.util.requestSystem.responses.SignInResponse;
import lab7.common.util.requestSystem.responses.SignUpResponse;

public class UserAcceptor {

    private final AuthorizationModule authorizationModule;
    private final Response response;

    public UserAcceptor(AuthorizationModule authorizationModule, Response response) {
        this.authorizationModule = authorizationModule;
        this.response = response;
    }

    public Pair<String, String> acceptAuthorization() {
        authorizationModule.setAuthorizationDone(((SignInResponse) response).isSuccessfulAuthorization());
        if (!authorizationModule.isAuthorizationDone()) {
            TextFormatter.printErrorMessage("User not found");
        } else {
            TextFormatter.printMessage("Authorization complete");
            return ((SignInResponse) response).getPair();
        }
        return null;
    }

    public Pair<String, String> acceptRegistration() {
        authorizationModule.setAuthorizationDone(((SignUpResponse) response).isSuccessfulAuthorization());
        TextFormatter.printMessage("User successful added. Authorization complete");
        return ((SignUpResponse) response).getPair();
    }
}
