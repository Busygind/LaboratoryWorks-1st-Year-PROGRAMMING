package lab7.common.util.requestSystem.requests;

import java.io.Serializable;

public interface CommandRequest extends Request, Serializable {
    String getName();
    String getUsername();
}
