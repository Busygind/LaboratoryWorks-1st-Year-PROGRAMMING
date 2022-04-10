package lab.common.util.requestSystem;

import lab.common.util.entities.Dragon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {

    private final String message;
    private List<Dragon> dragons = null;
    private int countOfDragons = 0;

    public Response(String message) {
        this.message = message;
    }

    public Response(List<Dragon> dragons, String message) {
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
}
