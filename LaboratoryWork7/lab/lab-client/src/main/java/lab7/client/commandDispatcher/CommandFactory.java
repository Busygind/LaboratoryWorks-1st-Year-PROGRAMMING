package lab7.client.commandDispatcher;

import javafx.util.Pair;
import lab7.client.exceptions.CommandNotFoundException;
import lab7.common.util.entities.Dragon;
import lab7.common.util.handlers.TextFormatter;
import lab7.common.util.requestSystem.requests.*;

import java.util.List;

public class CommandFactory {

    private final ArgumentsListener listener = new ArgumentsListener();
    private final Pair<String, String> loginData;

    public CommandFactory(Pair<String, String> loginData) {
        this.loginData = loginData;
    }

    public CommandRequest createCommand(String name, List<String> args) throws CommandNotFoundException {
        Dragon dragon;
        try {
            switch (name) {
                case "add":
                case "add_if_max":
                case "add_if_min":
                    if (args.size() != 3) {
                        return null;
                    }
                    dragon = listener.inputDragon(args.get(0), args.get(1), args.get(2));
                    if (dragon == null) {
                        throw new IllegalArgumentException("Аргументы команды некорректны");
                    }
                    return new CommandRequestWithDragon(name, dragon, loginData);
                case "exit":
                case "help":
                case "history":
                case "clear":
                case "info":
                case "print_ascending":
                case "print_descending":
                case "max_by_cave":
                case "show":
                    //todo реализовать exit
                    if (args.size() != 0) {
                        return null;
                    }
                    return new CommandRequestWithoutArgs(name, loginData);
                case "remove_by_id":
                    if (args.size() != 1) {
                        return null;
                    }
                    long id = Long.parseLong(args.get(0));
                    return new CommandRequestWithId(name, id, loginData);
                case "update_by_id":
                    if (args.size() != 1) {
                        return null;
                    }
                    long idOfDragon = Long.parseLong(args.get(0));
                    dragon = listener.inputDragonWithPrimitives();
                    return new CommandRequestWithDragonAndId(name, dragon, idOfDragon, loginData);
                default:
                    throw new CommandNotFoundException("Command with current name not found");
            }
        } catch (IndexOutOfBoundsException e) {
            TextFormatter.printErrorMessage("Incorrect count of args");
            return null;
        }
    }
}
