package lab7.server;

import lab7.common.util.requestSystem.requests.*;
import lab7.server.databaseHandlers.DatabaseConnector;
import lab7.server.databaseHandlers.DatabaseInitializer;
import lab7.server.exceptions.DisconnectInitException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public final class Server {

    private static final DatabaseConnector CONNECTOR =
            new DatabaseConnector("jdbc:postgresql://localhost:5432/dragons_database",
                                "postgres", "chh455");
    private static Selector selector;
    private static Connection dbConnection;
    static ExecutorService threadPool = Executors.newFixedThreadPool(2);


    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        try {
            dbConnection = CONNECTOR.getNewConnection();
            DatabaseInitializer initializer = new DatabaseInitializer(dbConnection);
            initializer.initialize();
            initializer.fillCollection(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConsoleThread consoleThread = new ConsoleThread();
        consoleThread.start();
        startServer();
        consoleThread.shutdown();
    }

    private static void startServer() {
        ServerConfig.LOGGER.info("Server started");
        try {
            selector = Selector.open();
            ServerSocketChannel server = initChannel(selector);
            startSelectorLoop(server);
        } catch (IOException e) {
            ServerConfig.LOGGER.fatal("Some problems with IO. Try again");
        } catch (ClassNotFoundException e) {
            ServerConfig.LOGGER.error("Trying to serialize non-serializable object");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startSelectorLoop(ServerSocketChannel channel) throws IOException, ClassNotFoundException, InterruptedException {
        while (channel.isOpen()) {
            selector.select();
            startIteratorLoop(channel);
        }
    }

    private static void startIteratorLoop(ServerSocketChannel channel) throws IOException, ClassNotFoundException {
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();

            if (key.isAcceptable()) {
                SocketChannel socketChannel = channel.accept();
                ServerConfig.LOGGER.info("Server get connection from " + socketChannel.getLocalAddress());
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ServerConfig.LOGGER.info("Client " + socketChannel.getLocalAddress() + " trying to send message");
                Request request = IOController.getRequest(socketChannel);
                RequestReader requestReader = new RequestReader(request, socketChannel, dbConnection);
                try {
                    requestReader.read();
                } catch (DisconnectInitException e) {
                    ServerConfig.LOGGER.info("Client " + socketChannel.getLocalAddress() + " init disconnect.");
                    socketChannel.close();
                    break;
                }

            }
        }
    }

    private static ServerSocketChannel initChannel(Selector selector) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        ServerConfig.LOGGER.info("Socket opened");
        server.socket().bind(new InetSocketAddress(ServerConfig.SERVER_PORT));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        return server;
    }

}
