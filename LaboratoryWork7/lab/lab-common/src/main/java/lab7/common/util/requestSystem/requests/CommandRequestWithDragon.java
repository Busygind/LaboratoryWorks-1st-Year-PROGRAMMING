package lab7.common.util.requestSystem.requests;

import javafx.util.Pair;
import lab7.common.util.entities.Dragon;

import java.io.Serializable;

public class CommandRequestWithDragon implements CommandRequest, Serializable {

    private static final RequestType TYPE = RequestType.COMMAND_WITH_DRAGON;
    private final String name;
    private final Dragon dragon;
    private final Pair<String, String> loginData;

    public CommandRequestWithDragon(String name, Dragon dragon, Pair<String, String> loginData) {
        this.name = name;
        this.dragon = dragon;
        this.loginData = loginData;
    }

    public Dragon getDragon() {
        return dragon;
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
