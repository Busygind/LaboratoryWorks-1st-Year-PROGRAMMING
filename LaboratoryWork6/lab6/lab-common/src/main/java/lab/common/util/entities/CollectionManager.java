package lab.common.util.entities;

import lab.common.util.handlers.TextFormatter;

import java.util.Date;
import java.util.HashSet;
/**
 * Класс коллекции, содержащий текущую коллекцию <b>dragons</b>,
 * отвечает за генерацию ID для новых элементов и за все действия,
 * связанные с коллекцией.
 */
public class CollectionManager {
    /**
     * Дата создания коллекции
     */
    private final Date creationDate;
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
     * Метод, добавляющий полученного дракона в коллекцию
     *
     * @param dragon дракон, которого нужно добавить в коллекцию
     */
    public void addDragon(Dragon dragon) {
        dragon.setId();
        dragons.add(dragon);
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
    public String removeById(long id) {
        try {
            Dragon dragon = getById(id);
            if (dragon != null) {
                dragons.remove(dragon);
                return TextFormatter.colorInfoMessage("Dragon successfully removed");
            } else {
                return TextFormatter.colorErrorMessage("Dragon with that ID not found");
            }
        } catch (NumberFormatException e) {
            return TextFormatter.colorErrorMessage("ID имеет некорректный формат");
        }
    }

    public Dragon getById(Long id) {
        return dragons.stream().filter(dr -> dr.getId().equals(id)).findAny().orElse(null);
    }

    /**
     * Метод, выводящий пользователю информацию о коллекции
     */
    public String showInfo() {
        return TextFormatter.colorInfoMessage("Information about collection: ")
                + TextFormatter.colorMessage("Collection type: " + dragons.getClass()
                + " initialization date: " + creationDate
                + " count of dragons: " + dragons.size());
    }

    public Dragon getMaxByCave() {
        return dragons.stream().max(Dragon::compareByCave).get();
    }

    public Dragon getMax() {
        return dragons.stream().max(Dragon::compareTo).get();
    }

    public Dragon getMin() {
        return dragons.stream().min(Dragon::compareTo).get();
    }
}
