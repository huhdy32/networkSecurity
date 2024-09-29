package network;

import protocol.Encoder;
import protocol.ProtocolMessage;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private final BufferedOutputStream outputStream;

    public Connection(final int port) throws IOException {
        this.socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", port));
        outputStream = new BufferedOutputStream(socket.getOutputStream());
    }


    public void sendMessage(final ProtocolMessage protocolMessage) throws IOException {
        outputStream.write(protocolMessage.getMessage());
        outputStream.flush();
    }
}
