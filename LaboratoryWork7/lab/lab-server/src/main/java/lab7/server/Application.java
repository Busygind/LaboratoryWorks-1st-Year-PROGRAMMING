package lab7.server;

import lab7.common.util.requestSystem.requests.Request;
import lab7.common.util.requestSystem.responses.Response;
import lab7.server.databaseHandlers.DatabaseConnector;
import lab7.server.databaseHandlers.DatabaseInitializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Application {

//    to use on PC
//    private static final DatabaseConnector CONNECTOR =
//            new DatabaseConnector("jdbc:postgresql://localhost:5432/dragons_database",
//                    "postgres", "chh455");
//    to use on helios
    private static final DatabaseConnector CONNECTOR =
            new DatabaseConnector("jdbc:postgresql://pg:5432/studs",
                    "s335103", "chh455");
    private volatile Connection dbConnection;
    private volatile Selector selector;
    private final ExecutorService readExecutor = Executors.newFixedThreadPool(2);
    private final ExecutorService handleExecutor = Executors.newFixedThreadPool(2);
    private final ExecutorService sendExecutor = Executors.newFixedThreadPool(2);
    private volatile Set<SelectionKey> workingKeys = Collections.synchronizedSet(new HashSet<>());

    public void run() {
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

    private void startServer() {
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

    private void startSelectorLoop(ServerSocketChannel channel) throws IOException, ClassNotFoundException, InterruptedException {
        while (channel.isOpen()) {
            selector.select();
            startIteratorLoop(channel);
        }
    }

    private void startIteratorLoop(ServerSocketChannel channel) throws IOException, ClassNotFoundException {
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isValid() && !workingKeys.contains(key)) {
                if (key.isAcceptable()) {
                    accept(channel);
                } else if (key.isReadable()) {
                    workingKeys.add(key);
                    ServerConfig.LOGGER.info("Client " + ((SocketChannel) key.channel()).getLocalAddress() + " sent message");
                    Supplier<Request> requestReader = new RequestReader(key);
                    Consumer<Response> sender = new ResponseSender(key, workingKeys);
                    CompletableFuture
                            .supplyAsync(requestReader, readExecutor)
                            .thenApplyAsync(request -> {
                                RequestHandler requestHandler = new RequestHandler(request, dbConnection);
                                try {
                                    return requestHandler.handle(request);
                                } catch (IOException e) {
                                    ServerConfig.LOGGER.error("Error during request handling");
                                    return null;
                                }
                            }, handleExecutor)
                            .thenAcceptAsync(sender, sendExecutor);
                }
            }
        }
    }

    private void accept(ServerSocketChannel channel) throws IOException {
        SocketChannel socketChannel = channel.accept();
        ServerConfig.LOGGER.info("Server get connection from " + socketChannel.getLocalAddress());
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private ServerSocketChannel initChannel(Selector selector) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        ServerConfig.LOGGER.info("Socket opened");
        server.socket().bind(new InetSocketAddress(ServerConfig.SERVER_PORT));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        return server;
    }

}
