package lab.client;

import lab.common.util.handlers.LineSplitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ScriptReader {
    private static final File starting = new File(System.getProperty("user.dir"));
    private static Set<String> namesOfRanScripts = new HashSet<>();
    private String filename;
    private File file;

    public ScriptReader(String commandLine) {
        if (!ExecuteScriptCommandIsCorrect(commandLine)) {
            throw new IllegalArgumentException("Incorrect count of args in execute_script command");
        }
        this.filename = LineSplitter.smartSplit(commandLine).get(1);
    }

    public ArrayList<String> getCommandsFromFile() throws FileNotFoundException {
        ArrayList<String> commands = new ArrayList<>();
        file = new File(starting, filename);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            commands.add(nextLine);
        }
        return commands;
    }

    private boolean ExecuteScriptCommandIsCorrect(String commandLine) {
        ArrayList<String> commandParts = LineSplitter.smartSplit(commandLine);
        if (commandParts.size() != 2) {
            return false;
        }
        return true;
    }


}
