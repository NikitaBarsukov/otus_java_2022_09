package ru.otus;

import ru.otus.testframework.processor.TestProcessor;
import ru.otus.test.DummyCalcServiceTest;

public class Main {
    public static void main(String[] args) {
        try {
            TestProcessor.process(DummyCalcServiceTest.class);
        } catch (InterruptedException ignored) {}
    }
}