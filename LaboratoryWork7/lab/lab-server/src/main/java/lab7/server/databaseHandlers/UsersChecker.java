package lab7.server.databaseHandlers;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersChecker {

    private final Connection dbConnection;
    private final Pair<String, String> userData;

    public UsersChecker(Connection dbConnection, Pair<String, String> userData) {
        this.dbConnection = dbConnection;
        this.userData = userData;
    }

    public boolean checkUserInData() throws SQLException {
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        PreparedStatement statement = dbConnection.prepareStatement(Statements.checkUserInData.getStatement());
        statement.setString(1, userData.getKey());
        statement.setString(2, passwordEncryptor.encrypt(userData.getValue()));
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}
