package lab.client;

import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;
import lab.common.util.requestSystem.Serializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
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
    private static final Scanner scanner = new Scanner(System.in);
    private static final int sleepTime = 1000;
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
        hostname = scanner.nextLine();
        hostAddress = new InetSocketAddress(hostname, PORT);

        try {
            selector = Selector.open();

            SocketChannel client = SocketChannel.open(hostAddress);
            TextFormatter.printMessage("Connected!");
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_WRITE);
            startSelectorLoop(client, scanner);
        } catch (IOException e) {
            TextFormatter.printErrorMessage(e.getMessage());
        } catch (ClassNotFoundException e) {
            TextFormatter.printErrorMessage("Trying to serialize non-serializable object");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startSelectorLoop(SocketChannel channel, Scanner sc) throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
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
                ByteBuffer readBuffer = ByteBuffer.allocate(4096);
                socketChannel.read(readBuffer);
                Response response = Serializer.deserializeResponse(readBuffer.array());
                TextFormatter.printInfoMessage(response.getMessage());
                channel.register(selector, SelectionKey.OP_WRITE);
            } else if (key.isWritable()) {
                TextFormatter.printInfoMessage("Enter command (to check available commands type \"help\"): ");
                try {
                    String input = sc.nextLine();
                    List<String> splittedLine = LineSplitter.smartSplit(input);
                    if (splittedLine.get(0).equalsIgnoreCase("execute_script")) {
                        ScriptReader scriptReader = new ScriptReader(input);
                        startSelectorLoop(channel, new Scanner(scriptReader.getPath()));
                        scriptReader.stopScriptReading();
                    }
                    try {
                        ByteBuffer buffer = StreamController.prepareCommandToSend(input);
                        channel.write(buffer);
                        channel.register(selector, SelectionKey.OP_READ);
                        Thread.sleep(sleepTime);
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
