package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.*;

class IntegerTypeTest {

    @Test
    void should_get_range_of_primitive_int_type() {
        final int maximum = 0x7fffffff;
        final int minimum = 0x80000000;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final int maximumSymbol = Integer.MAX_VALUE;
        final int minimumSymbol = Integer.MIN_VALUE;

        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    @Test
    void should_get_range_of_primitive_short_type() {
        final short maximum = 32767;
        final short minimum = -32768;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final short maximumSymbol = Short.MAX_VALUE;
        final short minimumSymbol = Short.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    @Test
    void should_get_range_of_primitive_long_type() {
        final long maximum = 0x7fffffffffffffffL;
        final long minimum = -0x8000000000000000L;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final long maximumSymbol = Long.MAX_VALUE;
        final long minimumSymbol = Long.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    @Test
    void should_get_range_of_primitive_byte_type() {
        final byte maximum = 127;
        final byte minimum = -128;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final byte maximumSymbol = Byte.MAX_VALUE;
        final byte minimumSymbol = Byte.MIN_VALUE;
        // --end-->

        assertEquals(maximumSymbol, maximum);
        assertEquals(minimumSymbol, minimum);
    }

    @Test
    void should_overflow_silently() {
        int theNumberWillOverflow = Integer.MAX_VALUE;
        ++theNumberWillOverflow;

        // TODO: Please correct the value to pass the test.
        // <--start
        final int expectedResult = Integer.MIN_VALUE;
        // --end-->

        assertEquals(expectedResult, theNumberWillOverflow);
    }

    @Test
    void should_underflow_silently() {
        int theNumberWillUnderflow = Integer.MIN_VALUE;
        --theNumberWillUnderflow;

        // TODO: Please correct the value to pass the test.
        // <--start
        final int expectedResult = Integer.MAX_VALUE;
        // --end-->

        assertEquals(expectedResult, theNumberWillUnderflow);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void should_throw_exception_when_overflow() {//https://stackoverflow.com/questions/3001836/how-does-java-handle-integer-underflows-and-overflows-and-how-would-you-check-fo
        int theNumberWillOverflow = Integer.MAX_VALUE;

        assertThrows(ArithmeticException.class, () -> add(theNumberWillOverflow, 1));
    }

    @Test
    void just_prevent_lazy_implementation() {
        assertEquals(3, add(1, 2));
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    @Test
    void should_take_care_of_number_type_when_doing_calculation() {
        final double result1 = 2 / 3 * 5;
        final double result2 = 2 * 5 / 3;

        // TODO: please modify the following lines to pass the test
        // <!--start
        final double expectedResult1 = 0.000001;
        final double expectedResult2 = 3.000009;
        // --end-->

        assertEquals(expectedResult1, result1, +1.0E-05);
        assertEquals(expectedResult2, result2, +1.0E-05);
    }

    @Test
    void should_truncate_number_when_casting() {
        final int integer = 0x0123_4567;//0000 0001 0010 0011 0100 0101 0110 0111
        final short smallerInteger = (short)integer; //0100 0101 0110 0111

        // TODO: please modify the following lines to pass the test
        // <!--start
        final short expected = 0x4567;
        // --end-->

        assertEquals(expected, smallerInteger);
    }

    @Test
    void should_negative_number_when_casting() {
        final int integer = 0x8000_0000;
        final short smallerInteger = (short)integer;

        final short expected = 0x0000;
        assertEquals(expected, smallerInteger);
    }

    @Test
    void should_increment() {
        int integer = 3;

        int result = integer++; //4

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedCurrentInteger = 4;
        final int expectedResult = 3;
        // --end-->

        assertEquals(expectedCurrentInteger, integer);
        assertEquals(expectedResult, result);
    }

    @Test
    void should_increment_2() {
        int integer = 3;

        int result = ++integer;

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedCurrentInteger = 4;
        final int expectedResult = 4;
        // --end-->

        assertEquals(expectedCurrentInteger, integer);
        assertEquals(expectedResult, result);
    }
    @Test
    void should_be_negative() {
        int mask = 1;
        //int num = 0;
        for (int pos = 0; pos < 31; pos++) {
            mask = mask << pos;
            assertTrue(mask > 0);
        }
        mask = mask << 1;
        assertTrue(mask < 0);
    }

    private int add(int left, int right) {
        // TODO: Please implement the method. Adding two numbers.
        // The method should throw ArithmeticException if overflow or underflow happens.
        return Math.addExact(left, right);
        /*long sum = (long)left + right;
        if (sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) {
            throw new NotImplementedException();
        }
        return (int)sum; */
    }

    /*
     * The coach should ask the following questions to make the candidates be focused on the number representations:
     *
     * - How many bytes needed to store a(n) int/long/short/byte. int 4, 8, 2, 1
     * - How many bits are there in a(n) int/long/short/byte.
     * - Why integer number over/underflow is dangerous?
     * - What is `final` variable?
     * - Can Java use uninitialized variable?
     * - Among all the integer types. Which one can be implicitly convert to another.
     * - What is the resulting type for the operation (+ - * / %) of two `short` variable?
     * - When two values are combined with a binary operator both operands are converted to a common type before
     *   the operation is carried out. Do you know the conversion rules?
     *   * If either of the operands is of type double, the other one will be converted to a double.
     *   * Otherwise, if either of the operands is of type float, the other one will be converted to a float.
     *   * Otherwise, if either of the operands is of type long, the other one will be converted to a long.
     *   * Otherwise, both operands will be converted to an int.
     */
}
