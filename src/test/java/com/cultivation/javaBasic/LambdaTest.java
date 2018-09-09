package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class LambdaTest {
    @Test
    void should_apply_to_interface_with_single_abstract_method() {//functional interface can be written as a lambda
        StringFunc lambda = () -> "Hello";//a form of anonymous class

        // TODO: please modify the following code to pass the test
        // <--start
        final String expect = "Hello";
        // --end-->

        assertEquals(expect, lambda.getString());
    }

    @Test
    void should_apply_to_interface_with_single_abstract_method_exercise() {
        CharSupplier lambda = () -> 'c';
        final char expect = 'c';
        assertEquals(expect, lambda.getAsChar());
    }

    @Test
    void should_get_sum() {
        IntFunction lambda = ((left, right) -> left + right);
        final int expect = 3;
        assertEquals(3, lambda.apply(1, 2));
    }

    @Test
    void should_swap_given_array() {
        int[] array1 = new int[]{1};
        int[] array2 = new int[] {1, 2};
        int[] array3 = new int[] {1, 2, 3};
        BiConsumer biConsumer = (int[] array) -> {
            int len = array.length;
            for (int i = 0; i < len / 2; i++) {
                int temp = array[i];
                array[i] = array[len - i - 1];
                array[len - i - 1] = temp;
            }
            return array;
        };
        assertArrayEquals(new int[]{1}, biConsumer.swaper(array1));
        assertArrayEquals(new int[]{2, 1}, biConsumer.swaper(array2));
        assertArrayEquals(new int[]{3, 2, 1}, biConsumer.swaper(array3));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO: please bind lambda to instanceMethod.
        // <--start
        StringFunc lambda = this :: instanceMethod;
        // --end-->

        assertEquals("instanceMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO: please bind lambda to staticMethod
        // <--start
        StringFunc lambda = LambdaTest :: staticMethod;
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_bind_to_constructor() {
        // TODO: please bind lambda to constructor of ArrayList<Integer>
        // <--start
        GenericFunc<ArrayList<Integer>> lambda = ArrayList :: new;
        // --end-->

        ArrayList<Integer> value = lambda.getValue();
        value.add(1);

        assertEquals(1, value.size());
    }

    @Test
    void should_capture_variable_in_a_closure() {//the type of variable captured by lambda is final, cannot be updated in lambda also outside lambda
        int captured = 5;//closure function + context (definition environment)
        final int[] array = new int[]{1,2,3};
        array[1] = 8;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array);
        }//{1,8,3} final is a relationship
        StringFunc lambda = () -> captured + " has been captured.";

        final String message = lambda.getString();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "5 has been captured.";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_evaluate_captured_variable_when_executing() {
        ValueHolder<String> value = new ValueHolder<>();
        value.setValue("I am the King of the world!");

        StringFunc lambda = () -> "The length of captured value is: " + value.getValue().length();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "The length of captured value is: 4" ;
        // --end-->

        value.setValue("Blah");
        assertEquals(expected, lambda.getString());
    }

    @Test
    void should_extend_variable_scope() {
        StringFunc stringFunc = returnLambda();
        String message = stringFunc.getString();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "In the year 2019" ;
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_capture_this_variable() {//capture
        ThisInClosure instance = new ThisInClosure();
        StringFunc stringFunc = instance.getLambda();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "ThisInClosure";
        // --end-->

        assertEquals(expected, stringFunc.getString());
    }

    @Test
    void should_not_assign_lambda_to_object() {
        //Object object = () -> "abc";
        //lambda can only be assigned to functional interface
    }

    private static StringFunc returnLambda() {
        int year = 2019;
        return () -> "In the year " + year;
    }

    @SuppressWarnings("unused")
    private static String staticMethod() {
        return "staticMethod";
    }

    @SuppressWarnings("unused")
    private String instanceMethod() {
        return "instanceMethod";
    }
}

/*
 * - Do you think you can assign a lambda expression to an Object instance?
 */
