package lab.server;

import lab.common.util.entities.CollectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Scanner;

public class ServerConfig {
    protected static final CollectionManager manager = new CollectionManager();
    protected static final Logger logger = LogManager.getLogger();
    protected static final File starting = new File(System.getProperty("user.dir")); // Get current user directory
    protected static final int SERVER_PORT = 45846;
    protected static final Scanner scanner = new Scanner(System.in);
}
