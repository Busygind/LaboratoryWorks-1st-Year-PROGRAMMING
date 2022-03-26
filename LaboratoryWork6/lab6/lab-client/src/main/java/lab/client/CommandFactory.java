package lab.client;

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

import java.util.ArrayList;

public class CommandFactory {

    ArgumentsListener listener = new ArgumentsListener();

    public CommandAbstract createCommand(String name, ArrayList<String> args) throws CommandNotFoundException {
        Dragon dragon;
        try {
            switch (name) {
                case "add":
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    return new AddCommand(dragon);
                case "add_if_max":
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    return new AddIfMaxCommand(dragon);
                case "add_if_min":
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    return new AddIfMinCommand(dragon);
                case "clear":
                    return new ClearCommand();
                case "exit":
                    return new ExitCommand();
                case "help":
                    return new HelpCommand();
                case "history":
                    return new HistoryCommand();
                case "info":
                    return new InfoCommand();
                case "max_by_cave":
                    return new MaxByCaveCommand();
                case "print_ascending":
                    return new PrintAscendingCommand();
                case "print_descending":
                    return new PrintDescendingCommand();
                case "remove_by_id":
                    long id = Long.parseLong(args.get(0));
                    return new RemoveByIdCommand(id);
                case "show":
                    return new ShowCommand();
                case "update_by_id":
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
