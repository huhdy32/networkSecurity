package protocol;

import java.util.Arrays;

public enum Cipher {
    DES("DES"),
    TRIPLE_DES("3DES"),
    AES("AES"),
    RSA("RSA");

    private String name;

    Cipher(String name) {
        this.name = name;
    }

    public static Cipher getCipher(final String name) {
        return Arrays.stream(Cipher.values())
                .filter(cipher -> cipher.name.equals(name))
                .findAny()
                .orElseThrow();
    }
}
