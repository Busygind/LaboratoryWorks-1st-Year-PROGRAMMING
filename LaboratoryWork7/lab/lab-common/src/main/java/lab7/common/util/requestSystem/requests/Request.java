package lab7.common.util.requestSystem.requests;

import java.io.Serializable;

public interface Request extends Serializable {
    RequestType getType();
}
