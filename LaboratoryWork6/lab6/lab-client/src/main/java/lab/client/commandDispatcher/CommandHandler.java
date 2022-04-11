package lab.client.commandDispatcher;

import lab.common.util.commands.CommandAbstract;

import java.io.IOException;
import java.util.List;

/**
 * Класс, инициализирующий команду по полученной строке
 */
public class CommandHandler {

    private CommandHandler() {
        //never used
    }

    public static CommandAbstract initCommand(String line) throws IOException {
        List<String> commandWithArgs = LineSplitter.smartSplit(line);
        String commandName = CommandHandler.getCommandName(commandWithArgs);
        List<String> commandArgs = CommandHandler.getCommandArguments(commandWithArgs);
        CommandFactory factory = new CommandFactory();
        return factory.createCommand(commandName, commandArgs);
    }

    /**
     * Метод, извлекающий из полученного массива аргументов данные, которые являются аргументами
     *
     * @param line разделенная строка
     * @return массив аргументов
     */
    public static List<String> getCommandArguments(List<String> line) {
        line.remove(0);
        return line;
    }

    /**
     * Метод, извлекающий из полученного массива строк имя команды
     *
     * @param line разделенная строка
     * @return имя команды
     */
    public static String getCommandName(List<String> line) {
        return line.get(0);
    }
}
