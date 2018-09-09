package com.cultivation.javaBasicExtended.reverseString;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (input == "") {
            return new String[0];
        }
        String[] strings = input.split(" ");
        if (strings.length == 0) {
            return new String[0];
        }
        String[] result = new String[strings.length];
        for (int i = strings.length - 1; i >= 0; i--) {
            result[strings.length - i - 1] = strings[i];
        }
        return result;
//        throw new NotImplementedException();
        // --end-->
    }
}
