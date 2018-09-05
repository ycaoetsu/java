package com.cultivation.javaBasic.util;

@SuppressWarnings("unused")
public class OverloadingFixture {
    public String methodWithOneParameter(String value) {
        return "methodWithOneParameter(String)";
    }

    public String methodWithOneParameter(Object value) {
        return "methodWithOneParameter(Object)";
    }

    public String methodWithTwoParameters(String string, Integer integer) {
        return "methodWithTwoParameters(String, Integer)";
    }

    public String methodWithTwoParameters(String string, Number number) {
        return "methodWithTwoParameters(String, Number)";
    }

    /*public String methodWithTwoParameters(String string, int integer) {
        return "methodWithTwoParameters(String, Int)";
    }*/ //boxing 是在就近找int找不到就近找Integer
}
