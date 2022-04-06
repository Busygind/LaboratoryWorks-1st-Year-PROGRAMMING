package lab.server;

import lab.common.util.commands.CommandAbstract;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.HistorySaver;
import lab.common.util.requestSystem.Response;
import lab.common.util.requestSystem.Serializer;
import lab.server.exceptions.DisconnectInitException;
import lab.server.fileHandlers.XMLReader;
import lab.server.fileHandlers.XMLWriter;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public final class Server {

    private static Selector selector;
    protected static File file;

//    Назначение автоматически генерируемых полей объектов в коллекции.
//    Сохранение коллекции в файл при исполнении специальной команды, доступной только серверу (клиент такую команду отправить не может).
    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        ConsoleThread consoleThread = new ConsoleThread();
        consoleThread.start();
        startServer(args);
        consoleThread.shutdown();
    }

    private static void startServer(String[] args) {
        ServerConfig.logger.info("Server started");
        //String fileName = args[0];
        //file = new File(ServerConfig.starting, fileName); // Initialize file from cmd
        file = new File("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWorks-1st-Year-PROGRAMMING\\LaboratoryWork6\\lab6\\Dragons.xml");
        fillCollectionFromFile(file);
        try {
            selector = Selector.open();
            ServerSocketChannel server = initChannel(selector);
            startSelectorLoop(server);
        } catch (IOException e) {
            ServerConfig.logger.fatal("Some problems with IO. Try again");
        } catch (ClassNotFoundException e) {
            ServerConfig.logger.error("Trying to serialize non-serializable object");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startSelectorLoop(ServerSocketChannel channel) throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
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
                ServerConfig.logger.info("Server get connection from " + socketChannel.getLocalAddress());
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ServerConfig.logger.info("Client " + socketChannel.getLocalAddress() + " trying to send message");
                CommandAbstract command = IOController.getCommand(socketChannel);
                HistorySaver.addCommandInHistory(command);
                try {
                    Response response = IOController.buildResponse(command, ServerConfig.manager);
                    ByteBuffer buffer = Serializer.serializeResponse(response);
                    socketChannel.write(buffer);
                    ServerConfig.logger.info("Server wrote response to client");
                } catch (DisconnectInitException e) {
                    XMLWriter.write(file, ServerConfig.manager);
                    ServerConfig.logger.info("Client " + socketChannel.getLocalAddress() + " init disconnect. Collection successfully saved");
                    socketChannel.close();
                    break;
                }
            }
        }
    }

    private static ServerSocketChannel initChannel(Selector selector) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        ServerConfig.logger.info("Socket opened");
        server.socket().bind(new InetSocketAddress(ServerConfig.SERVER_PORT));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        return server;
    }

    private static void fillCollectionFromFile(File file) {
        XMLReader reader = new XMLReader();
        try {
            for (Dragon dragon : reader.read(file)) {
                ServerConfig.manager.addDragon(dragon);
                if (dragon.getCreationDate() == null) {
                    dragon.setCreationDate();
                }
            }
            ServerConfig.logger.info("Collection successfully filled");
        } catch (IOException e) {
            ServerConfig.logger.fatal("File doesn't exist");
            System.exit(0);
        } catch (Exception e) {
            //todo не понятно, какую ошибку бросает при неверном формате ввода
            ServerConfig.logger.fatal("Can't parse file, data is incorrect");
            System.exit(0);
        }
    }
}
