package lab.common.util.commands;

import lab.common.util.entities.CollectionManager;

import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends CommandAbstract {

    /**
     * Словарь, сопоставляющий доступные команды с соответствующими командами
     */
    private static final Map<String, String> COMMANDS = new HashMap<>();

    public HelpCommand() {
        super("help", "Вывести список доступных команд", 0);
    }

    @Override
    public String execute(CollectionManager manager) {
        fillCommandsForBuildList();
        return getCommands();
    }

    private static void fillCommandsForBuildList() {
        COMMANDS.put("add [name, age, wingspan]", "add new dragon");
        COMMANDS.put("add_if_max [name, age, wingspan]", "add new dragon if him age is max");
        COMMANDS.put("add_if_min [name, age, wingspan]", "add new dragon if him age is min");
        COMMANDS.put("clear", "clear collection");
        COMMANDS.put("help", "get list of available commands");
        COMMANDS.put("history", "get 11 last commands");
        COMMANDS.put("info", "get info about collection");
        COMMANDS.put("max_by_cave", "get dragon with the deepest cave");
        COMMANDS.put("print_ascending", "show dragons from youngest to the oldest");
        COMMANDS.put("print_descending", "show dragons from oldest to the youngest");
        COMMANDS.put("remove_by_id [id]", "remove dragon with given ID");
        COMMANDS.put("show", "show all dragons in collection");
        COMMANDS.put("update_by_id [id]", "update info about dragon with given ID");
    }

    private static String getCommands() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> pair : COMMANDS.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue();
            sb.append(key).append(" - ").append(value).append("\n");
        }
        return sb.toString();
    }

}
