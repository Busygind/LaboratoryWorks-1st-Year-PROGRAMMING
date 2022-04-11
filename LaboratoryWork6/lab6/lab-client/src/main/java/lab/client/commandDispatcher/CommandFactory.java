package lab.client.commandDispatcher;

import lab.client.exceptions.CommandNotFoundException;
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
import lab.common.util.handlers.TextFormatter;

import java.util.List;

public class CommandFactory {

    private final ArgumentsListener listener = new ArgumentsListener();

    public CommandAbstract createCommand(String name, List<String> args) throws CommandNotFoundException {
        Dragon dragon;
        try {
            switch (name) {
                case "add":
                    if (args.size() != 3) {
                        return null;
                    }
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    return new AddCommand(dragon);
                case "add_if_max":
                    if (args.size() != 3) {
                        return null;
                    }
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    return new AddIfMaxCommand(dragon);
                case "add_if_min":
                    if (args.size() != 3) {
                        return null;
                    }
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    return new AddIfMinCommand(dragon);
                case "clear":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new ClearCommand();
                case "exit":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new ExitCommand();
                case "help":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new HelpCommand();
                case "history":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new HistoryCommand();
                case "info":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new InfoCommand();
                case "max_by_cave":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new MaxByCaveCommand();
                case "print_ascending":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new PrintAscendingCommand();
                case "print_descending":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new PrintDescendingCommand();
                case "remove_by_id":
                    if (args.size() != 1) {
                        return null;
                    }
                    long id = Long.parseLong(args.get(0));
                    return new RemoveByIdCommand(id);
                case "show":
                    if (args.size() != 0) {
                        return null;
                    }
                    return new ShowCommand();
                case "update_by_id":
                    if (args.size() != 1) {
                        return null;
                    }
                    long idOfDragon = Long.parseLong(args.get(0));
                    dragon = listener.inputDragonWithPrimitives();
                    return new UpdateByIdCommand(idOfDragon, dragon);
                default:
                    throw new CommandNotFoundException("Command with current name not found");
            }
        } catch (IndexOutOfBoundsException e) {
            TextFormatter.printErrorMessage("Incorrect count of args");
            return null;
        }
    }
}
