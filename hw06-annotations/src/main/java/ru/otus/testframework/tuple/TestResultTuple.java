package ru.otus.testframework.tuple;


public class TestResultTuple {
    private String lastCaseName;
    private Throwable exception;

    public boolean isCompletedNormally() {
        return exception == null;
    }

    public String getLastCaseName() {
        return lastCaseName;
    }

    public void setLastCaseName(String lastCaseName) {
        this.lastCaseName = lastCaseName;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception.getCause().getCause();
    }
}