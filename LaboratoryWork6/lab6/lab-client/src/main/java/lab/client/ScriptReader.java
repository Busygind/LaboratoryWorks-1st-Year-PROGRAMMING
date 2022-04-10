package lab.client;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
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
        ArrayList<String> commandParts = LineSplitter.smartSplit(commandLine);
        return namesOfRanScripts.contains(commandParts.get(1));
    }

    public void stopScriptReading() {
        namesOfRanScripts.remove(filename);
    }

    public File getPath() {
        return path;
    }

}
