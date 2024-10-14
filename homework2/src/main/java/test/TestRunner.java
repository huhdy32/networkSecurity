package test;

import java.util.function.Consumer;

public class TestRunner {
    private final TestCase testGenerator = new TestCase("a");
    public void runTests(final Consumer<String> consumer) {
        consumer.accept(testGenerator.get10ByteString());
//        consumer.accept(testGenerator.get1000ByteString());
    }
}
