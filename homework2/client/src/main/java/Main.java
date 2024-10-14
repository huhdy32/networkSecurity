import network.Client;
import protocol.Cipher;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    private static final int BASIC_PORT = 17872;

    public static void main(String[] args) {
        try {
            final Client client = new Client(getPort(args), Cipher.RSA);
            client.initWithServer();
            client.sendMessage("testMessage Is Sended");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
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
