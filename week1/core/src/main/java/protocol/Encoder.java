package protocol;

import java.util.Base64;

public class Encoder {
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Base64.Decoder decoder = Base64.getDecoder();

    public byte[] encode(final byte[] bytes) {
        return encoder.encode(bytes);
    }

    public byte[] decode(final String encodedBytes) {
        return decoder.decode(encodedBytes);
    }
}
