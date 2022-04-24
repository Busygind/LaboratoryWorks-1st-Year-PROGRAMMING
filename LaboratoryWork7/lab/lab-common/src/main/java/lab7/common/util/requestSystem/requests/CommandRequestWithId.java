package lab7.common.util.requestSystem.requests;

import java.io.Serializable;

public class CommandRequestWithId implements CommandRequest, Serializable {

    private static final RequestType type = RequestType.COMMAND_WITH_ID;
    private final String name;
    private final long id;
    private final String username;

    public CommandRequestWithId(String name, long id, String username) {
        this.name = name;
        this.id = id;
        this.username = username;
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
        return type;
    }
}
