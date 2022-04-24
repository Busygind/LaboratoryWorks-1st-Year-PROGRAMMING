package lab7.client.usersController;

import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.requests.Request;
import lab7.common.util.requestSystem.requests.SignInRequest;
import lab7.common.util.requestSystem.requests.SignUpRequest;

import java.util.Scanner;

public class AuthorizationModule {
    private static final Scanner SCANNER = new Scanner(System.in);

    private boolean authorizationDone = false;

    public Request greet() {
        TextFormatter.printMessage("Do you want to create a new user? [y/n]");
        String input = SCANNER.nextLine();
        if ("y".equalsIgnoreCase(input)) {
            return createNewUser();
        } else if ("n".equalsIgnoreCase(input)) {
            return authorize();
        }
        TextFormatter.printErrorMessage("Incorrect answer");
        return greet();
    }

    private SignUpRequest createNewUser() {
        TextFormatter.printMessage("Enter your name: ");
        String login = SCANNER.nextLine();
        TextFormatter.printMessage("Enter your password: ");
        String password = SCANNER.nextLine();
        TextFormatter.printMessage("Repeat your password: ");
        if (SCANNER.nextLine().equals(password)) {
            TextFormatter.printMessage("Trying to create new user... ");
            return new SignUpRequest(login, password);
        }
        TextFormatter.printErrorMessage("Passwords didn't match, try again");
        return createNewUser();
    }

    private SignInRequest authorize() {
        TextFormatter.printMessage("Enter your name: ");
        String login = SCANNER.nextLine();
        TextFormatter.printMessage("Enter your password: ");
        String password = SCANNER.nextLine();
        TextFormatter.printMessage("Trying to log in... ");
        return new SignInRequest(login, password);
    }

    public boolean isAuthorizationDone() {
        return authorizationDone;
    }

    public void setAuthorizationDone(boolean authorizationDone) {
        this.authorizationDone = authorizationDone;
    }
}
