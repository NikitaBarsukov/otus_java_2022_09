package ru.otus.test;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.service.DummyCalcService;
import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

public class DummyCalcServiceTest {
    private static final Logger log = LoggerFactory.getLogger(DummyCalcServiceTest.class);

    private DummyCalcService service;

    @Before
    public void setup() {
        service = new DummyCalcService(ImmutableList.of(2,2));
    }

    @Test
    public void sumShouldReturnCorrectSumWith6() {
        int res = service.dummySum(6);
        log.info("AssertThat {} is equal to 16", res);
    }

    @Test
    public void sumShouldReturnCorrectSumWith4() {
        int res = service.dummySum(4);
        log.info("AssertThat {} is equal to 12", res);
    }

    @Test
    public void sumShouldThrowClassCastExep() {
        int res = service.dummySum(4.5);
    }

    @After
    public void clean() {
        service = null;
    }
}
