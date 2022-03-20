package lab.server;

import lab.common.util.commands.CommandAbstract;
import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;
import lab.server.fileHandlers.XMLReader;
import lab.server.fileHandlers.XMLWriter;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public final class Server {

    private static final CollectionManager manager = new CollectionManager();
    private static final int SERVER_PORT = 45846;

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
//        File starting = new File(System.getProperty("user.dir")); // Get current user directory
//        File file = new File(starting, fileName); // Initialize file from cmd
//        Logger logger = LogManager.getLogger();
        File file = new File("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWorks-1st-Year-PROGRAMMING\\LaboratoryWork6\\lab6\\Dragons.xml");
        XMLReader reader = new XMLReader(); // Initialize parser
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

        try (ServerSocket server= new ServerSocket(SERVER_PORT)) {
            // становимся в ожидание подключения к сокету под именем - "client" на серверной стороне
            Socket client = server.accept();
            // после хэндшейкинга сервер ассоциирует подключающегося клиента с этим сокетом-соединением
            System.out.println("Connection accepted");
            // инициируем каналы для общения в сокете, для сервера

            // канал записи в сокет
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            System.out.println("ObjectOutputStream created");

            // канал чтения из сокета
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("ObjectInputStream created");

            // начинаем диалог с подключенным клиентом в цикле, пока сокет не закрыт
            while(!client.isClosed()) {

                System.out.println("Server reading from channel");

                // сервер ждёт в канале чтения получения данных клиента
                CommandAbstract commandFromClient = (CommandAbstract) in.readObject();
//                CommandAbstract command = (CommandAbstract) in.readObject();
//                command.execute()
                // после получения данных считывает их
                System.out.println("READ from client message - " + commandFromClient);


                // и выводит в консоль
                System.out.println("Server try writing to channel");

                if (commandFromClient.getName().equals("exit")) {
                    TextFormatter.printInfoMessage("Client initialize disconnect");
                    break;
                }
                // если условие окончания работы не верно - продолжаем работу -
                // отправляем эхо-ответ обратно клиенту
                out.writeObject(new Response(commandFromClient.execute(manager), true));
                out.flush();
                System.out.println("Server Wrote message to client.");
            }

            XMLWriter.write(file, manager);
            TextFormatter.printMessage("Collection successfully saved");

            System.out.println("Closing connections & channels.");
            in.close();
            out.close();

            // потом закрываем сам сокет общения на стороне сервера!
            client.close();
            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException | ClassNotFoundException e) {
            //todo обработать
            e.printStackTrace();
        }

    }
}
