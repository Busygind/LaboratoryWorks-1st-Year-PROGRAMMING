package com.lab5.client.handlers;

import com.lab5.client.entities.Coordinates;
import com.lab5.client.entities.Dragon;
import com.lab5.client.entities.DragonCave;
import com.lab5.client.enums.DragonCharacter;
import com.lab5.client.enums.Color;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс, отвечающий за работу с пользователем во время
 * ввода данных о новом элементе коллекции
 */
public class ArgumentsListener {
    /**
     * Метод обработки и инициализации данных примитивных типов для переданного
     * в аргументе дракона
     *
     * @param dragon дракон, характеристики примитивных типов которого вводит пользователь
     */
    protected void inputPrimitives(Dragon dragon) {
        Scanner scanner = new Scanner(System.in);
        String[] inputArray = scanner.nextLine().split(" ");
        try {
            dragon.setName(inputArray[0]);
            dragon.setAge(Integer.parseInt(inputArray[1]));
            dragon.setWingspan(Integer.parseInt(inputArray[2]));
        } catch (IllegalArgumentException e) {
            System.out.println("Введены некорректные данные, верный формат: name age[>0] wingspan[>0]");
            inputPrimitives(dragon);
        }
    }

    /**
     * Метод обработки и инициализации координат (не устанавливает данные
     * в поля объекта коллекции)
     *
     * @return объект координат, данные которых ввёл пользователь
     */
    protected Coordinates inputCoordinates() {
        System.out.println("Введите координаты:");
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите координату x (целое число): ");
        try {
            coordinates.setX(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Число имеет неверный формат");
            inputX(coordinates);
        } catch (IllegalArgumentException e) {
            System.out.println("Число не входит в допустимый диапазон");
            inputX(coordinates);
        }
    }

    /**
     * Метод обработки и инициализации координаты У и присваивание ее объекту координат
     *
     * @param coordinates объект координат, у которых вводит пользователь
     */
    private void inputY(Coordinates coordinates) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите Y(число с плавающей точкой): ");
        try {
            coordinates.setY(Float.parseFloat(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
            inputY(coordinates);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputY(coordinates);
        }
    }

    /**
     * Метод обработки и инициализации пещеры (не устанавливает данные
     * в поля объекта коллекции)
     *
     * @return пещера, данные о которой ввёл пользователь
     */
    protected DragonCave inputCave() {
        System.out.println("Введите данные о пещере:");
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите глубину пещеры (число с плавающей точкой): ");
        try {
            cave.setDepth(Double.parseDouble(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
            inputDepth(cave);
        }
    }

    /**
     * Метод обработки и инициализации количества сокровищ в пещере и присваивание этого количества объекту пещеры
     *
     * @param cave пещера, количество сокровищ в которой запрашивается у пользователя
     */
    private void inputNumOfTreasures(DragonCave cave) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество сокровищ (целое число, большее 0): ");
        try {
            cave.setNumberOfTreasures(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
            inputNumOfTreasures(cave);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputNumOfTreasures(cave);
        }
    }

    /**
     * Метод обработки цвета дракона, полученного от пользователя
     *
     * @param dragon дракон, цвет которого запрашивается у пользователя
     */
    protected void inputColor(Dragon dragon) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите цвет дракона, доступные цвета: " + Arrays.toString(Color.values()) + ", если у дракона нет цвета, нажмите Enter: ");
        String inputString = scanner.nextLine().toUpperCase();
        if ("".equals(inputString)) {
            dragon.setColor(null);
        } else {
            try {
                dragon.setColor(Color.valueOf(inputString));
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода, такого цвета не существует");
                inputColor(dragon);
            }
        }
    }

    /**
     * Метод обработки характера дракона, полученного от пользователя
     *
     * @param dragon дракон, характер которого запрашивается у пользователя
     */
    protected void inputCharacter(Dragon dragon) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите настроение дракона, доступные настроения: " + Arrays.toString(DragonCharacter.values()) + ": ");
        try {
            dragon.setCharacter(DragonCharacter.valueOf(scanner.nextLine().toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода, такого настроения не существует");
            inputCharacter(dragon);
        }
    }
}
