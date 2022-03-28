package lab.server;

import lab.common.util.commands.CommandAbstract;
import lab.common.util.entities.CollectionManager;
import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;
import lab.common.util.requestSystem.Serializer;
import lab.server.exceptions.DisconnectInitException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class IOController {

    public static CommandAbstract getCommand(SocketChannel in) throws IOException, ClassNotFoundException {
        ByteBuffer readBuffer = ByteBuffer.allocate(4096);
        in.read(readBuffer);
        return Serializer.deserializeCommand(readBuffer.array());
    }

    public static Response buildResponse(CommandAbstract command, CollectionManager manager) throws DisconnectInitException {
        if (command.getName().equals("exit")) {
            TextFormatter.printInfoMessage("Client initialize disconnect");
            throw new DisconnectInitException("Client initialize disconnect");
        }
        return new Response(command.execute(manager), true);
    }
}
