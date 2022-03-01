package com.lab5.client.handlers;

import com.lab5.client.entities.CollectionOfDragons;
import com.lab5.client.entities.Dragon;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

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
    public HashSet<Dragon> read(File file) throws IOException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("dragon", Dragon.class);
        xStream.alias("set", CollectionOfDragons.class);
        xStream.addImplicitCollection(CollectionOfDragons.class, "dragons");
        StringBuilder xmlText = new StringBuilder();
        FileReader reader = new FileReader(file);
        Scanner sc = new Scanner(reader);
        while (sc.hasNextLine()) {
            xmlText.append(sc.nextLine());
        }
        reader.close();
        CollectionOfDragons dragons = (CollectionOfDragons) xStream.fromXML(xmlText.toString());
        return dragons.getDragons();
    }

}
