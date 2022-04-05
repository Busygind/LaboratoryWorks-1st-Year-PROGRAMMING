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
        if (scriptAlreadyRan(commandLine)) {
            throw new IllegalArgumentException("This script already ran");
        }
        this.filename = LineSplitter.smartSplit(commandLine).get(1);
        namesOfRanScripts.add(filename);
        path = Path.of(filename).toAbsolutePath();
    }

    private boolean scriptAlreadyRan(String commandLine) {
        ArrayList<String> commandParts = LineSplitter.smartSplit(commandLine);
        return namesOfRanScripts.contains(commandParts.get(1));
    }

    public void stopScriptReading() {
        namesOfRanScripts.remove(filename);
    }

    public Path getPath() {
        return path;
    }


}
