package com.lab5.client.entities;

import java.io.File;
import java.util.Date;
import java.util.HashSet;

/**
 * Класс коллекции, содержащий текущую коллекцию <b>dragons</b>,
 * отвечает за генерацию ID для новых элементов и за все действия,
 * связанные с коллекцией.
 */
public class CollectionOfDragons {
    /**
     * Статичное поле, отвечающее за инкрементацию ID для исключения повторений
     */
    private static int idCounter = 1;
    /**
     * Дата создания коллекции
     */
    private final Date creationDate;
    /**
     * Файл, в который будет производиться запись коллекции при сохранении
     */
    private File outFile;
    /**
     * Сет объектов класса Dragon, текущее содержимое коллекции
     */
    private HashSet<Dragon> dragons;

    /**
     * Конструктор объекта данного класса.
     * Устанавливает коллекцию и дату её создания
     */
    public CollectionOfDragons() {
        dragons = new HashSet<>();
        creationDate = new Date();
    }

    /**
     * Метод, возвращающий текущую коллекцию драконов в формате HashSet
     *
     * @return HashSet драконов, находящихся в коллекции
     */
    public HashSet<Dragon> getDragons() {
        return dragons;
    }

    /**
     * Метод, возвращающий дату создания коллекции
     *
     * @return дата создания коллекции
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Метод, возвращающий текущий файл, в который будет производиться запись коллекции при сохранении
     *
     * @return файл, в который производится запись готовой коллекции
     */
    public File getOutFile() {
        return outFile;
    }

    /**
     * Метод, устанавливающий файл, в который будет производиться запись коллекции при сохранении
     *
     * @param outFile файл, в который будет производиться запись коллекции
     */
    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    /**
     * Метод, добавляющий полученного дракона в коллекцию
     *
     * @param dragon дракон, которого нужно добавить в коллекцию
     */
    public void addDragon(Dragon dragon) {
        dragon.setId();
        idCounter++;
        dragons.add(dragon);
        System.out.println("Дракон успешно добавлен в коллекцию");
    }

    /**
     * Метод, очищающий текущую коллекцию
     */
    public void clear() {
        dragons.clear();
    }

    /**
     * Метод, удаляющий дракона из коллекции по полученному ID, если таковой существует
     *
     * @param id id дракона, которого нужно удалить
     */
    public void removeById(long id) {
        boolean idIsValid = false;
        for (Dragon dragon : dragons) {
            if (dragon.getId() == id) {
                idIsValid = true;
                dragons.remove(dragon);
                System.out.println("Дракон успешно удален");
            }
        }
        if (!idIsValid) {
            System.out.println("Дракона с таким id нет в коллекции");
        }
    }

    /**
     * Метод, выводящий пользователю информацию о коллекции
     */
    public void showInfo() {
        System.out.println("Collection type: " + dragons.getClass()
                + " initialization date: " + creationDate
                + " count of dragons: " + dragons.size());
    }
}
