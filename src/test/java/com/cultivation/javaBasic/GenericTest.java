package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.Manager;
import com.cultivation.javaBasic.util.Pair;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericTest {
    @SuppressWarnings("unused")
    @Test
    void should_auto_resolve_generic_method() {
        final String[] words = {"Hello", "Good", "Morning"};
        //generic cannot pass primitive type final int[] num = {1, ,2, 3};
        //besides for generics, you have intSupplier, doubleSupplier, predicate interface (java.util.interface Function.java file
        // TODO: please call getMiddle method for string
        // <--start
        final String last = GenericTest.getLast(words);
        // --end-->

        assertEquals("Morning", last);
    }

    @Test
    void should_specify_a_type_restriction_on_typed_parameters() {
        int minimumInteger = min(new Integer[]{1, 2, 3});
        double minimumReal = min(new Double[]{1.2, 2.2, -1d});
        String minString = min(new String[]{"ab", "cd"});
        assertEquals(1, minimumInteger);
        assertEquals(-1d, minimumReal, 1.0E-05);
        assertEquals("ab", minString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_not_know_generic_type_parameters_at_runtime() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));
    }

    @Test
    void should_erase_type_runtime() throws NoSuchFieldException {
        TestClass<String> test1 = new TestClass<>();
        Class<?> fieldClass = test1.getClass().getField("var").getType();
        Class<Object> expected = Object.class;
        assertTrue(expected.equals(fieldClass));
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked", "unused", "ConstantConditions"})
    @Test
    void should_be_careful_of_raw_type_generic() {
        Pair<Manager> managerPair = new Pair<>();
        Pair rawPair = managerPair;//Object type
        rawPair.setFirst(new Employee());

        boolean willThrow = false;
        try {
            Manager first = managerPair.getFirst();//Manager first = (Manager)rawPair.getFirst();
        } catch (ClassCastException error) {
            willThrow = true;
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), willThrow);
    }

    @Test
    void should_swap() {//?
        Pair<String> pair = new Pair<>("Hello", "World");

        swap(pair);

        assertEquals("World", pair.getFirst());
        assertEquals("Hello", pair.getSecond());
    }



    @SuppressWarnings("unused")
    private static <T> T getLast(T[] args) {
        return args[args.length - 1];
    }

    // TODO: please implement the following code to pass the test. It should be generic after all.
    // The method should only accept `Number` and the number should implement `Comparable<T>`
    // <--start
    @SuppressWarnings("unused")
    private <T extends Comparable<T>> T min(T[] list) {
        T minValue = list[0];
        for (int i = 0; i < list.length; i++) {
            if (list[i].compareTo(minValue) < 0) {
                minValue = list[i];
            }
        }
        return minValue;
    }



    // --end-->

    // TODO: please implement following method to pass the test. But you cannot change the signature
    // <--start
    @SuppressWarnings("unused")
    private static void swap(Pair<?> pair) {
        swapHelper(pair);
    }
    private static <T> void swapHelper(Pair<T> pair) {//? called wildCard, unknown type
        T o1 = pair.getFirst();
        T o2 = pair.getSecond();
        pair.setFirst(o2);
        pair.setSecond(o1);
    }

    // TODO: You can add additional method within the range if you like
    // <--start

    // --end-->
    @Test
//   public <? super Integer> testSigniture(? t) {//have the erasure
//        return t;
//   } lower bound can only be used in parameters
    //upper bound can be used as paramter and return type with T
    //cannot be applied to primitive types

//    public Object testSigniture(Object t) {
//        return t;
//    }

    public Integer testSigniture(Integer t) {
        return t;
    }
}

/*class testSigniture<T> {

}
class testSigniture {

}*/
/*
 * - Can you give an example when you have to specify a typed parameter in generic method call?
 * - Can type parameter have more than one bounds? Can type parameter bounds to class? Can type parameter bounds to both
 *   class and interface?
 * - What if you have 2 class that one is generic called `KeyValuePair<K, V>` and the second is a non-generic method
 *   called `KeyValuePair` in the same package?
 * - Can you use primitive types as type parameter?
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   String key = value.getKey();
 *   ```
 * - Please describe the wildcard generic type.
 */

class TestClass<T> {
    public T var;
}

class TestClassChild<T> extends TestClass {
    public T val;
}