package lab7.common.util.requestSystem.requests;

import java.io.Serializable;

public class CommandRequestWithoutArgs implements CommandRequest, Serializable {

    private static final RequestType TYPE = RequestType.COMMAND_WITHOUT_ARGS;
    private final String name;
    private final String username;

    public CommandRequestWithoutArgs(String name, String username) {
        this.name = name;
        this.username = username;
    }

//    public CommandAbstract getCommand() {
//        return command;
//    }

    @Override
    public RequestType getType() {
        return TYPE;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
