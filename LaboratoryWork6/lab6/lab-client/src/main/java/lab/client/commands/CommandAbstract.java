package lab.client.commands;

import java.util.ArrayList;

public abstract class CommandAbstract {
    private final String name;
    private final String description;
    private final int countOfArgs;

    public CommandAbstract(String name, String description, int countOfArgs) {
        this.name = name;
        this.description = description;
        this.countOfArgs = countOfArgs;
    }

    public abstract boolean execute(ArrayList<String> args);

    public int getCountOfArgs() {
        return countOfArgs;
    }

    public String getDescription() {
        return name + " : " + description;
    }
}
