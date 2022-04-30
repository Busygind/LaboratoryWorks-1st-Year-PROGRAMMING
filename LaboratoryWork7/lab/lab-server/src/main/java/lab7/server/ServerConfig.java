package lab7.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerConfig {
    public static final CollectionManager MANAGER = new CollectionManager();
    public static final Logger LOGGER = LogManager.getLogger();
    protected static final int SERVER_PORT = 45846;
}
