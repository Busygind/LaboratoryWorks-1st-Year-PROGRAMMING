package lab.client;

import lab.common.util.commands.CommandAbstract;
import lab.common.util.handlers.CommandListener;
import lab.common.util.handlers.LineSplitter;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;
import lab.common.util.requestSystem.Serializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class StreamController {

    public static ByteBuffer prepareCommandToSend(String input) throws IOException {
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
            return null;
        }
        CommandAbstract command = CommandListener.initCommand(input);
        if (command == null) {
//               TextFormatter.printErrorMessage();
            throw new NullPointerException("Command is incorrect. Try again");
        }
        return Serializer.serializeCommand(command);
    }

}
