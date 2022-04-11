package lab.client.dataController;

import lab.common.util.handlers.TextFormatter;
import lab.common.util.requestSystem.Response;
import lab.common.util.requestSystem.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ResponseReceiver {

    private final SocketChannel channel;
    private final SelectionKey key;
    private final Selector selector;

    public ResponseReceiver(SocketChannel channel, SelectionKey key, Selector selector) {
        this.channel = channel;
        this.key = key;
        this.selector = selector;
    }

    public void receive() throws IOException, ClassNotFoundException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(socketChannel.socket().getReceiveBufferSize());
        socketChannel.read(readBuffer);
        Response response = Serializer.deserializeResponse(readBuffer.array());
        TextFormatter.printInfoMessage(response.getMessage());
        if (response.getDragons() != null) {
            TextFormatter.printMessage(response.getDragons().toString());
        }
        channel.register(selector, SelectionKey.OP_WRITE);
    }
}
