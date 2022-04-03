package lab.client;

import lab.common.util.commands.CommandAbstract;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, содержащий методы, вызываемые напрямую после соответствующих команд пользователя,
 * а также методы по обработке полученных данных
 */
public class CommandHandler {
    /**
     * Метод, циклически считывающий команды из консоли и вызывающий необходимые методы обработки коллекции
     */
    public static CommandAbstract initCommand(String line) throws IOException {
        ArrayList<String> commandWithArgs = LineSplitter.smartSplit(line);
        String commandName = CommandHandler.getCommandName(commandWithArgs);
        ArrayList<String> commandArgs = CommandHandler.getCommandArguments(commandWithArgs);
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
