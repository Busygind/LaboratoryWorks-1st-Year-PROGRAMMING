package lab.server;

import lab.common.util.commands.CommandAbstract;
import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.HistorySaver;
import lab.common.util.handlers.TextFormatter;
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

    private static final CollectionManager manager = new CollectionManager();
    private static final File starting = new File(System.getProperty("user.dir")); // Get current user directory
    private static final int SERVER_PORT = 45846;
    private static Selector selector;
    private static File file;
//    Работа с файлом, хранящим коллекцию.
//    Управление коллекцией объектов.
//    Назначение автоматически генерируемых полей объектов в коллекции.
//    Ожидание подключений и запросов от клиента.
//    Обработка полученных запросов (команд).
//    Сохранение коллекции в файл при завершении работы приложения.
//    Сохранение коллекции в файл при исполнении специальной команды, доступной только серверу (клиент такую команду отправить не может).
    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        //todo засунуть все в application
//        String fileName = args[0];
//        file = new File(starting, fileName); // Initialize file from cmd
        file = new File("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWorks-1st-Year-PROGRAMMING\\LaboratoryWork6\\lab6\\Dragons.xml");
        fillCollectionFromFile(file);
        try {
            selector = Selector.open();

            ServerSocketChannel server = initChannel(selector);
            startSelectorLoop(server);
        } catch (IOException e) {
            TextFormatter.printErrorMessage("Some problems with IO. Try again");
        } catch (ClassNotFoundException e) {
            TextFormatter.printErrorMessage("Trying to serialize non-serializable object");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void startSelectorLoop(ServerSocketChannel channel) throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            startIteratorLoop(channel);
        }
    }

    private static void startIteratorLoop(ServerSocketChannel channel) throws IOException, ClassNotFoundException, InterruptedException {
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();

            if (key.isAcceptable()) {
                SocketChannel socketChannel = channel.accept();

                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                CommandAbstract command = IOController.getCommand(socketChannel);
                HistorySaver.addCommandInHistory(command);
                try {
                    Response response = IOController.buildResponse(command, manager);
                    ByteBuffer buffer = Serializer.serializeResponse(response);
                    socketChannel.write(buffer);
                    TextFormatter.printInfoMessage("Server wrote response to client");
                } catch (DisconnectInitException e) {
                    XMLWriter.write(file, manager);
                    TextFormatter.printInfoMessage("Collection successfully saved");
                    TextFormatter.printInfoMessage(e.getMessage());
                    socketChannel.close();
                    break;
                }
            }
        }
    }

    private static ServerSocketChannel initChannel(Selector selector) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        TextFormatter.printInfoMessage("Socket opened");
        server.socket().bind(new InetSocketAddress(SERVER_PORT));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        return server;
    }

    private static void fillCollectionFromFile(File file) {
        XMLReader reader = new XMLReader();
        try {
            for (Dragon dragon : reader.read(file)) {
                manager.addDragon(dragon);
                if (dragon.getCreationDate() == null) {
                    dragon.setCreationDate();
                }
            }
        } catch (IOException e) {
            TextFormatter.printErrorMessage("Указанный файл не найден, попробуйте снова");
            System.exit(0);
        } catch (Exception e) {
            //todo не понятно, какую ошибку бросает при неверном формате ввода
            TextFormatter.printErrorMessage("Данные в файле представлены в неверном формате, ошибка на уровне работы с данными");
            System.exit(0);
        }
    }
}
