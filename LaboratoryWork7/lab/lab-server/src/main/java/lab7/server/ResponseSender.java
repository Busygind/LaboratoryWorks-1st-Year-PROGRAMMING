package lab7.server;

import lab7.common.util.requestSystem.Serializer;
import lab7.common.util.requestSystem.responses.CommandResponse;
import lab7.common.util.requestSystem.responses.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.function.Consumer;

public class ResponseSender implements Consumer<Response> {

    private final SelectionKey key;
    private volatile Set<SelectionKey> workingKeys;

    public ResponseSender(SelectionKey key, Set<SelectionKey> workingKeys) {
        this.key = key;
        this.workingKeys = workingKeys;
    }

    public synchronized void accept(Response response) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        if (response == null) {
            response = new CommandResponse("Server couldn't handle your request :(");
        }
        Serializer serializer = new Serializer();
        try {
            ByteBuffer buffer = serializer.serializeResponse(response);
            socketChannel.write(buffer);
        } catch (IOException e) {
            ServerConfig.LOGGER.error("Problem with response serializing or sending");
        }
        ServerConfig.LOGGER.info("Server wrote response to client");
        workingKeys.remove(key);
    }
}
