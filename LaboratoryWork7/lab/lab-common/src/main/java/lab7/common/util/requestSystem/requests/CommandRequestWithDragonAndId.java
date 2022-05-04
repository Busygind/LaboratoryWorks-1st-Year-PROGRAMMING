package lab7.common.util.requestSystem.requests;

import javafx.util.Pair;
import lab7.common.util.entities.Dragon;

import java.io.Serializable;

public class CommandRequestWithDragonAndId implements CommandRequest, Serializable {

    private static final RequestType TYPE = RequestType.COMMAND_WITH_DRAGON_AND_ID;
    private final String name;
    private final Dragon dragon;
    private final long id;
    private final Pair<String, String> loginData;

    public CommandRequestWithDragonAndId(String name, Dragon dragon, long id, Pair<String, String> loginData) {
        this.name = name;
        this.dragon = dragon;
        this.id = id;
        this.loginData = loginData;
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
    public Pair<String, String> getPair() {
        return loginData;
    }

    @Override
    public RequestType getType() {
        return TYPE;
    }
}
