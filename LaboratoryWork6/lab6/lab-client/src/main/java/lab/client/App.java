package lab.client;

import lab.common.util.commands.CommandAbstract;
import lab.common.util.exceptions.CommandNotFoundException;
import lab.common.util.handlers.CommandListener;
import lab.common.util.handlers.LineSplitter;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static final int sleepTimeTwo = 2000;
    static final int port = 45846;

    public static void run() throws IOException {
        SocketChannel channel = SocketChannel.open();
        Scanner sc = new Scanner(System.in);
        TextFormatter.printMessage("Enter host:");
        String host = sc.nextLine();
        channel.connect(new InetSocketAddress(host, port));
//        channel.configureBlocking(false);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectInputStream ois = new ObjectInputStream(channel.socket().getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(channel.socket().getOutputStream())) {

            TextFormatter.printInfoMessage("Client connected to socket.");
            TextFormatter.printInfoMessage("Client writing channel = oos & reading channel = ois initialized.");
            // проверяем живой ли канал и работаем если живой
            while (channel.isOpen()) {
                // ждём консоли клиента на предмет появления в ней данных
                if (br.ready()) {
                    // данные появились - работаем
                    TextFormatter.printInfoMessage("Client start writing in channel...");
                    Thread.sleep(1000);
                    String input = br.readLine();
                    ArrayList<String> splitCommand = LineSplitter.smartSplit(input);
                    // проверяем условие выхода из соединения
                    if ("exit".equalsIgnoreCase(splitCommand.get(0)) && splitCommand.size() == 1) {
                        // если условие выхода достигнуто разъединяемся
                        TextFormatter.printInfoMessage("Client kill connections");
                        oos.writeObject(CommandListener.initCommand("exit"));
                        oos.flush();
                        Thread.sleep(sleepTimeTwo);
                        // смотрим что нам ответил сервер
                        // напоследок перед закрытием ресурсов
                        if (ois.read() > -1) {
                            StreamController.getResponse(ois);
                        }
                        // после предварительных приготовлений выходим
                        // из цикла записи чтения
                        break;
                    }

                    try {
                      StreamController.sendCommand(oos, input);
                    } catch (IllegalArgumentException e) {
                        TextFormatter.printErrorMessage("Incorrect arguments");
                        continue;
                    } catch (CommandNotFoundException e) {
                        TextFormatter.printErrorMessage("Command not found. Type \"help\" to check available commands");
                        continue;
                    } catch (NullPointerException e) {
                        TextFormatter.printErrorMessage(e.getMessage());
                        continue;
                    }

                    // если успел забираем ответ из канала сервера в сокете и
                    // сохраняем её в ois переменную, печатаем на свою клиентскую консоль
                    StreamController.getResponse(ois);
                }
            }
// на выходе из цикла общения закрываем свои ресурсы
            System.out.println("Closing connections & channels on clientSide - DONE.");

        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
