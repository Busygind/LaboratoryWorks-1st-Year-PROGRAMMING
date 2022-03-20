package lab.client;

import lab.common.util.commands.CommandAbstract;
import lab.common.util.handlers.CommandListener;
import lab.common.util.handlers.LineSplitter;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StreamController {

    public static void sendCommand(ObjectOutputStream oos, String input) throws IOException, InterruptedException {
        ArrayList<String> splitCommand = LineSplitter.smartSplit(input);
        if ("execute_script".equalsIgnoreCase(splitCommand.get(0))) {
            try {
                ScriptReader scReader = new ScriptReader(input);
                for (String command : scReader.getCommandsFromFile()) {
                    //todo доделать экзекут!!!!!
                }
            } catch (IllegalArgumentException e) {
                TextFormatter.printErrorMessage(e.getMessage());
            }
            return;
        }
        CommandAbstract command = CommandListener.initCommand(input);
        if (command == null) {
//               TextFormatter.printErrorMessage();
            throw new NullPointerException("Something went wrong, try again");
        }
        oos.writeObject(command);
        oos.flush();
        TextFormatter.printInfoMessage("Client sent command to server.");
        Thread.sleep(2000);
    }

    public static void getResponse(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Response in = (Response) ois.readObject();
        TextFormatter.printMessage(in.getMessage());
    }
}
