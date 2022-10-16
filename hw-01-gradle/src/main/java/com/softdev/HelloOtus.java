package com.softdev;

import com.google.common.collect.ImmutableList;

public class HelloOtus {
    public static void main(String... args) {
        ImmutableList.of(1, 2, 3, 4, 5).forEach(System.out::println);
    }
}