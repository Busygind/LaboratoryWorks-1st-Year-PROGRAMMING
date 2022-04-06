package lab.common.util.requestSystem;

import lab.common.util.entities.Dragon;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {

    private final String message;
    private ArrayList<Dragon> dragons = null;

    public Response(String message) {
        this.message = message;
    }

    public Response(ArrayList<Dragon> dragons, String message) {
        this.dragons = dragons;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Dragon> getDragons() {
        return dragons;
    }
}
