package lab.server;

import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.CommandManager;
import lab.common.util.handlers.TextFormatter;
import lab.server.fileHandlers.XMLReader;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public final class Server {

    public static final int SERVER_PORT = 45846;

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
        final int sleepTimeThree = 3000;
        //todo засунуть все в application
//        String fileName = args[0];
//        File starting = new File(System.getProperty("user.dir")); // Get current user directory
//        File file = new File(starting, fileName); // Initialize file from cmd
//        Logger logger = LogManager.getLogger();
        try (ServerSocket server= new ServerSocket(SERVER_PORT)){
            // становимся в ожидание подключения к сокету под именем - "client" на серверной стороне
            Socket client = server.accept();
            // после хэндшейкинга сервер ассоциирует подключающегося клиента с этим сокетом-соединением
            System.out.println("Connection accepted.");
            // инициируем каналы для общения в сокете, для сервера

            // канал записи в сокет
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            System.out.println("ObjectOutputStream created");

            // канал чтения из сокета
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("ObjectInputStream created");

            // начинаем диалог с подключенным клиентом в цикле, пока сокет не закрыт
            while(!client.isClosed()){

                System.out.println("Server reading from channel");

                // сервер ждёт в канале чтения (inputstream) получения данных клиента
                String entry = (String) in.readObject();

                // после получения данных считывает их
                System.out.println("READ from client message - " + entry);

                // и выводит в консоль
                System.out.println("Server try writing to channel");

// инициализация проверки условия продолжения работы с клиентом по этому сокету по кодовому слову       - quit
                if(entry.equalsIgnoreCase("quit")){
                    System.out.println("Client initialize connections suicide ...");
                    out.writeObject("Server reply - " + entry + " - OK");
                    out.flush();
                    //out.close();
                    Thread.sleep(sleepTimeThree);
                    break;
                }

// если условие окончания работы не верно - продолжаем работу - отправляем эхо-ответ  обратно клиенту
                out.writeObject("Server reply - " + entry + " - OK");
                out.flush();
                //out.close();
                System.out.println("Server Wrote message to client.");

// освобождаем буфер сетевых сообщений (по умолчанию сообщение не сразу отправляется в сеть, а сначала накапливается в специальном буфере сообщений, размер которого определяется конкретными настройками в системе, а метод  - flush() отправляет сообщение не дожидаясь наполнения буфера согласно настройкам системы

            }

// если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            out.close();

            // потом закрываем сам сокет общения на стороне сервера!
            client.close();

            // потом закрываем сокет сервера который создаёт сокеты общения
            // хотя при многопоточном применении его закрывать не нужно
            // для возможности поставить этот серверный сокет обратно в ожидание нового подключения

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        File file = new File("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWorks-1st-Year-PROGRAMMING\\LaboratoryWork6\\lab6\\Dragons.xml");
        XMLReader reader = new XMLReader(); // Initialize parser
        CollectionManager collection = new CollectionManager(); // Initialize collection
        //todo отправить коллекцию на сервер, а не передавать менеджеру
        CommandManager.initCommands(collection);
        try {
            for (Dragon dragon : reader.read(file)) {
                collection.addDragon(dragon);
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
        collection.setOutFile(file);
    }
}
