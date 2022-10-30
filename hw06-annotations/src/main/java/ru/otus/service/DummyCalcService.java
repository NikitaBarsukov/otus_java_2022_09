package ru.otus.service;

import java.util.List;

public class DummyCalcService {

    private final List<Integer> digits;

    public DummyCalcService(List<Integer> digits) {
        this.digits = digits;
    }

    /**
     * Add some digit to all elements and return sum of it.
     * @return Sum of inner list.
     */
    public int dummySum(int add) {
        return digits.stream()
                .map(d -> d + add)
                .reduce(0, Integer::sum);
    }
}
