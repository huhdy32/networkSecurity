import server.EncryptionProcessor;
import server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final EncryptionProcessor cipherMapper = new EncryptionProcessor();
        Server bob = new Server(cipherMapper);
        bob.init();
    }
}
