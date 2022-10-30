package ru.otus.testframework.tuple;


public class TestResultTuple {
    private String lastCaseName;
    private Exception exception;

    public boolean isCompletedNormally() {
        return exception == null;
    }

    public String getLastCaseName() {
        return lastCaseName;
    }

    public void setLastCaseName(String lastCaseName) {
        this.lastCaseName = lastCaseName;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}