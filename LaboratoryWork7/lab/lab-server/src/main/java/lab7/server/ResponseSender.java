package lab7.server;

import lab7.common.util.requestSystem.Serializer;
import lab7.common.util.requestSystem.responses.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ResponseSender {

    private final SocketChannel socketChannel;

    public ResponseSender(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public void send(Response response) throws IOException {
        ByteBuffer buffer = Serializer.serializeResponse(response);
        socketChannel.write(buffer);
        ServerConfig.LOGGER.info("Server wrote response to client");
    }
}
