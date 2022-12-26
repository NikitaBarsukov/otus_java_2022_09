package ru.otus.testframework;

import java.util.HashMap;
import java.util.Map;

public class TestCaseReport {
    private final int all;
    private int completed;
    private int failed;
    private final Map<String, String> failedCases;

    public TestCaseReport(int all) {
        this.all = all;
        failedCases = new HashMap<>();
    }

    public void failed(String name, String cause) {
        failedCases.put(name, cause);
        failed++;
    }

    public void complete() {
        completed++;
    }

    @Override
    public String toString() {
        return "Test Results" +
                "Accepted = " + all +
                ", Completed=" + completed +
                ", Failed=" + failed +
                ", Failed Cases=" + failedCases;
    }
}
