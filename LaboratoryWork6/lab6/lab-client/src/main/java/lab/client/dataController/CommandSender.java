package lab.client.dataController;

import lab.client.commandDispatcher.CommandBuilder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class CommandSender {

    private static final int SLEEP_TIME = 500;
    private final SocketChannel channel;
    private final String input;
    private final Selector selector;

    public CommandSender(SocketChannel channel, String input, Selector selector) {
        this.channel = channel;
        this.input = input;
        this.selector = selector;
    }

    public void send() throws InterruptedException, IOException {
        ByteBuffer buffer = CommandBuilder.buildCommand(input);
        channel.write(buffer);
        channel.register(selector, SelectionKey.OP_READ);
        Thread.sleep(SLEEP_TIME);
    }
}
