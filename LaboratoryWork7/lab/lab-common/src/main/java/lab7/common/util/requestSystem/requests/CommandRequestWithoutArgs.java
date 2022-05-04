package lab7.common.util.requestSystem.requests;

import javafx.util.Pair;

import java.io.Serializable;

public class CommandRequestWithoutArgs implements CommandRequest, Serializable {

    private static final RequestType TYPE = RequestType.COMMAND_WITHOUT_ARGS;
    private final String name;
    private final Pair<String, String> loginData;

    public CommandRequestWithoutArgs(String name, Pair<String, String> loginData) {
        this.name = name;
        this.loginData = loginData;
    }

    @Override
    public RequestType getType() {
        return TYPE;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Pair<String, String> getPair() {
        return loginData;
    }
}
