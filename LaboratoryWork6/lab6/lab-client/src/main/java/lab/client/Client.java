package lab.client;

import lab.client.entities.CollectionManager;
import lab.client.entities.Dragon;
import lab.client.handlers.CommandListener;
import lab.client.handlers.CommandManager;
import lab.client.handlers.TextFormatter;
import lab.client.handlers.XMLReader;

import java.io.File;
import java.io.IOException;

/**
 @author Dmitry Busygin
 */
public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    /**
     * Старт программы. Инициализация входного файла с содержимым коллекции, создание парсера и занесение
     * первоначальных данных в коллекцию для дальнейшей работы
     *
     * @param args аргументы, переданные вместе с запуском программы (в случае данного проекта необходимо передать
     *              единственную строку с названием файла, содержащего данные о коллекции)
     * @throws IOException возникает при ошибке работы с файлом или его отсутствием в текущей директории
     */
    public static void main(String[] args) throws IOException {
//        String fileName = args[0];
//        File starting = new File(System.getProperty("user.dir")); // Get current user directory
//        File file = new File(starting, fileName); // Initialize file from cmd
//        Logger logger = LogManager.getLogger();
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
        CommandListener listener = new CommandListener(System.in);
        listener.run();

    }
}
