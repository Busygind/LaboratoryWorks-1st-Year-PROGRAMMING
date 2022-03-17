package lab.server.fileHandlers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;
import lab.common.util.handlers.DragonValidator;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс, отвечающий за стартовую обработку xml-файла с данными о коллекции
 */
public class XMLReader {

    /**
     * Метод, преобразующий xml-файл в коллекцию драконов
     *
     * @param file файл, из которого происходит считывание
     * @return заполненная коллекцию HashSet
     * @throws IOException возникает при некорректных данных в файле или их неправильной интерпретации
     */
    public HashSet<Dragon> read(File file) throws IOException, NumberFormatException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("dragon", Dragon.class);
        xStream.alias("set", CollectionManager.class);
        xStream.addImplicitCollection(CollectionManager.class, "dragons");
        StringBuilder xmlText = new StringBuilder();
        FileReader reader = new FileReader(file);
        Scanner sc = new Scanner(reader);
        while (sc.hasNextLine()) {
            xmlText.append(sc.nextLine());
        }
        reader.close();
        CollectionManager dragons = (CollectionManager) xStream.fromXML(xmlText.toString());
        boolean correctCollection = true;
        for (Dragon elem : dragons.getDragons()) {
            if (!DragonValidator.validateDragon(elem)) {
                correctCollection = false;
                System.out.println("Ошибка чтения файла на уровне валидации");
                System.exit(0);
            }
        }
        return dragons.getDragons();
    }

}
