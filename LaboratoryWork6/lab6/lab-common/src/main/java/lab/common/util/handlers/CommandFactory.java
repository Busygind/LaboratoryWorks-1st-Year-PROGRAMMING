package lab.common.util.handlers;

import lab.common.util.commands.AddCommand;
import lab.common.util.commands.AddIfMaxCommand;
import lab.common.util.commands.AddIfMinCommand;
import lab.common.util.commands.ClearCommand;
import lab.common.util.commands.CommandAbstract;
import lab.common.util.commands.ExitCommand;
import lab.common.util.commands.HelpCommand;
import lab.common.util.commands.HistoryCommand;
import lab.common.util.commands.InfoCommand;
import lab.common.util.commands.MaxByCaveCommand;
import lab.common.util.commands.PrintAscendingCommand;
import lab.common.util.commands.PrintDescendingCommand;
import lab.common.util.commands.RemoveByIdCommand;
import lab.common.util.commands.ShowCommand;
import lab.common.util.commands.UpdateByIdCommand;
import lab.common.util.entities.Dragon;
import lab.common.util.exceptions.CommandNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory {

    ArgumentsListener listener = new ArgumentsListener();
    /**
     * Список, сохраняющий данные о последних командах пользователя
     */
    private static final List<String> COMMANDS_HISTORY = new ArrayList<>();
    /**
     * Словарь, сопоставляющий доступные команды с соответствующими командами
     */
    private static final Map<String, CommandAbstract> COMMANDS = new HashMap<>();

    public CommandAbstract createCommand(String name, ArrayList<String> args) throws CommandNotFoundException {
        Dragon dragon;
        try {
            switch (name) {
                case "add":
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    COMMANDS_HISTORY.add("add");
                    return new AddCommand(dragon);
                case "add_if_max":
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    COMMANDS_HISTORY.add("add_if_max");
                    return new AddIfMaxCommand(dragon);
                case "add_if_min":
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    COMMANDS_HISTORY.add("add_if_min");
                    return new AddIfMinCommand(dragon);
                case "clear":
                    COMMANDS_HISTORY.add("clear");
                    return new ClearCommand();
                case "exit":
                    COMMANDS_HISTORY.add("exit");
                    return new ExitCommand();
                case "help":
                    COMMANDS_HISTORY.add("help");
                    return new HelpCommand();
                case "history":
                    COMMANDS_HISTORY.add("history");
                    return new HistoryCommand();
                case "info":
                    COMMANDS_HISTORY.add("info");
                    return new InfoCommand();
                case "max_by_cave":
                    COMMANDS_HISTORY.add("max_by_cave");
                    return new MaxByCaveCommand();
                case "print_ascending":
                    COMMANDS_HISTORY.add("print_ascending");
                    return new PrintAscendingCommand();
                case "print_descending":
                    COMMANDS_HISTORY.add("print_descending");
                    return new PrintDescendingCommand();
                case "remove_by_id":
                    COMMANDS_HISTORY.add("remove_by_id");
                    long id = Long.parseLong(args.get(0));
                    return new RemoveByIdCommand(id);
                case "show":
                    COMMANDS_HISTORY.add("show");
                    return new ShowCommand();
                case "update_by_id":
                    COMMANDS_HISTORY.add("update_by_id");
                    long idOfDragon = Long.parseLong(args.get(0));
                    dragon = listener.inputDragonWithPrimitives();
                    return new UpdateByIdCommand(idOfDragon, dragon);
                default:
                    COMMANDS_HISTORY.add(name);
                    throw new CommandNotFoundException("Command with current name not found");
            }
        } catch (IndexOutOfBoundsException e) {
            TextFormatter.printErrorMessage("Incorrect count of args");
            return null;
        }
    }

    public static List<String> getCommandsHistory() {
        return COMMANDS_HISTORY;
    }
}
