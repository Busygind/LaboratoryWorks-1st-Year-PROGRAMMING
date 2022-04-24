package lab7.common.util.requestSystem.responses;

import lab7.common.util.entities.Dragon;

import java.io.Serializable;
import java.util.List;

public class CommandResponse implements Serializable, Response {

    private static final ResponseType type = ResponseType.COMMAND;
    private final String message;
    private List<Dragon> dragons = null;
    private int countOfDragons = 0;

    public CommandResponse(String message) {
        this.message = message;
    }

    public CommandResponse(List<Dragon> dragons, String message) {
        this.dragons = dragons;
        this.message = message;
        this.countOfDragons = dragons.size();
    }

    public String getMessage() {
        return message;
    }

    public List<Dragon> getDragons() {
        return dragons;
    }

    public int getCountOfDragons() {
        return countOfDragons;
    }

    @Override
    public ResponseType getType() {
        return type;
    }
}
