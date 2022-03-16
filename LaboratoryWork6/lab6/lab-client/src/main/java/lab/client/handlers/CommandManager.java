package lab.client.handlers;


import lab.client.commands.*;
import lab.client.entities.CollectionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CommandManager {
    /**
     * Список, сохраняющий данные о последних командах пользователя
     */
    private static final List<String> COMMANDS_HISTORY = new ArrayList<>();
    /**
     * Словарь, сопоставляющий доступные команды с соответствующими командами
     */
    private static final Map<String, CommandAbstract> COMMANDS = new HashMap<>();

    /**
     * Конструктор объекта данного класса
     *
     * @param collection коллекция, с которой работает пользователь
     */
    private CommandManager() {
        //never used
    }

    /**
     * Метод, вызывающий необходимую команду по ее имени и аргументам
     *
     * @param commandName название вызываемой команды
     * @param commandArgs аргументы вызываемой команды
     */
    public static void invokeCommand(String commandName, ArrayList<String> commandArgs) {
        CommandAbstract command = COMMANDS.get(commandName);
        COMMANDS_HISTORY.add(commandName);
        try {

            if (commandArgs.size() != command.getCountOfArgs()) {
                TextFormatter.printErrorMessage("Неверное количество аргументов. Необходимо: " + command.getCountOfArgs());
            } else {
                command.execute(commandArgs);
            }
        } catch (NullPointerException e) {
            TextFormatter.printErrorMessage("Команда некорректна или пуста, попробуйте еще раз");
        }
    }

    public static void initCommands(CollectionManager collection) {
        COMMANDS.put("add", new AddCommand(collection));
        COMMANDS.put("clear", new ClearCommand(collection));
        COMMANDS.put("execute_script", new ExecuteScriptCommand());
        COMMANDS.put("exit", new ExitCommand());
        COMMANDS.put("help", new HelpCommand());
        COMMANDS.put("history", new HistoryCommand());
        COMMANDS.put("info", new InfoCommand(collection));
        COMMANDS.put("max_by_cave", new MaxByCaveCommand(collection));
        COMMANDS.put("print_ascending", new PrintAscendingCommand(collection));
        COMMANDS.put("print_descending", new PrintDescendingCommand(collection));
        COMMANDS.put("add_if_max", new AddIfMaxCommand(collection));
        COMMANDS.put("add_if_min", new AddIfMinCommand(collection));
        COMMANDS.put("remove_by_id", new RemoveByIdCommand(collection));
        COMMANDS.put("save", new SaveCommand(collection));
        COMMANDS.put("show", new ShowCommand(collection));
        COMMANDS.put("update_by_id", new UpdateByIdCommand(collection));
        //todo add_if_max и add_if_min
    }

    public static List<String> getCommandsHistory() {
        return COMMANDS_HISTORY;
    }

    public static Map<String, CommandAbstract> getCommands() {
        return COMMANDS;
    }
}
