package protocol;

import java.util.Arrays;

public enum MessageProtocol {
    KEY("KEY"),
    CIPHER_TYPE("CIPHER_TYPE"),
    ENCRYPTED_MESSAGE("ENCRYPTED_MESSAGE");

    private String name;

    MessageProtocol(final String name) {
        this.name = name;
    }

    public static MessageProtocol getProtocol(final String type) {
        return Arrays.stream(MessageProtocol.values())
                .filter(messageProtocol -> messageProtocol.name.contains(type))
                .findAny()
                .orElseThrow();
    }
}
