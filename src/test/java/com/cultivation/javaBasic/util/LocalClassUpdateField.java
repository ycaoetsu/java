package com.cultivation.javaBasic.util;

public class LocalClassUpdateField {
    private int year;
    public LocalClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    public void somethingHappen() {
        //int year = 3;
        class YearIncrementer {
            private int year = 3;
            @SuppressWarnings("WeakerAccess")
            public void increment() {
                ++this.year;
            }
        }

        new YearIncrementer().increment();
    }
}