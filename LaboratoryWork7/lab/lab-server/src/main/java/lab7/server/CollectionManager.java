package lab7.server;

import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;

import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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

    private final Lock readLock;
    private final Lock writeLock;

    /**
     * Конструктор объекта данного класса.
     * Устанавливает коллекцию и дату её создания
     */
    public CollectionManager() {
        dragons = new HashSet<>();
        creationDate = new Date();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    /**
     * Метод, возвращающий текущую коллекцию драконов в формате HashSet
     *
     * @return HashSet драконов, находящихся в коллекции
     */
    public HashSet<Dragon> getDragons() {
        readLock.lock();
        try {
            return dragons;
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Метод, добавляющий полученного дракона в коллекцию
     *
     * @param dragon дракон, которого нужно добавить в коллекцию
     */
    public void addDragon(Dragon dragon) {
        writeLock.lock();
        dragons.add(dragon);
        writeLock.unlock();
    }

    /**
     * Метод, очищающий текущую коллекцию
     */
    public void clear(String username) {
        readLock.lock();
        dragons.removeIf(dragon -> dragon.getAuthorName().equals(username));
        readLock.unlock();
    }

    /**
     * Метод, удаляющий дракона из коллекции по полученному ID, если таковой существует
     *
     * @param id id дракона, которого нужно удалить
     */
    public String removeById(long id) {
        writeLock.lock();
        try {
            Dragon dragon = getById(id);
            if (dragon != null) {
                dragons.remove(dragon);
                return TextFormatter.colorMessage("Dragon successfully removed");
            } else {
                return TextFormatter.colorErrorMessage("Dragon with that ID not found");
            }
        } catch (NumberFormatException e) {
            return TextFormatter.colorErrorMessage("ID имеет некорректный формат");
        } finally {
            writeLock.unlock();
        }
    }

    public Dragon getById(Long id) {
        readLock.lock();
        try {
            return dragons.stream().filter(dr -> dr.getId().equals(id)).findAny().orElse(null);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Метод, выводящий пользователю информацию о коллекции
     */
    public String showInfo() {
        readLock.lock();
        try {
            return TextFormatter.colorInfoMessage("Information about collection: ")
                    + TextFormatter.colorMessage("Collection type: " + dragons.getClass()
                    + " initialization date: " + creationDate
                    + " count of dragons: " + dragons.size());
        } finally {
            readLock.unlock();
        }
    }

    public Dragon getMaxByCave() {
        readLock.lock();
        try {
            return dragons.stream().max(Dragon::compareByCave).get();
        } finally {
            readLock.unlock();
        }
    }

    public Dragon getMax() {
        readLock.lock();
        try {
            return dragons.stream().max(Dragon::compareTo).get();
        } finally {
            readLock.unlock();
        }
    }

    public Dragon getMin() {
        readLock.lock();
        try {
            return dragons.stream().min(Dragon::compareTo).get();
        } finally {
            readLock.unlock();
        }
    }
}
