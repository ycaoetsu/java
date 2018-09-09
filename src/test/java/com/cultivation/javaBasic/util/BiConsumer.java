package com.cultivation.javaBasic.util;

public interface BiConsumer {
    int[] swaper(int[] array);
    default void getName() {
        System.out.print("name");
    }
}
