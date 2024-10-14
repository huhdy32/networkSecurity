package protocol;

import java.util.Objects;

public class ProtocolMessage implements EncodedMessage{
    private final MessageProtocol messageProtocol;
    private final String message;

    public ProtocolMessage(MessageProtocol messageProtocol, final String message) {
        if (Objects.isNull(messageProtocol) || Objects.isNull(message)) {
            throw new RuntimeException("PROTOCOL은 널일 수 없음");
        }
        this.messageProtocol = messageProtocol;
        this.message = message;
    }

    @Override
    public byte[] getMessage() {
        final String encryptedMessage = messageProtocol.name() + " " + message + "\n";
        return encryptedMessage.getBytes();
    }
}
