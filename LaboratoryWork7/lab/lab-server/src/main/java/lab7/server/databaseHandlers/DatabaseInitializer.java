package lab7.server.databaseHandlers;

import lab7.common.util.entities.Coordinates;
import lab7.common.util.entities.Dragon;
import lab7.common.util.entities.DragonCave;
import lab7.common.util.enums.Color;
import lab7.common.util.enums.DragonCharacter;
import lab7.common.util.handlers.DragonValidator;
import lab7.server.ServerConfig;

import java.sql.*;

public class DatabaseInitializer {
    private final Connection connection;
    private final Statement sqlStatement;

    public DatabaseInitializer(Connection connection) throws SQLException {
        this.connection = connection;
        this.sqlStatement = connection.createStatement();
    }

    public void initialize() throws SQLException {
        sqlStatement.executeUpdate("CREATE SEQUENCE IF NOT EXISTS ids START 3;");

        createDragonsTable();
        createUsersTable();
    }

    private void createDragonsTable() throws SQLException {
        sqlStatement.executeUpdate("CREATE TABLE IF NOT EXISTS s335103Dragons (" +
                "id bigint PRIMARY KEY DEFAULT (nextval('ids')), " +
                "name varchar(70) NOT NULL CHECK (name<>'')," +
                "creationDate date DEFAULT (current_date)," +
                "age int NOT NULL CHECK (age > 0)," +
                "wingspan int NOT NULL CHECK (wingspan > 0)," +
                "xCoord int NOT NULL CHECK (xCoord < 603), " +
                "yCoord real NOT NULL," +
                "color varchar(6) " +
                "CHECK (color='GREEN' OR " +
                "color='BLUE' OR " +
                "color='YELLOW' OR " +
                "color='ORANGE' OR " +
                "color='WHITE')," +
                "caveDepth real," +
                "caveNumOfTreasures int NOT NULL CHECK (caveNumOfTreasures > 0)," +
                "dragonCharacter varchar(12) NOT NULL " +
                "CHECK (dragonCharacter='WISE' OR " +
                "dragonCharacter='GOOD' OR " +
                "dragonCharacter='CHAOTIC' OR " +
                "dragonCharacter='CHAOTIC_EVIL' OR " +
                "dragonCharacter='FICKLE')," +
                "author varchar(75) NOT NULL);");
    }

    private void createUsersTable() throws SQLException {
        sqlStatement.executeUpdate("CREATE TABLE IF NOT EXISTS s335103Users (" +
                "name varchar(70) PRIMARY KEY NOT NULL," +
                "password varchar(255) NOT NULL);");
    }

    public void fillCollection(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(Statements.getAll.getStatement());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Dragon dragon = new Dragon();
            dragon.setId(resultSet.getLong(1));
            dragon.setName(resultSet.getString(2));
            dragon.setCreationDate(resultSet.getDate(3));
            dragon.setAge(resultSet.getInt(4));
            dragon.setWingspan(resultSet.getInt(5));
            dragon.setCoordinates(new Coordinates(resultSet.getInt(6), resultSet.getFloat(7)));
            dragon.setColor(resultSet.getString(8) != null ?
                    Color.valueOf(resultSet.getString(8)) : null);
            dragon.setCave(new DragonCave(resultSet.getFloat(9), resultSet.getInt(10)));
            dragon.setCharacter(resultSet.getString(11) != null ?
                    DragonCharacter.valueOf(resultSet.getString(11)) : null);
            dragon.setAuthorName(resultSet.getString(12));
            if (DragonValidator.validateDragon(dragon)) {
                ServerConfig.manager.addDragon(dragon);
            } else {
                ServerConfig.logger.fatal("Dragon with id = " + resultSet.getLong(1) + " is incorrect");
                System.exit(1);
            }
        }
        ServerConfig.logger.info("Collection successfully filled");
    }

    public Connection getConnection() {
        return this.connection;
    }
}
