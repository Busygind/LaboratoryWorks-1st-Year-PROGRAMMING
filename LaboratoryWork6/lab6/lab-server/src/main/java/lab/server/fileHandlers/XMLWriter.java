package lab.server.fileHandlers;

import com.thoughtworks.xstream.XStream;
import lab.common.util.entities.CollectionManager;
import lab.common.util.entities.Dragon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс, отвечающий за сохранение текущей коллекции в xml-файл
 */
public class XMLWriter {

    /**
     * Метод, сохраняющий данные в формате xml, ИСПОЛЬЗУЕТСЯ СТОРОННЯЯ БИБЛИОТЕКА XStream
     *
     * @param file файл, в который производится запись
     * @param dragons коллекция, которую необходимо сохранить
     * @throws IOException возникает при невозможности записи в файл полученных данных
     */
    public static void write(File file, CollectionManager dragons) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("dragon", Dragon.class);
        xStream.alias("set", CollectionManager.class);
        xStream.addImplicitCollection(CollectionManager.class, "dragons");
        String xmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" + xStream.toXML(dragons.getDragons());
        FileWriter writer = new FileWriter(file);
        writer.write(xmlText);
        writer.flush();
        writer.close();
    }

}
