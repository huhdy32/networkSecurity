class CipherResultConsolePrinter implements CipherResultPrinter {
    @Override
    public void printResult(String cipherName, String message, String encryptedMessage, String decryptedMessage) {
        System.out.println("==================CIPHER : " + cipherName + "==================");
        System.out.println("PLAIN TEXT     : " + message);
        System.out.println("ENCRYPTED TEXT : " + encryptedMessage);
        System.out.println("DECRYPTED TEXT : " + decryptedMessage);
//        System.out.println("=================================================");
    }
}
