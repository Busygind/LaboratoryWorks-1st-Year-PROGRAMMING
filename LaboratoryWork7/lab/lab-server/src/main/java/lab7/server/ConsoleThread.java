package lab7.server;

import java.util.Scanner;

public class ConsoleThread extends Thread {
    private static final Scanner scanner = ServerConfig.SCANNER;
    private volatile boolean running = true;

    @Override
    public void run() {
        ServerConfig.LOGGER.info("Console thread is running");
        while (running) {
            String line = scanner.nextLine();
            if ("exit".equalsIgnoreCase(line)) {
                ServerConfig.LOGGER.info("Server closed");
                System.exit(0);
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
