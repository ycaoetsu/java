package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {
    private int year;

    public InnerClassUpdateField(int year) {
        this.year = year;
    }
    public void add() {
        class YearIncrementation {
            public void add() {

            }
        }
        this.new InnerClass(2).add();
    }

    public int getYear() {
        return this.year;
    }

    public class InnerClass {
        private int year;
        public InnerClass(int year) {
            this.year = year;
        }
        public void add() {
            InnerClassUpdateField.this.year += this.year;
        }
    }
}
