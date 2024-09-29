import cipher.CipherFactory;
import cipher.MyCipherHandler;
import network.Client;
import protocol.Cipher;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        final String key = "aeskey1234567898";
        final CipherFactory cipherFactory = new CipherFactory();
        final MyCipherHandler myCipherHandler = new MyCipherHandler(cipherFactory.getAESCipher(key));
        try {
            final Client client = new Client(myCipherHandler, 17872, Cipher.AES, key);
            client.sendKey();
            client.sendProtocolAndKey();
            client.sendMessage("testMessage Is Sended");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
