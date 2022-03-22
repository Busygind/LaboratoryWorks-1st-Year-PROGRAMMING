package lab.common.util.handlers;

import lab.common.util.commands.CommandAbstract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Класс, содержащий методы, вызываемые напрямую после соответствующих команд пользователя,
 * а также методы по обработке полученных данных
 */
public class CommandListener {
    private final InputStream inputStream;

    public CommandListener(InputStream stream) {
        this.inputStream = stream;
    }
    /**
     * Метод, циклически считывающий команды из консоли и вызывающий необходимые методы обработки коллекции
     */
    public static CommandAbstract initCommand(String line) throws IOException {
        ArrayList<String> commandWithArgs = LineSplitter.smartSplit(line);
        String commandName = CommandListener.getCommandName(commandWithArgs);
        ArrayList<String> commandArgs = CommandListener.getCommandArguments(commandWithArgs);
        CommandFactory factory = new CommandFactory();
        return factory.createCommand(commandName, commandArgs);
    }

    /**
     * Метод, извлекающий из полученного массива аргументов данные, которые являются аргументами
     *
     * @param line разделенная строка
     * @return массив аргументов
     */
    public static ArrayList<String> getCommandArguments(ArrayList<String> line) {
        line.remove(0);
        return line;
    }

    /**
     * Метод, извлекающий из полученного массива строк имя команды
     *
     * @param line разделенная строка
     * @return имя команды
     */
    public static String getCommandName(ArrayList<String> line) {
        return line.get(0);
    }

}
