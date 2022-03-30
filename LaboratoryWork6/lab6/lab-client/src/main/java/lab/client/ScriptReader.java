package lab.client;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ScriptReader {
    private static Set<String> namesOfRanScripts = new HashSet<>();
    private final String filename;
    private final Path path;

    public ScriptReader(String commandLine) {
        if (!ExecuteScriptCommandIsCorrect(commandLine)) {
            throw new IllegalArgumentException("Incorrect count of args in execute_script command or that script already ran");
        }
        this.filename = LineSplitter.smartSplit(commandLine).get(1);
        namesOfRanScripts.add(filename);
        path = Path.of(filename).toAbsolutePath();
    }

    private boolean ExecuteScriptCommandIsCorrect(String commandLine) {
        ArrayList<String> commandParts = LineSplitter.smartSplit(commandLine);
        return commandParts.size() == 2 && !namesOfRanScripts.contains(commandParts.get(1));
    }

    public void stopScriptReading() {
        namesOfRanScripts.remove(filename);
    }

    public Path getPath() {
        return path;
    }


}
