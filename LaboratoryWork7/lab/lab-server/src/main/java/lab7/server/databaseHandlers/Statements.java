package lab7.server.databaseHandlers;

public enum Statements {
    addDragon("INSERT INTO s335103Dragons " +
            "(id, name, creationDate, age, wingspan, xCoord, yCoord, color, caveDepth," +
            "caveNumOfTreasures, dragonCharacter, author) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),

    checkUserInData("SELECT * FROM s335103Users WHERE (name=? AND password=?);"),

    addUserToData("INSERT INTO s335103Users (name, password) VALUES(?, ?)"),

    clearByUser("DELETE FROM s335103Dragons WHERE (author=?) RETURNING s335103Dragons.id;"),

    getAllForUser("SELECT * FROM s335103Dragons WHERE author=?;"),

    removeById("DELETE FROM s335103Dragons WHERE (author=? AND id=?) RETURNING s335103Dragons.id;"),

    updateById("UPDATE s335103Dragons " +
            "SET name = ?, " +
            "creationDate = ?, " +
            "age = ?, " +
            "wingspan = ?, " +
            "xCoord = ?, " +
            "yCoord = ?, " +
            "color = ?, " +
            "caveDepth = ?, " +
            "caveNumOfTreasures = ?, " +
            "dragonCharacter = ? " +
            "WHERE (id=? AND author=?) RETURNING s335103Dragons.id;"),

    getNextId("SELECT nextval('ids')"),

    getAll("SELECT * FROM s335103Dragons;");

    private final String statement;

    Statements(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}
