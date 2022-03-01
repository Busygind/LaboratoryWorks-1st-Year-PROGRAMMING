package com.lab5.client;

import com.lab5.client.entities.CollectionOfDragons;
import com.lab5.client.entities.Dragon;
import com.lab5.client.handlers.CommandListener;
import com.lab5.client.handlers.XMLReader;

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
        String fileName = args[0];
        File starting = new File(System.getProperty("user.dir")); // Get current user directory
        File file = new File(starting, fileName); // Initialize file from cmd
        //File file = new File("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWork5\\lab5\\lab-client\\src\\main\\Dragons.xml");
        XMLReader reader = new XMLReader(); // Initialize parser
        CollectionOfDragons collection = new CollectionOfDragons(); // Initialize collection
        for (Dragon elem : reader.read(file)) {
            collection.addDragon(elem);
            if (elem.getCreationDate() == null) {
                elem.setCreationDate();
            }
        }
        collection.setOutFile(file);
        CommandListener cl = new CommandListener(collection);
        cl.commandsReader();
    }
}
