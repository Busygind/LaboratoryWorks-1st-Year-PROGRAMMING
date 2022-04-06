package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;

import java.io.Serializable;

public abstract class CommandAbstract implements Serializable {
    private final String name;
    private final String description;
    private final int countOfArgs;

    public CommandAbstract(String name, String description, int countOfArgs) {
        this.name = name;
        this.description = description;
        this.countOfArgs = countOfArgs;
    }

    public abstract Object execute(CollectionManager manager);

    public int getCountOfArgs() {
        return countOfArgs;
    }

    public String getDescription() {
        return name + " : " + description;
    }

    public String getName() {
        return name;
    }
}
