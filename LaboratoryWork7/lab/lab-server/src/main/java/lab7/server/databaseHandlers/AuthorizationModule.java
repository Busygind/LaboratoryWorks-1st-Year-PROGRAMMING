package lab7.server.databaseHandlers;

import lab7.common.util.requestSystem.requests.Request;
import lab7.common.util.requestSystem.requests.SignInRequest;
import lab7.common.util.requestSystem.requests.SignUpRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationModule {

    private final Connection connection;
    private final Request request;

    private final boolean correctUser;

    public AuthorizationModule(SignInRequest signInRequest, Connection connection) throws SQLException {
        this.request = signInRequest;
        this.connection = connection;
        this.correctUser = checkUserInData();
    }

    public AuthorizationModule(SignUpRequest signUpRequest, Connection connection) throws SQLException {
        this.request = signUpRequest;
        this.connection = connection;
        this.correctUser = addUserToData();
    }

    public boolean checkUserInData() throws SQLException {
        SignInRequest signInRequest = (SignInRequest) request;
        PreparedStatement statement = connection.prepareStatement(Statements.checkUserInData.getStatement());
        statement.setString(1, signInRequest.getLogin());
        statement.setString(2, signInRequest.getPassword());
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
        //todo написать логику
    }

    public boolean addUserToData() throws SQLException {
        SignUpRequest signUpRequest = (SignUpRequest) request;
        PreparedStatement statement = connection.prepareStatement(Statements.addUserToData.getStatement());
        statement.setString(1, signUpRequest.getLogin());
        statement.setString(2, signUpRequest.getPassword());
        statement.executeUpdate();
        return true;
    }

    public boolean isCorrectUser() {
        return correctUser;
    }
}
