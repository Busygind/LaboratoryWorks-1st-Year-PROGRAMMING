package lab.common.util.handlers;

import lab.common.util.commands.AddCommand;
import lab.common.util.commands.AddIfMaxCommand;
import lab.common.util.commands.AddIfMinCommand;
import lab.common.util.commands.ClearCommand;
import lab.common.util.commands.CommandAbstract;
import lab.common.util.commands.ExecuteScriptCommand;
import lab.common.util.commands.ExitCommand;
import lab.common.util.commands.HelpCommand;
import lab.common.util.commands.HistoryCommand;
import lab.common.util.commands.InfoCommand;
import lab.common.util.commands.MaxByCaveCommand;
import lab.common.util.commands.PrintAscendingCommand;
import lab.common.util.commands.PrintDescendingCommand;
import lab.common.util.commands.RemoveByIdCommand;
import lab.common.util.commands.SaveCommand;
import lab.common.util.commands.ShowCommand;
import lab.common.util.commands.UpdateByIdCommand;
import lab.common.util.entities.CollectionManager;

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
     *
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
    }

    public static List<String> getCommandsHistory() {
        return COMMANDS_HISTORY;
    }

    public static Map<String, CommandAbstract> getCommands() {
        return COMMANDS;
    }
}
