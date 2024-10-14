package network;

import protocol.ProtocolMessage;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private final BufferedOutputStream outputStream;
    private final BufferedReader inputReader;

    public Connection(final int port) throws IOException {
        this.socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", port));
        System.out.println("접속 서버 포트 : " + port);
        outputStream = new BufferedOutputStream(socket.getOutputStream());
        inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    public void sendMessage(final ProtocolMessage protocolMessage) throws IOException {
        outputStream.write(protocolMessage.getMessage());
        outputStream.flush();
        System.out.println("SEND : " + protocolMessage);
    }

    public String readLine() throws IOException {
        final String received = inputReader.readLine();
        System.out.println("RECEIVED : " + received);
        return received;
    }
}
