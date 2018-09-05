package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BooleanOperatorsTest {

    @SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions"})
    @Test
    void should_perform_logical_boolean_operations() {
        boolean[] actualResults = {
            true && true,//T
            true && false,//F
            false && false,//F
            true || true,//T
            true || false,//T
            false || false,//F
            true & true,//T
            true & false,//F
            false & false,//F
            true | true,//T
            true | false,//T
            false | false,//F
            3 == 7,//F
            4 != 5 //T
        };

        // TODO: please modify the following code to pass the test
        // <--start
        boolean[] expectedResult = {true, false, false, true, true, false, true, false, false, true, true, false, false, true};
        // --end-->

        assertArrayEquals(expectedResult, actualResults);
    }

    @Test
    void should_do_bitwise_and_boolean_operation() {
        final int value = 0x1234_abcd; //0001 0010 0011 0100 1010 1011 1100 1101
        final int mask = 0x000f_ff00;  //0000 0000 0000 1111 1111 1111 0000 0000

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x0004_ab00;//0000 0000 0000 0100 1010 1011 0000 0000
        // --end-->

        assertEquals(expected, value & mask);
    }

    @Test
    void should_do_bitwise_or_boolean_operation() {
        final int value = 0x1234_0000; //0001 0010 0011 0100 0000 0000 0000 0000
        final int mask = 0x0000_abcd;  //0000 0000 0000 0000 1010 1011 1100 1101

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x1234_abcd; //0001 0010 0011 0100 1010 1011 1100 1101
        // --end-->

        assertEquals(expected, value | mask);
    }

    @Test
    void should_do_bitwise_not_operation() {
        final int value = 0x0000_ffff;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0xffff_0000;
        // --end-->

        assertEquals(expected, ~value);
    }

    @Test
    void should_not_prior_and() {//& | ~
        final int value = 0xffff;// ~value & 0xffff
        final int test =  0xffff & ~value;
        assertEquals(0x0000, test); // ~ > &
    }
    @Test
    void should_not_prior_or() {
        final int orVal = 0x0000_ffff;
        int testNotPriorOr = ~orVal | 0x0000_ffff;
        int expectedNotPriorOr = 0xffff_ffff;
        int expectedOrPriorNot = 0xffff_0000;
        assertEquals(expectedNotPriorOr, testNotPriorOr);//inline command option N
        assertNotEquals(expectedOrPriorNot, testNotPriorOr);
    }
    @Test
    void should_and_prior_or(){
        final int val = 0xffff;
        final int test = val | 0x0000 & 0xffff;
    }
}
