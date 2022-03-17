package lab.client;

import lab.common.util.handlers.CommandListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class App {
    public static void run() throws IOException {
        final int sleepTimeOne = 1000;
        final int sleepTimeTwo = 2000;
        final int port = 45846;
        SocketChannel channel = SocketChannel.open();
        Scanner sc = new Scanner(System.in);
        String host = sc.nextLine();
        channel.connect(new InetSocketAddress(host, port));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             ObjectInputStream ois = new ObjectInputStream(channel.socket().getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(channel.socket().getOutputStream())) {

            System.out.println("Client connected to socket.");
            System.out.println("Client writing channel = oos & reading channel = ois initialized.");
            // проверяем живой ли канал и работаем если живой
            while (channel.isOpen()) {
                // ждём консоли клиента на предмет появления в ней данных
                if (br.ready()) {
                    // данные появились - работаем
                    System.out.println("Client start writing in channel...");
                    Thread.sleep(1000);
                    String clientCommand = br.readLine();

                    // пишем данные с консоли в канал сокета для сервера
                    oos.writeObject(clientCommand);
                    oos.flush();
                    System.out.println("Client sent message " + clientCommand + " to server.");
                    Thread.sleep(sleepTimeOne);
                    // ждём чтобы сервер успел прочесть сообщение
                    // из сокета и ответить

                    // проверяем условие выхода из соединения
                    if ("exit".equalsIgnoreCase(clientCommand)) {
                        // если условие выхода достигнуто разъединяемся
                        System.out.println("Client kill connections");
                        Thread.sleep(sleepTimeTwo);
                        // смотрим что нам ответил сервер
                        // напоследок перед закрытием ресурсов
                        if (ois.read() > -1) {
                            System.out.println("reading...");
                            String in = (String) ois.readObject();
                            System.out.println(in);
                        }
                        // после предварительных приготовлений выходим
                        // из цикла записи чтения
                        break;
                    }

                    // если условие разъединения не достигнуто продолжаем работу
                    System.out.println("Client sent message & start waiting for data from server...");
                    Thread.sleep(sleepTimeTwo);

                    // если успел забираем ответ из канала сервера в сокете и
                    // сохраняем её в ois переменную, печатаем на свою клиентскую консоль
                    System.out.println("reading...");
                    String in = (String) ois.readObject();
                    System.out.println(in);
                }
            }
// на выходе из цикла общения закрываем свои ресурсы
            System.out.println("Closing connections & channels on clientSide - DONE.");

        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        CommandListener listener = new CommandListener(System.in);
        listener.run();
    }
}
