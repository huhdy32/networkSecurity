package test;

class TestCase {
    private final String temp;

    public TestCase(String repeatable) {
        this.temp = repeatable;
    }

    private char getChar(final int index) {
        return temp.charAt( index % temp.length());
    }

    public String get10ByteString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(getChar(i));
        }
        return stringBuilder.toString();
    }

    public String get100ByteString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            stringBuilder.append(getChar(i));
        }
        return stringBuilder.toString();
    }

    public String get1000ByteString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(getChar(i));
        }
        return stringBuilder.toString();
    }
}
