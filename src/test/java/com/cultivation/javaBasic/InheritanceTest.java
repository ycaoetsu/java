package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.PersonForEquals;
import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Parent {
    static void method() {
        System.out.print("1");
    }
}
class Child extends Parent{
    void method2() {
        method();
    }
}


class InheritanceTest {

    @Test
    void should_be_derived_from_object_class() {//?
        // TODO: please modify the following code to pass the test
        // <--start
        final Class<?> expectedSuperClass = Object.class;//java.lang.Object;
        // --end-->
        System.out.print(SimpleEmptyClass.class.getSuperclass());
        assertEquals(expectedSuperClass, SimpleEmptyClass.class.getSuperclass());
        //assertTrue(Object.class.getName().equals("class java.lang.Object"));
    }

    @Test
    void should_call_super_class_constructor() {//when call a child's constructor, first call its parent's constructor
        DerivedFromSuperClassWithDefaultConstructor instance = new DerivedFromSuperClassWithDefaultConstructor();

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = {"SuperClassWithDefaultConstructor.constructor()", "DerivedFromSuperClassWithDefaultConstructor.constructor()"};
        // --end-->

        String[] logs = instance.getLogs();

        assertArrayEquals(expected, logs);
    }

    @Test
    void should_call_super_class_constructor_continued() {
        DerivedFromSuperClassWithDefaultConstructor instance = new DerivedFromSuperClassWithDefaultConstructor(42);

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = {"SuperClassWithDefaultConstructor.constructor()", "DerivedFromSuperClassWithDefaultConstructor.constructor()", "DerivedFromSuperClassWithDefaultConstructor.constructor(int)"};
        // --end-->

        String[] logs = instance.getLogs();

        assertArrayEquals(expected, logs);
    }

    @Test
    void should_call_super_class_constructor_more() {
        DerivedFromSuperClassWithDefaultConstructor instance = new DerivedFromSuperClassWithDefaultConstructor("God");

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = {"SuperClassWithDefaultConstructor.constructor(String)", "DerivedFromSuperClassWithDefaultConstructor.constructor(String)"};
        // --end-->

        String[] logs = instance.getLogs();

        assertArrayEquals(expected, logs);
    }

    @Test
    void should_call_most_derived_methods() {
        BaseClassForOverriding instance = new DerivedFromBaseClassForOverriding();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expectedName = "DerivedFromBaseClassForOverriding";
        // --end-->

        assertEquals(expectedName, instance.getName());
    }

    @Test
    void should_call_super_class_methods() {
        DerivedFromBaseClassForOverridingCallingSuper instance = new DerivedFromBaseClassForOverridingCallingSuper();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expectedName = "BaseClassForOverriding->DerivedFromBaseClassForOverridingCallingSuper";
        // --end-->

        assertEquals(expectedName, instance.getName());
    }

    @SuppressWarnings({"ConstantConditions", "RedundantCast", "UnnecessaryLocalVariable"})
    @Test
    void should_use_caution_when_dealing_with_array_type() {
        //derived[4] -> Parent[]
        DerivedFromSuperClassWithDefaultConstructor derived[] = new DerivedFromSuperClassWithDefaultConstructor[4];
        SuperClassWithDefaultConstructor parent[] = (DerivedFromSuperClassWithDefaultConstructor[])derived;

        boolean willThrow = false;
        try {
            parent[3] = new SuperClassWithDefaultConstructor();
        } catch (Exception error) {
            willThrow = true;
        }
        assertEquals(false, willThrow);

    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    void should_not_make_you_confused() {//sub-class convert to super class instance, when call method, return to results from sub-class
        NestedDerivedClassWithName nested = new NestedDerivedClassWithName();
        DerivedFromBaseClassWithName derived = nested;

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "NestedDerivedClassWithName";
        // --end-->

        assertEquals(expected, derived.getName());
    }

    @Test
    void should_not_make_you_confused_2() {//subclass cast to super class, if subclass has the method, run subclass method, if not, go to the super class
        DerivedFromBaseClassWithName derived = new DerivedFromBaseClassWithName();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "BaseClassWithName";
        // --end-->

        assertEquals(expected, derived.getName());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_use_instance_of_to_determine_inheritance_relationship() {//subclass instance is an instance of all super classes
        NestedDerivedClassWithName nested = new NestedDerivedClassWithName();//declares a subclass instance

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expectedResult1 = Optional.of(true);
        final Optional<Boolean> expectedResult2 = Optional.of(true);
        final Optional<Boolean> expectedResult3 = Optional.of(true);
        // --end-->

        assertEquals(expectedResult1.get(), nested instanceof NestedDerivedClassWithName);
        assertEquals(expectedResult2.get(), nested instanceof DerivedFromBaseClassWithName);
        assertEquals(expectedResult3.get(), nested instanceof BaseClassWithName);
    }

    @SuppressWarnings({"ConstantConditions", "UnnecessaryBoxing"})
    @Test
    void should_use_instance_of_only_in_inheritance_relationship() {//Integer, Long -> Number
        final Object integer = new Integer(42);  // the magic of life

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expectedResult1 = Optional.of(true);
        final Optional<Boolean> expectedResult2 = Optional.of(false);
        // --end-->

        assertEquals(expectedResult1.get(), integer instanceof Integer );
        assertEquals(expectedResult2.get(), integer instanceof Long );
    }

    @SuppressWarnings({"SimplifiableJUnitAssertion", "EqualsWithItself"})
    @Test
    void should_write_perfect_equals_1() {
        PersonForEquals person = new PersonForEquals("James", (short) 1990);

        assertTrue(person.equals(person));
    }

    @SuppressWarnings("SimplifiableJUnitAssertion")
    @Test
    void should_write_perfect_equals_2() {
        PersonForEquals person = new PersonForEquals("James", (short) 1990);
        PersonForEquals samePerson = new PersonForEquals("James", (short) 1990);

        assertTrue(person.equals(samePerson));
        assertTrue(samePerson.equals(person));
    }

    @SuppressWarnings("SimplifiableJUnitAssertion")
    @Test
    void should_write_perfect_equals_3() {
        PersonForEquals person = new PersonForEquals("James", (short) 1990);
        PersonForEquals samePerson = new PersonForEquals("James", (short) 1990);
        PersonForEquals stillTheSamePerson = new PersonForEquals("James", (short) 1990);

        assertTrue(person.equals(samePerson));
        assertTrue(samePerson.equals(stillTheSamePerson));
        assertTrue(person.equals(stillTheSamePerson));
    }

    @SuppressWarnings({"ConstantConditions", "ObjectEqualsNull", "SimplifiableJUnitAssertion"})
    @Test
    void should_write_perfect_equals_4() {
        PersonForEquals person = new PersonForEquals("James", (short) 1990);

        assertFalse(person.equals(null));
    }

    @SuppressWarnings({"EqualsBetweenInconvertibleTypes", "SimplifiableJUnitAssertion", "UnnecessaryBoxing"})
    @Test
    void should_write_perfect_equals_5() {
        PersonForEquals person = new PersonForEquals("James", (short) 1990);
        Integer instanceWithOtherType = new Integer(1990);

        assertFalse(person.equals(instanceWithOtherType));
    }

    @SuppressWarnings("SimplifiableJUnitAssertion")
    @Test
    void should_write_perfect_equals_6() {
        PersonForEquals person = new PersonForEquals("James", (short) 1990);
        PersonForEquals different = new PersonForEquals("James", (short) 1991);

        assertFalse(person.equals(different));
        assertFalse(different.equals(person));
    }

    @Test
    void should_write_perfect_equals_7() {
        PersonForEquals person = new PersonForEquals("James", (short) 1990);
        PersonForEquals different1 = new PersonForEquals("James", (short) 1991);
        PersonForEquals different2 = new PersonForEquals("Harry", (short) 1990);
        PersonForEquals samePerson = new PersonForEquals("James", (short) 1990);

        assertNotEquals(person.hashCode(), different1.hashCode());
        assertNotEquals(person.hashCode(), different2.hashCode());
        assertEquals(person.hashCode(), samePerson.hashCode());
    }

    @Test
    void should_have_right_ranking() {
        PersonForEquals person1 = new PersonForEquals("Alice", (short) 1990);
        PersonForEquals person2 = new PersonForEquals("James", (short) 1992);
        PersonForEquals person3 = new PersonForEquals("James", (short) 1990);
//        PersonForEquals[] result;

//        assertTrue(new PersonForEquals[]{new PersonForEquals("Alice", (short)1990)}.equals(result[0]));
//        assertTrue(new PersonForEquals[]{new PersonForEquals("James", (short)1990)}.equals(result[1]));
//        assertTrue(new PersonForEquals[]{new PersonForEquals("James", (short)1992)}.equals(result[2]));
    }
}

/*
 * - If `Derived` derives from `Base` and `Base` class contains one parameterized constructor, then do you think we
 *   should explicitly call constructor in `Base` from `Derived` class?
 * - Why you should override hashCode while overriding equals?
 * - How to write a perfect equals method?
 */
