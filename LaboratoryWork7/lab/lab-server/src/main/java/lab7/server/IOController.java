package lab7.server;

import lab7.server.commands.CommandAbstract;
import lab7.common.util.entities.CollectionManager;
import lab7.common.util.requestSystem.requests.Request;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.common.util.requestSystem.Serializer;
import lab7.server.exceptions.DisconnectInitException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class IOController {

    public static Request getRequest(SocketChannel in) throws IOException, ClassNotFoundException {
        ByteBuffer readBuffer = ByteBuffer.allocate(4096);
        in.read(readBuffer);
        return Serializer.deserializeRequest(readBuffer.array());
    }

    public static CommandResponse buildResponse(CommandAbstract command, CollectionManager manager) throws DisconnectInitException {
        if (command.getName().equals("exit")) {
            throw new DisconnectInitException("Client initialize disconnect");
        }
        return (CommandResponse) command.execute(manager);
    }
}
