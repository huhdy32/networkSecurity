package server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final EncryptionProcessor encryptionProcessor;

    public Server(EncryptionProcessor cipherMapper) {
        this.encryptionProcessor = cipherMapper;
    }

    public void init(final int port) throws IOException {
        ServerSocket socket = new ServerSocket(port);
        System.out.println("서버 포트 : " + port);
        while (true) {
            final Socket receivedSocket = socket.accept();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(receivedSocket.getInputStream()));
            while (true) {
                final String receivedMessage = bufferedReader.readLine();

                System.out.println("======= RECEIVED MESSAGE =======");
                System.out.println(receivedMessage);

                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(receivedSocket.getOutputStream());
                final String decodedMessage = encryptionProcessor.process(receivedMessage, bufferedOutputStream);
                System.out.println("DECODED = " + decodedMessage);
                if (decodedMessage != null) {
                    return;
                }
            }
        }
    }
}

