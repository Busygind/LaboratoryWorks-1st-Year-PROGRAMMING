package lab.client.handlers;

import lab.client.entities.Coordinates;
import lab.client.entities.Dragon;
import lab.client.entities.DragonCave;
import lab.client.enums.DragonCharacter;
import lab.client.enums.Color;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс, отвечающий за работу с пользователем во время
 * ввода данных о новом элементе коллекции
 */
public class ArgumentsListener {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Метод обработки и инициализации данных примитивных типов для переданного
     * в аргументе дракона
     *
     * @param dragon дракон, характеристики примитивных типов которого вводит пользователь
     */
    public void inputPrimitives(Dragon dragon) {
        String[] inputArray = scanner.nextLine().split(" ");
        try {
            dragon.setName(inputArray[0]);
            dragon.setAge(Integer.parseInt(inputArray[1]));
            dragon.setWingspan(Integer.parseInt(inputArray[2]));
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage("Введены некорректные данные, верный формат: name age[>0] wingspan[>0]");
            inputPrimitives(dragon);
        }
    }

    /**
     * Метод обработки и инициализации координат (не устанавливает данные
     * в поля объекта коллекции)
     *
     * @return объект координат, данные которых ввёл пользователь
     */
    public Coordinates inputCoordinates() {
        TextFormatter.printInfoMessage("Введите координаты:");
        Coordinates newCoordinates = new Coordinates();
        inputX(newCoordinates);
        inputY(newCoordinates);
        return newCoordinates;
    }

    /**
     * Метод обработки и инициализации координаты Х и присваивание ее объекту координат
     *
     * @param coordinates объект координат, х которых вводит пользователь
     */
    private void inputX(Coordinates coordinates) {
        TextFormatter.printInfoMessage("Введите координату x (целое число): ");
        try {
            int x = Integer.parseInt(scanner.nextLine());
            coordinates.setX(x);
            boolean correctField = DragonValidator.validateField(coordinates, "x");
            if (!correctField) {
                inputX(coordinates);
            }
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Число имеет неверный формат");
            inputX(coordinates);
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage("Число не входит в допустимый диапазон");
            inputX(coordinates);
        }
    }

    /**
     * Метод обработки и инициализации координаты У и присваивание ее объекту координат
     *
     * @param coordinates объект координат, у которых вводит пользователь
     */
    private void inputY(Coordinates coordinates) {
        TextFormatter.printInfoMessage("Введите Y(число с плавающей точкой): ");
        try {
            float y = Float.parseFloat(scanner.nextLine());
            coordinates.setY(y);
            boolean correctField = DragonValidator.validateField(coordinates, "y");
            if (!correctField) {
                inputY(coordinates);
            }
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Ошибка ввода");
            inputY(coordinates);
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage(e.getMessage());
            inputY(coordinates);
        }
    }

    /**
     * Метод обработки и инициализации пещеры (не устанавливает данные
     * в поля объекта коллекции)
     *
     * @return пещера, данные о которой ввёл пользователь
     */
    public DragonCave inputCave() {
        TextFormatter.printInfoMessage("Введите данные о пещере:");
        DragonCave cave = new DragonCave();
        inputDepth(cave);
        inputNumOfTreasures(cave);
        return cave;
    }

    /**
     * Метод обработки и инициализации глубины пещеры и присваивание ее объекту пещеры
     *
     * @param cave пещера, глубина которой запрашивается у пользователя
     */
    private void inputDepth(DragonCave cave) {
        TextFormatter.printInfoMessage("Введите глубину пещеры (число с плавающей точкой): ");
        try {
            cave.setDepth(Double.parseDouble(scanner.nextLine()));
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Ошибка ввода");
            inputDepth(cave);
        }
    }

    /**
     * Метод обработки и инициализации количества сокровищ в пещере и присваивание этого количества объекту пещеры
     *
     * @param cave пещера, количество сокровищ в которой запрашивается у пользователя
     */
    private void inputNumOfTreasures(DragonCave cave) {
        TextFormatter.printInfoMessage("Введите количество сокровищ (целое число, большее 0): ");
        try {
            cave.setNumberOfTreasures(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            TextFormatter.printErrorMessage("Ошибка ввода");
            inputNumOfTreasures(cave);
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage(e.getMessage());
            inputNumOfTreasures(cave);
        }
    }

    /**
     * Метод обработки цвета дракона, полученного от пользователя
     *
     * @param dragon дракон, цвет которого запрашивается у пользователя
     */
    public void inputColor(Dragon dragon) {
        TextFormatter.printInfoMessage("Введите цвет дракона, доступные цвета: " + Arrays.toString(Color.values()) + ", если у дракона нет цвета, нажмите Enter: ");
        try {
            String color = scanner.nextLine().toUpperCase();
            if ("".equals(color)) {
                dragon.setColor(null);
            } else {
                dragon.setColor(Color.valueOf(color));
            }
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage("Ошибка ввода, такого цвета не существует");
            inputColor(dragon);
        }
    }

    /**
     * Метод обработки характера дракона, полученного от пользователя
     *
     * @param dragon дракон, характер которого запрашивается у пользователя
     */
    public void inputCharacter(Dragon dragon) {
        TextFormatter.printInfoMessage("Введите настроение дракона, доступные настроения: " + Arrays.toString(DragonCharacter.values()) + ": ");
        try {
            dragon.setCharacter(DragonCharacter.valueOf(scanner.nextLine().toUpperCase()));
        } catch (IllegalArgumentException e) {
            TextFormatter.printErrorMessage("Ошибка ввода, такого настроения не существует");
            inputCharacter(dragon);
        }
    }
}
