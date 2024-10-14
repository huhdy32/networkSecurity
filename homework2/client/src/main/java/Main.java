import cipher.CipherFactory;
import cipher.MyCipherHandler;
import network.Client;
import protocol.Cipher;

import java.io.IOException;

public class Main {
    private static final int BASIC_PORT = 17872;

    public static void main(String[] args) {
        final String key = "aeskey1234567898";
        final CipherFactory cipherFactory = new CipherFactory();
        final MyCipherHandler myCipherHandler = new MyCipherHandler(cipherFactory.getAESCipher(key));
        try {
            final Client client = new Client(myCipherHandler, getPort(args), Cipher.AES, key);
            client.sendKey();
            client.sendProtocolAndKey();
            client.sendMessage("testMessage Is Sended");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static int getPort(final String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("포트 값은 여러개로 설정 불가");
        }
        if (args.length == 1) {
            try {
                return Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("포트 값 파싱 실패 : " + e);
            }
        }
        return BASIC_PORT;
    }
}
