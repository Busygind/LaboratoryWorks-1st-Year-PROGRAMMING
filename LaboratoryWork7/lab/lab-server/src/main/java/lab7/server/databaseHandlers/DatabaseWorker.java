package lab7.server.databaseHandlers;

import lab7.common.util.entities.Dragon;
import lab7.server.ServerConfig;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseWorker {
    private final Connection connection;
    private final String username;

    public DatabaseWorker(Connection dbConnection, String username) {
        this.connection = dbConnection;
        this.username = username;
    }

    public boolean addDragon(Dragon dragon) {
        try {
            PreparedStatement statement = connection.prepareStatement(Statements.addDragon.getStatement());
            statement.setLong(1, generateId());
            statement.setString(2, dragon.getName());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, dragon.getAge());
            statement.setInt(5, dragon.getWingspan());
            statement.setInt(6, dragon.getCoordinates().getX());
            statement.setFloat(7, dragon.getCoordinates().getY());
            statement.setString(8, String.valueOf(dragon.getColor()));
            statement.setDouble(9, dragon.getCave().getDepth());
            statement.setInt(10, dragon.getCave().getNumberOfTreasures());
            statement.setString(11, String.valueOf(dragon.getCharacter()));
            statement.setString(12, username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeById(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(Statements.removeById.getStatement());
            statement.setString(1, username);
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            ServerConfig.logger.info("SQL problem with removing by id");
            return false;
        }
    }

    public boolean updateById(Dragon dragon, long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(Statements.updateById.getStatement());
            statement.setString(1, dragon.getName());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.setInt(3, dragon.getAge());
            statement.setInt(4, dragon.getWingspan());
            statement.setInt(5, dragon.getCoordinates().getX());
            statement.setFloat(6, dragon.getCoordinates().getY());
            statement.setString(7, String.valueOf(dragon.getColor()));
            statement.setDouble(8, dragon.getCave().getDepth());
            statement.setInt(9, dragon.getCave().getNumberOfTreasures());
            statement.setString(10, String.valueOf(dragon.getCharacter()));
            statement.setLong(11, id);
            statement.setString(12, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            ServerConfig.logger.info("SQL problem with removing by id");
            e.printStackTrace();
            return false;
        }
    }

    private Long generateId() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Statements.getNextId.getStatement());
            if (resultSet.next()) {
                return resultSet.getLong("nextval");
            }
            return null;
        } catch (SQLException e) {
            ServerConfig.logger.info("SQL problem with generating id");
            return null;
        }
    }
}
