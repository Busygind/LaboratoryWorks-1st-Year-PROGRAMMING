package lab7.common.util.requestSystem.requests;

import lab7.common.util.entities.Dragon;

import java.io.Serializable;

public class CommandRequestWithDragonAndId implements CommandRequest, Serializable {

    private static final RequestType TYPE = RequestType.COMMAND_WITH_DRAGON_AND_ID;
    private final String name;
    private final Dragon dragon;
    private final long id;
    private final String username;

    public CommandRequestWithDragonAndId(String name, Dragon dragon, long id, String username) {
        this.name = name;
        this.dragon = dragon;
        this.id = id;
        this.username = username;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public long getId() {
        return id;
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
