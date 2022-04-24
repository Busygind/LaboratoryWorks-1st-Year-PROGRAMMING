package lab7.client;

import lab7.client.commandDispatcher.LineSplitter;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScriptReader {
    private static Set<String> namesOfRanScripts = new HashSet<>();
    private final String filename;
    private final File path;

    public ScriptReader(String commandLine) {
        if (scriptAlreadyRan(commandLine)) {
            throw new IllegalArgumentException("This script already ran");
        }
        this.filename = LineSplitter.smartSplit(commandLine).get(1);
        namesOfRanScripts.add(filename);
        path = new File(new File(System.getProperty("user.dir")), filename);//Path.of(filename).toAbsolutePath();
    }

    private boolean scriptAlreadyRan(String commandLine) {
        List<String> commandParts = LineSplitter.smartSplit(commandLine);
        return namesOfRanScripts.contains(commandParts.get(1));
    }

    public void stopScriptReading() {
        namesOfRanScripts.remove(filename);
    }

    public File getPath() {
        return path;
    }

}
