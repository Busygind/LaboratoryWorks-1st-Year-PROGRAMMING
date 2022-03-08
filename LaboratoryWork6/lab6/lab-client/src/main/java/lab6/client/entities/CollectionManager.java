package lab6.client.entities;

import lab6.client.handlers.ArgumentsListener;
import lab6.client.handlers.TextFormatter;
import lab6.client.handlers.XMLWriter;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
//todo реализовать синглтон
/**
 * Класс коллекции, содержащий текущую коллекцию <b>dragons</b>,
 * отвечает за генерацию ID для новых элементов и за все действия,
 * связанные с коллекцией.
 */
public class CollectionManager {
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
    public CollectionManager() {
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
        TextFormatter.printInfoMessage("Дракон успешно добавлен в коллекцию");
    }

    /**
     * Метод, очищающий текущую коллекцию
     */
    public void clear() {
        dragons.clear();
        if (this.getDragons().isEmpty()) {
            TextFormatter.printInfoMessage("Collection successful cleared");
        } else {
            TextFormatter.printErrorMessage("Something went wrong, try again.");
        }
    }

    /**
     * Метод, удаляющий дракона из коллекции по полученному ID, если таковой существует
     *
     * @param id id дракона, которого нужно удалить
     */
    public void removeById(String id) {
        boolean idIsValid = false;
        try {
            long idNumber = Long.parseLong(id);
            for (Dragon dragon : dragons) {
                if (dragon.getId() == idNumber) {
                    idIsValid = true;
                    dragons.remove(dragon);
                    TextFormatter.printInfoMessage("Дракон успешно удален");
                    break;
                }
            }
            if (!idIsValid) {
                TextFormatter.printErrorMessage("Дракона с таким id нет в коллекции");
            }
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("ID имеет некорректный формат");
        }
    }

    public void updateById(String id) {
        ArgumentsListener argumentsListener = new ArgumentsListener();
        try {
            long newId = Long.parseLong(id);
            for (Dragon elem : getDragons()) {
                if (elem.getId() == newId) {
                    TextFormatter.printInfoMessage("Введите информацию о драконе: {name, age, wingspan}");
                    argumentsListener.inputPrimitives(elem);
                    elem.setCoordinates(argumentsListener.inputCoordinates());
                    argumentsListener.inputColor(elem);
                    argumentsListener.inputCharacter(elem);
                    elem.setCave(argumentsListener.inputCave());
                    TextFormatter.printInfoMessage("Данные о драконе успешно обновлены");
                }
            }
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("ID представлено в неверном формате");
        }
    }

    public void save() {
        XMLWriter writer = new XMLWriter();
        try {
            writer.write(this.getOutFile(), this);
            TextFormatter.printInfoMessage("Коллекция успешно сохранена");
        } catch (IOException e) {
            TextFormatter.printErrorMessage("Ошибка сохранения, файл не найден или некорректен");
        }
    }


    /**
     * Метод, выводящий пользователю информацию о коллекции
     */
    public void showInfo() {
        TextFormatter.printInfoMessage("Information about collection: ");
        TextFormatter.printMessage("Collection type: " + dragons.getClass()
                + " initialization date: " + creationDate
                + " count of dragons: " + dragons.size());
    }
}
