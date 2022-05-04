package lab7.server.databaseHandlers;

import lab7.common.util.requestSystem.requests.Request;
import lab7.common.util.requestSystem.requests.SignInRequest;
import lab7.common.util.requestSystem.requests.SignUpRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorizationModule {

    private final Connection connection;
    private final Request request;
    private final PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

    private final boolean correctUser;

    public AuthorizationModule(SignInRequest signInRequest, Connection connection) throws SQLException {
        this.request = signInRequest;
        this.connection = connection;
        UsersChecker checker = new UsersChecker(connection, signInRequest.getPair());
        this.correctUser = checker.checkUserInData();
    }

    public AuthorizationModule(SignUpRequest signUpRequest, Connection connection) throws SQLException {
        this.request = signUpRequest;
        this.connection = connection;
        this.correctUser = addUserToData();
    }

    private boolean addUserToData() throws SQLException {
        SignUpRequest signUpRequest = (SignUpRequest) request;
        PreparedStatement statement = connection.prepareStatement(Statements.addUserToData.getStatement());
        statement.setString(1, signUpRequest.getPair().getKey());
        statement.setString(2, passwordEncryptor.encrypt(signUpRequest.getPair().getValue()));
        statement.executeUpdate();
        return true;
    }

    public boolean isCorrectUser() {
        return correctUser;
    }
}
