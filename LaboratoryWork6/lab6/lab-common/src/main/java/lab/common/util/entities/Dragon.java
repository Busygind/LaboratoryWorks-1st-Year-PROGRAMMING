package lab.common.util.entities;

import lab.common.util.enums.Color;
import lab.common.util.enums.DragonCharacter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Класс объектов, хранимых в коллекции, которой управляет программа
 */
public class Dragon implements Comparable<Dragon>, Serializable {

    /**
     * Количество полей примитивного типа, которые необходимо передавать при инициализации
     * нового дракона в одной строчке с используемой командой
     */
    public static final int COUNT_OF_PRIMITIVE_ARGS = 3;
    /**
     * Счетчик id элементов, служит для обеспечения уникальности поля id у каждого элемента
     */
    private static long idCounter = 1;
    /**
     * id текущего элемента коллекции
     * <strong>(Значение поля должно быть больше 0, значение этого поля должно быть уникальным,
     * значение этого поля должно генерироваться автоматически)</strong>
     */
    @Min(1)
    private long id;
    /**
     * Имя текущего элемента коллекции
     * <strong>(Поле не может быть null, строка не может быть пустой)</strong>
     */
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")
    private String name;
    /**
     * Координаты текущего элемента коллекции
     * <strong>(Поле не может быть null)</strong>
     */
    @Valid
    @NotNull
    private Coordinates coordinates;

    private Date creationDate;
    /**
     * Возраст текущего дракона
     * <strong>(Значение поля должно быть больше 0)</strong>
     */
    @Min(1)
    private int age;
    /**
     * Размах крыльев текущего дракона
     * <strong>(Значение поля должно быть больше 0)</strong>
     */
    @Min(1)
    private int wingspan;
    /**
     * Цвет текущего дракона
     * <strong>(Поле может быть null)</strong>
     */
    private Color color;
    /**
     * Характер текущего дракона
     * <strong>(Поле не может быть null)</strong>
     */
    @NotNull
    private DragonCharacter character;
    /**
     * Пещера текущего дракона
     * <strong>(Поле не может быть null)</strong>
     */
    @Valid
    @NotNull
    private DragonCave cave;

    /**
     * Конструктор объекта данного класса
     */
    public Dragon() {
        this.creationDate = new Date();
    }

    /**
     * Метод, устанавливающий id текущему элементу коллекции. Генерация происходит автоматически,
     * аргументы на вход не поступают
     */
    public void setId() {
        this.id = idCounter++;
    }

    /**
     * Метод, возвращающий значение поля id у текущего элемента коллекции
     *
     * @return id дракона
     */
    public Long getId() {
        return id;
    }

    /**
     * Метод, устанавливающий значение поля name у текущего элемента коллекции
     *
     * @param name имя дракокна
     */
    public void setName(String name) {
        if (name == null || " ".equals(name)) {
            throw new IllegalArgumentException("Имя не может быть пустым или null, попробуйте снова");
        }
        this.name = name;
    }

    /**
     * Метод, возвращающий значение поля name у текущего элемента коллекции
     *
     * @return имя дракона
     */
    public String getName() {
        return this.name;
    }

    /**
     * Метод, устанавливающий значение поля coordinates у текущего элемента коллекции
     *
     * @param coordinates координаты дракона
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Не переданы координаты или они некорректны, попробуйте снова");
        }
        this.coordinates = coordinates;
    }

    /**
     * Метод, возвращающий содержимое поля coordinates текущего элемента коллекции
     *
     * @return объект координат
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * Метод, установливающий значение поля age текущему элементу коллекции
     *
     * @param age значение возраста дракона
     */
    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Некорректный возраст дракона, попробуйте снова");
        }
        this.age = age;
    }

    /**
     * Метод, возвращающий значение поля age текущего элемента коллекции
     *
     * @return значение возраста дракона
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Метод, устанавливающий значение поля wingspan у текущего элемента коллекции
     *
     * @param wingspan значение размаха крыльев дракона
     */
    public void setWingspan(int wingspan) {
        if (wingspan <= 0) {
            throw new IllegalArgumentException("Некорректный размах крыльев дракона, попробуйте снова");
        }
        this.wingspan = wingspan;
    }

    /**
     * Метод, возвращающий значение поля wingspan у текущего элемента коллекции
     *
     * @return значение размаха крыльев дракона
     */
    public int getWingspan() {
        return this.wingspan;
    }

    /**
     * Метод, устанавливающий значение поля color у текущего элемента коллекции
     *
     * @param color цвет дракона
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Метод, возвращающий значение поля color у текущего элемента коллекции
     *
     * @return цвет дракона
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Метод, устанавливающий значение поля character у текущего элемента коллекции
     *
     * @param character характер дракона
     */
    public void setCharacter(DragonCharacter character) {
        if (character == null) {
            throw new IllegalArgumentException("Не передан характер дракона, попробуйте снова");
        }
        this.character = character;
    }

    /**
     * Метод, возвращающий значение поля character у текущего элемента коллекции
     *
     * @return характер дракона
     */
    public DragonCharacter getCharacter() {
        return this.character;
    }

    /**
     * Метод, устанавливающий значение поля cave текущего элемента коллекции
     *
     * @param cave пещера дракона
     */
    public void setCave(DragonCave cave) {
        if (cave == null) {
            throw new IllegalArgumentException("Не переданы данные о пещере или они некорректны, попробуйте снова");
        }
        this.cave = cave;
    }

    /**
     * Метод, возвращающий объект, находящийся в поле cave у текущего элемента коллекции
     *
     * @return пещера дракона
     */
    public DragonCave getCave() {
        return this.cave;
    }

    /**
     * Метод, устанавливающий значение поля creationDate у текущего элемента коллекции.
     * Значение генерируется автоматически, аргументов нет
     */
    public void setCreationDate() {
        this.creationDate = new Date();
    }

    /**
     * Метод, возвращающий значение поля creationDate у текущего элемента коллекции
     *
     * @return дата создания дракона
     */
    public Date getCreationDate() {
        return creationDate;
    }

    public int compareByCave(Dragon o) {
        if (o == null) {
            return 1;
        }
        return getCave().compareTo(o.getCave());
    }

    public int compareByName(Dragon o) {
        if (o == null) {
            return 1;
        }
        return getName().compareTo(o.getName());
    }

    @Override
    public int compareTo(Dragon o) {
        if (o == null) {
            return 1;
        }
        return Comparator.comparing(Dragon::getAge).thenComparing(Dragon::getName).thenComparing(Dragon::getWingspan).compare(this, o);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Dragon dragon = (Dragon) obj;

        return getName().equals(dragon.getName())
                && age == dragon.age
                && coordinates.equals(dragon.coordinates)
                && wingspan == dragon.wingspan
                && cave.equals(dragon.cave)
                && id == dragon.id
                && color.equals(dragon.color)
                && character.equals(dragon.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, cave, color, character, age, wingspan, creationDate);
    }

    @Override
    public String toString() {
        return "\nDragon #" + id + "\nname: " + name
                + "\ncreationDate: " + getCreationDate()
                + "\nage: " + age + "\nwingspan: " + wingspan
                + "\ncoordinates: " + coordinates.toString() + "\ncolor: " + color
                + "\ncharacter: " + character + "\ncave: " + cave.toString() + "\n========================";
    }
}
