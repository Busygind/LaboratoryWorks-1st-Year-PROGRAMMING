package lab7.common.util.requestSystem.requests;

import lab7.common.util.entities.Dragon;

import java.io.Serializable;

public class CommandRequestWithDragon implements CommandRequest, Serializable {

    private static final RequestType TYPE = RequestType.COMMAND_WITH_DRAGON;
    private final String name;
    private final Dragon dragon;
    private final String username;

    public CommandRequestWithDragon(String name, Dragon dragon, String username) {
        this.name = name;
        this.dragon = dragon;
        this.username = username;
    }

    public Dragon getDragon() {
        return dragon;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public RequestType getType() {
        return TYPE;
    }
}
