package lab7.server;

import lab7.common.util.entities.CollectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ServerConfig {
    public static final CollectionManager MANAGER = new CollectionManager();
    public static final Logger LOGGER = LogManager.getLogger();
    protected static final int SERVER_PORT = 45846;
    protected static final Scanner SCANNER = new Scanner(System.in);
}
