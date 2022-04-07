package lab.client;

import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;
import lab.common.util.requestSystem.Serializer;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 @author Dmitry Busygin
 */
public final class Client {

    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    private static final int PORT = 45846;
    private static final int BYTE_BUFFER_LENGTH = 4096;
    private static final int SLEEP_TIME = 1000;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static InetSocketAddress hostAddress;
    private static Selector selector;
    private static String hostname;

    /**
     * Старт программы. Инициализация входного файла с содержимым коллекции, создание парсера и занесение
     * первоначальных данных в коллекцию для дальнейшей работы
     *
     * @param args аргументы, переданные вместе с запуском программы (в случае данного проекта необходимо передать
     *              единственную строку с названием файла, содержащего данные о коллекции)
     * @throws IOException возникает при ошибке работы с файлом или его отсутствием в текущей директории
     */
    public static void main(String[] args) {
        TextFormatter.printMessage("Enter hostname: ");
        hostname = SCANNER.nextLine();
        hostAddress = new InetSocketAddress(hostname, PORT);

        try {
            selector = Selector.open();

            SocketChannel client = SocketChannel.open(hostAddress);
            TextFormatter.printMessage("Connected!");
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_WRITE);
            startSelectorLoop(client, SCANNER);
        } catch (ConnectException e) {
            TextFormatter.printErrorMessage("Server with this host is temporarily unavailable. Try again later");
            main(args);
        } catch (StreamCorruptedException e) {
            TextFormatter.printErrorMessage("Disconnected.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            TextFormatter.printErrorMessage(e.getMessage());
        } catch (ClassNotFoundException e) {
            TextFormatter.printErrorMessage("Trying to serialize non-serializable object");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnresolvedAddressException e) {
            TextFormatter.printErrorMessage("Server with this host not found. Try again");
            main(args);
        }
    }

    private static void startSelectorLoop(SocketChannel channel, Scanner sc) throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            selector.select();
            if (!startIteratorLoop(channel, sc)) {
                break;
            }
        }
    }

    private static boolean startIteratorLoop(SocketChannel channel, Scanner sc) throws IOException, ClassNotFoundException, InterruptedException {
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();

            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(BYTE_BUFFER_LENGTH);
                socketChannel.read(readBuffer);
                Response response = Serializer.deserializeResponse(readBuffer.array());
                TextFormatter.printInfoMessage(response.getMessage());
                if (response.getDragons() != null) {
                    TextFormatter.printMessage(response.getDragons().toString());
                }
                channel.register(selector, SelectionKey.OP_WRITE);
            } else if (key.isWritable()) {
                TextFormatter.printInfoMessage("Enter command (to check available commands type \"help\"): ");
                try {
                    String input = sc.nextLine();
                    List<String> splittedLine = LineSplitter.smartSplit(input);
                    if (splittedLine.get(0).equalsIgnoreCase("execute_script") && splittedLine.size() == 2) {
                        ScriptReader scriptReader = new ScriptReader(input);
                        startSelectorLoop(channel, new Scanner(scriptReader.getPath()));
                        scriptReader.stopScriptReading();
                    }
                    try {
                        ByteBuffer buffer = CommandBuilder.buildCommand(input);
                        channel.write(buffer);
                        channel.register(selector, SelectionKey.OP_READ);
                        Thread.sleep(SLEEP_TIME);
                    } catch (NullPointerException | IOException e) {
                        TextFormatter.printErrorMessage(e.getMessage());
                    }
                } catch (NoSuchElementException | IllegalArgumentException e) {
                    TextFormatter.printErrorMessage(e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }
}
