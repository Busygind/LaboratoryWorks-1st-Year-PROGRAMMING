package lab.client;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ScriptReader {
    private static Set<String> namesOfRanScripts = new HashSet<>();
    private String filename;
    private Path path;

    public ScriptReader(String commandLine) {
        if (!ExecuteScriptCommandIsCorrect(commandLine)) {
            throw new IllegalArgumentException("Incorrect count of args in execute_script command");
        }
        this.filename = LineSplitter.smartSplit(commandLine).get(1);
        path = Path.of(filename).toAbsolutePath();
    }

    private boolean ExecuteScriptCommandIsCorrect(String commandLine) {
        ArrayList<String> commandParts = LineSplitter.smartSplit(commandLine);
        return commandParts.size() == 2;
    }

    public Path getPath() {
        return path;
    }


}
