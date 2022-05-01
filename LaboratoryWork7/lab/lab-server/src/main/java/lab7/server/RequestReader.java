package lab7.server;

import lab7.common.util.requestSystem.requests.Request;
import lab7.common.util.requestSystem.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.function.Supplier;

public class RequestReader implements Supplier<Request> {

    private final SelectionKey key;

    public RequestReader(SelectionKey key) {
        this.key = key;
    }

    public Request get() {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(4096);
        try {
            socketChannel.read(readBuffer);
            Serializer serializer = new Serializer();
            return serializer.deserializeRequest(readBuffer.array());
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
