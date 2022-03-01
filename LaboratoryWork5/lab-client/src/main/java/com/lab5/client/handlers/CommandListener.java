package com.lab5.client.handlers;

import com.lab5.client.entities.CollectionOfDragons;
import com.lab5.client.entities.Dragon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;
import java.util.NoSuchElementException;


/**
 * Класс, содержащий методы, вызываемые напрямую после соответствующих команд пользователя,
 * а также методы по обработке полученных данных
 */
public class CommandListener {

    /**
     * Словарь, сопоставляющий доступные команды с соответствующими методами
     */
    private static Map<String, Method> commands = new HashMap<>();
    /**
     * Список, сохраняющий данные о последних командах пользователя
     */
    private static List<String> commandHistory = new ArrayList<>();
    /**
     * Объект класса, управляющего считыванием примитивов во время заполнения
     * полей нового элемента коллекции
     */
    private ArgumentsListener argumentsListener = new ArgumentsListener();
    /**
     * Объект коллекции, с полем dragons которого производятся действия
     */
    private CollectionOfDragons collection;

    /**
     * Конструктор объекта данного класса
     * @param collection коллекция, с которой работает пользователь
     */
    public CommandListener(CollectionOfDragons collection) {
        this.collection = collection;
        for (Method method : CommandListener.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                commands.put(command.name(), method);
            }
        }
    }

    /**
     * Метод, вызываемый командой <strong>help</strong>
     */
    @Command(name = "help",
            args = "",
            countOfArgs = 0,
            desc = "Доступные пользователю команды",
            aliases = {})
    private void help() {
        StringBuilder sb = new StringBuilder("Список команд: \n");
        for (Method m : this.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Command.class)) {
                Command com = m.getAnnotation(Command.class);
                sb.append(com.name()).append(" ")
                        .append(com.args()).append(" - ")
                        .append(com.desc()).append("\n");
            }
        }
        System.out.println(sb);
    }

    /**
     * Метод, вызываемый командой <strong>info</strong>
     */
    @Command(name = "info",
            args = "",
            countOfArgs = 0,
            desc = "Вывести информацию о коллекции",
            aliases = {})
    private void info() {
        System.out.println("Information about collection: ");
        collection.showInfo();
    }

    /**
     * Метод, вызываемый командой <strong>show</strong>
     */
    @Command(name = "show",
            args = "",
            countOfArgs = 0,
            desc = "Показать всех драконов в коллекции",
            aliases = {})
    private void show() {
        System.out.println(collection.getDragons());
    }

    /**
     * Метод, вызываемый командой <strong>add</strong>
     *
     * @param dragonName имя дракона, которого добавляет пользователь
     * @param age возраст дракона, которого добавляет пользователь
     * @param wingspan размах крыльев дракона, которого добавляет пользователь
     */
    @Command(name = "add",
            args = "{name age wingspan}",
            countOfArgs = Dragon.COUNT_OF_PRIMITIVE_ARGS,
            desc = "Добавить элемент в коллекцию",
            aliases = {})
    private void add(String dragonName, String age, String wingspan) {
        String name = dragonName.substring(0, 1).toUpperCase() + dragonName.substring(1); //Делаем имя с большой буквы
        Dragon dragon = new Dragon();
        try {
            dragon.setAge(Integer.parseInt(age));
            dragon.setWingspan(Integer.parseInt(wingspan));
        } catch (NumberFormatException e) {
            System.out.println("Аргументы имеют неверный формат");
            return;
        }
        dragon.setName(name);
        dragon.setCoordinates(argumentsListener.inputCoordinates());
        argumentsListener.inputColor(dragon);
        argumentsListener.inputCharacter(dragon);
        dragon.setCave(argumentsListener.inputCave());
        collection.addDragon(dragon);
    }

    /**
     * Метод, вызываемый командой <strong>update</strong>
     *
     * @param id id дракона, данные о котором необходимо обновить
     */
    @Command(name = "update",
            args = "{id}",
            countOfArgs = 1,
            desc = "Обновить данные о элементе коллекции по данному id",
            aliases = {})
    private void update(String id) {
        long newId = Long.parseLong(id);
        for (Dragon elem : collection.getDragons()) {
            if (elem.getId() == newId) {
                System.out.println("Введите информацию о драконе: {name, age, wingspan}");
                Scanner sc = new Scanner(System.in);
                argumentsListener.inputPrimitives(elem);
                elem.setCoordinates(argumentsListener.inputCoordinates());
                argumentsListener.inputColor(elem);
                argumentsListener.inputCharacter(elem);
                elem.setCave(argumentsListener.inputCave());
                System.out.println("Данные о драконе успешно обновлены");
            }
        }
    }

    /**
     * Метод, вызываемый командой <strong>remove_by_id</strong>
     *
     * @param id id дракона, которого необходимо удалить из коллекции
     */
    @Command(name = "remove_by_id",
            args = "{id}",
            countOfArgs = 1,
            desc = "Удалить элемент из коллекции по его ID",
            aliases = {})
    private void removeById(String id) {
        collection.removeById(Long.parseLong(id));
    }

    /**
     * Метод, вызываемый командой <strong>clear</strong>
     */
    @Command(name = "clear",
            args = "",
            countOfArgs = 0,
            desc = "Очищение коллекции",
            aliases = {})
    private void clear() {
        collection.clear();
        if (collection.getDragons().isEmpty()) {
            System.out.println("Collection successful cleared");
        } else {
            System.out.println("Something went wrong, try again.");
        }
    }

     /**
      * Метод, вызываемый командой <strong>save</strong>
      */
     @Command(name = "save",
            args = "",
            countOfArgs = 0,
            desc = "Сохранение коллекции в файл",
            aliases = {})
    private void save() throws IOException {
        XMLWriter writer = new XMLWriter();
        writer.write(collection.getOutFile(), collection);
        System.out.println("Коллекция успешно сохранена");
    }

    /**
     * Метод, вызываемый командой <strong>execute_script</strong>
     *
     * @param filename имя файла, скрипт из которого необходимо выполнить
     */
    @Command(name = "execute_script",
            args = "{filename}",
            countOfArgs = 1,
            desc = "Считать и исполнить скрипт из указанного файла",
            aliases = {})
    private void executeScript(String filename) {
        try {
            File starting = new File(System.getProperty("user.dir")); // Get current user directory
            File file = new File(starting, filename); // Initialize file from cmd
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                if (!("execute_script " + filename).equals(nextLine)) {
                    ArrayList<String> line = LineSplitter.smartSplit(nextLine);
                    invokeMethod(getCommandName(line), getCommandArguments(line));
                } else {
                    System.out.println("Ошибка выполнения. Скрипт вызывает сам себя.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла с таким именем в текущей папке нет. Переместите файл и повторите попытку");
        }
    }

    /**
     * Метод, вызываемый командой <strong>exit</strong>
     *
     * @throws IOException может возникнуть при неполадках с сохранением данных в файл
     */
    @Command(name = "exit",
            args = "",
            countOfArgs = 0,
            desc = "Выход из программы без сохранения",
            aliases = {})
    private void exit() throws IOException {
        System.out.println("Сохранить коллекцию в файл? y/n");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals("y")) {
            save();
        }
        System.exit(0);
    }

    /**
     * Метод, вызываемый командой <strong>add_if_max</strong>
     *
     * @param name имя дракона, которого пытается добавить пользователь
     * @param age возраст дракона, которого пытается добавить пользователь
     * @param wingspan размах крыльев дракона, которого пытается добавить пользователь
     */
    @Command(name = "add_if_max",
            args = "{name, age, wingspan}",
            countOfArgs = Dragon.COUNT_OF_PRIMITIVE_ARGS,
            desc = "Добавить дракона в коллекцию, если его возраст больше, чем у самого старшего в коллекции",
            aliases = {})
    private void addIfMax(String name, String age, String wingspan) {
        int maxAge = 0;
        for (Dragon dragon : collection.getDragons()) {
            if (dragon.getAge() > maxAge) {
                maxAge = dragon.getAge();
            }
        }
        if (Integer.parseInt(age) > maxAge) {
            add(name, age, wingspan);
        } else {
            System.out.println("В коллекции есть дракон постарше!");
        }
    }

    /**
     * Метод, вызываемый командой <strong>add_if_min</strong>
     *
     * @param name имя дракона, которого пытается добавить пользователь
     * @param age возраст дракона, которого пытается добавить пользователь
     * @param wingspan размах крыльев дракона, которого пытается добавить пользователь
     */
    @Command(name = "add_if_min",
            args = "{name, age, wingspan}",
            countOfArgs = Dragon.COUNT_OF_PRIMITIVE_ARGS,
            desc = "Добавить дракона в коллекцию, если его возраст меньше, чем у самого младшего в коллекции",
            aliases = {})
    private void addIfMin(String name, String age, String wingspan) {
        int minAge = Integer.MAX_VALUE;
        for (Dragon dragon : collection.getDragons()) {
            if (dragon.getAge() < minAge) {
                minAge = dragon.getAge();
            }
        }
        if (Integer.parseInt(age) < minAge) {
            add(name, age, wingspan);
        } else {
            System.out.println("В коллекции есть дракон помладше!");
        }
    }

    /**
     * Метод, вызываемый командой <strong>history</strong>
     */
    @Command(name = "history",
            args = "",
            countOfArgs = 0,
            desc = "Вывести последние 11 команд (без их аргументов)",
            aliases = {})
    private void showHistory() {
        final int countOfWatchableCommands = 11;
        if (commandHistory.size() > countOfWatchableCommands) {
            System.out.println(commandHistory.subList(commandHistory.size() - countOfWatchableCommands, commandHistory.size()));
        }
        System.out.println(commandHistory);
    }

    /**
     * Метод, вызываемый командой <strong>max_by_cave</strong>
     */
    @Command(name = "max_by_cave",
            args = "",
            countOfArgs = 0,
            desc = "Вывести любого дракона из коллекции, глубина пещеры которого является максимальной",
            aliases = {})
    private void showMaxByCave() {
        double maxDepth = Double.MIN_VALUE;
        Dragon dragonWithDeepestCave = new Dragon();
        for (Dragon dragon : collection.getDragons()) {
            if (dragon.getCave().getDepth() > maxDepth) {
                maxDepth = dragon.getCave().getDepth();
                dragonWithDeepestCave = dragon;
            }
        }
        System.out.println("Данные о драконе с самой глубокой пещерой:\n" + dragonWithDeepestCave);
    }

    /**
     * Метод, вызываемый командой <strong>print_ascending</strong>
     */
    @Command(name = "print_ascending",
            args = "",
            countOfArgs = 0,
            desc = "Вывести драконов коллекции от младшего к старшему",
            aliases = {})
    private void printAscending() {
        List<Dragon> dragons = new ArrayList<>(collection.getDragons());
        Collections.sort(dragons);
        System.out.println(dragons);
    }

    /**
     * Метод, вызываемый командой <strong>print_descending</strong>
     */
    @Command(name = "print_descending",
            args = "",
            countOfArgs = 0,
            desc = "Вывести драконов коллекции от старшего к младшему",
            aliases = {})
    private void printDescending() {
        List<Dragon> dragons = new ArrayList<>(collection.getDragons());
        dragons.sort(Collections.reverseOrder());
        System.out.println(dragons);
    }

    /**
     * Метод, циклически считывающий команды из консоли и вызывающий необходимые методы обработки коллекции
     */
    public void commandsReader() {
        while (true) { // цикл завершится только при вызове команды exit или вводе ctrl+d
            try {
                ArrayList<String> line = readCommandFromSystemIn();
                invokeMethod(getCommandName(line), getCommandArguments(line));
            } catch (NoSuchElementException e) {
                System.out.println("Введена команда прерывания работы приложения. Работа завершена");
                System.exit(0);
            }
        }
    }

    /**
     * Метод, вызывающий необходимую команду по ее имени и аргументам
     *
     * @param commandName название вызываемой команды
     * @param commandArgs аргументы вызываемой команды
     */
    protected void invokeMethod(String commandName, ArrayList<String> commandArgs) {
        Method method = commands.get(commandName);
        commandHistory.add(commandName);
        try {
            Command command = method.getAnnotation(Command.class);
            if (commandArgs.size() != command.countOfArgs()) {
                System.out.println("Неверное количество аргументов. Необходимо: " + command.countOfArgs());
            } else {
                method.invoke(this, commandArgs.toArray());
            }
        } catch (NullPointerException | IllegalAccessException e) {
            System.out.println("Команда некорректна или пуста, попробуйте еще раз");
        } catch (InvocationTargetException e) {
            System.out.println();
        }
    }

    /**
     * Метод, считывающий команду из консоли и разделяющий ее на имя и аргументы
     *
     * @return разделенная строка с составляющими частями команды
     */
    protected static ArrayList<String> readCommandFromSystemIn() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().toLowerCase();
        return LineSplitter.smartSplit(line);
    }

    /**
     * Метод, извлекающий из полученного массива строк данные, которые являются аргументами
     *
     * @param line разделенная строка
     * @return массив аргументов
     */
    protected static ArrayList<String> getCommandArguments(ArrayList<String> line) {
        line.remove(0);
        return line;
    }

    /**
     * Метод, извлекающий из полученного массива строк имя команды
     *
     * @param line разделенная строка
     * @return имя команды
     */
    protected static String getCommandName(ArrayList<String> line) {
        return line.get(0);
    }
}
