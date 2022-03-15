package lab.client.commands;

import lab.client.handlers.CommandManager;
import lab.client.handlers.TextFormatter;
import lab.client.handlers.LineSplitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static lab.client.handlers.CommandListener.getCommandArguments;
import static lab.client.handlers.CommandListener.getCommandName;


public class ExecuteScriptCommand extends CommandAbstract {

    private static Set<String> namesOfRanScripts = new HashSet<>();

    public ExecuteScriptCommand() {
        super("execute_script", "Считать и исполнить скрипт из указанного файла", 1);
    }

    @Override
    public boolean execute(ArrayList<String> args) {
        try {
            String filename = args.get(0);
            namesOfRanScripts.add(filename);
            File starting = new File(System.getProperty("user.dir")); // Get current user directory
            File file = new File(starting, filename); // Initialize file from cmd
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                if (!("execute_script " + filename).equals(nextLine)) {
                    ArrayList<String> line = LineSplitter.smartSplit(nextLine);
                    CommandManager.invokeCommand(getCommandName(line), getCommandArguments(line));
                } else {
                    TextFormatter.printErrorMessage("Ошибка выполнения. Скрипт вызывает сам себя.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            TextFormatter.printErrorMessage("Файла с таким именем в текущей папке нет. Переместите файл и повторите попытку");
            return false;
        }
        return true;
    }
}
