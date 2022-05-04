package lab7.common.util.requestSystem.requests;

import javafx.util.Pair;

import java.io.Serializable;

public class CommandRequestWithId implements CommandRequest, Serializable {

    private static final RequestType TYPE = RequestType.COMMAND_WITH_ID;
    private final String name;
    private final long id;
    private final Pair<String, String> loginData;

    public CommandRequestWithId(String name, long id, Pair<String, String> loginData) {
        this.name = name;
        this.id = id;
        this.loginData = loginData;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Pair<String, String> getPair() {
        return loginData;
    }

    @Override
    public RequestType getType() {
        return TYPE;
    }
}
