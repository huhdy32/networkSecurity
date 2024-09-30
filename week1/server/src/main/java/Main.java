import server.EncryptionProcessor;
import server.Server;

import java.io.IOException;

public class Main {
    private static final int BASIC_PORT = 17872;

    public static void main(String[] args) throws IOException {
        final EncryptionProcessor cipherMapper = new EncryptionProcessor();

        Server bob = new Server(cipherMapper);
        bob.init(getPort(args));
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
