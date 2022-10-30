package ru.otus.testframework.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.testframework.TestCaseReport;
import ru.otus.testframework.tuple.TestResultTuple;
import ru.otus.utils.ReflectionHelper;
import ru.otus.test.DummyCalcServiceTest;
import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestProcessor {
    private static final Logger log = LoggerFactory.getLogger(TestProcessor.class);

    public static void process(Class<DummyCalcServiceTest> clazz) throws InterruptedException {
        List<Method> beforeCases = new ArrayList<>();
        List<Method> testCases = new ArrayList<>();
        List<Method> afterCases = new ArrayList<>();
        for (var method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeCases.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                testCases.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                afterCases.add(method);
            }
        }
        List<Callable<TestResultTuple>> tasks = new ArrayList<>(testCases.size());
        for (var testCase : testCases) {
            tasks.add(() -> doTest(testCase, beforeCases, afterCases, clazz));
        }
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<TestResultTuple>> futures = pool.invokeAll(tasks);
        TestCaseReport report = new TestCaseReport(testCases.size());
        for (Future<TestResultTuple> future : futures) {
            try {
                var caseResult = future.get();
                if (caseResult.isCompletedNormally()) {
                    report.complete();
                } else {
                    report.failed(caseResult.getLastCaseName(), caseResult.getException().getMessage());
                }
            } catch (ExecutionException e) {
                report.failed("doProcess()", e.getMessage());
            }
        }
        pool.shutdown();
        log.info(report.toString());
    }

    private static TestResultTuple doTest(Method testCase,
                                  List<Method> beforeCases,
                                  List<Method> afterCases,
                                  Class<DummyCalcServiceTest> clazz) {
        TestResultTuple result = new TestResultTuple();
        try {
            DummyCalcServiceTest testInstance = ReflectionHelper.instantiate(clazz);
            beforeCases.forEach(beforeCase -> {
                result.setLastCaseName(beforeCase.getName());
                ReflectionHelper.callMethod(testInstance, beforeCase.getName());
            });
            result.setLastCaseName(testCase.getName());
            ReflectionHelper.callMethod(testInstance, testCase.getName());
            afterCases.forEach(afterCase -> {
                result.setLastCaseName(afterCase.getName());
                ReflectionHelper.callMethod(testInstance, afterCase.getName());
            });
        } catch (Exception e) {
            result.setException(e);
        }
        return result;
    }
}
