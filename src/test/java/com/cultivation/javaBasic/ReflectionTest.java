package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.MethodWithAnnotation;
import com.cultivation.javaBasic.util.MyAnnotation;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReflectionTest {

    @Test
    void should_be_able_to_get_class_object() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final Class<? extends Employee> expected = Employee.class;
        // --end-->

        assertEquals(expected, employeeClass);
    }

    @Test
    void should_be_able_to_get_full_qualified_name() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "com.cultivation.javaBasic.util.Employee";
        // --end-->

        assertEquals(expected, employeeClass.getName());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_instantiate_types_at_runtime() throws Exception {
        Class<?> theClass = Class.forName("com.cultivation.javaBasic.util.Employee");

        // TODO: please created an instance described by `theClass`
        // <--start
        Employee instance = new Employee();
        // --end-->

        assertEquals("Employee", instance.getTitle());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_print_all_static_methods_of_double() {
        Class<Double> doubleClass = Double.class;

        // TODO: please get all public static declared methods of Double. Sorted in an ascending order
        // <--start
        Method[] getReflectClassMethods = doubleClass.getDeclaredMethods();
        //String[] publicStaticMethods = new String[getReflectClassMethods.length];
        List<String> resultList = new ArrayList<>();
        for (Method method : getReflectClassMethods) {
            if (Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
                resultList.add(method.getName());
            }
        }
        /*for (int i = 0; i < publicStaticMethods.length; i++) {
            if (getReflectClassMethods[i].getModifiers())
            publicStaticMethods[i] = getReflectClassMethods[i].getName();
        } */
        String[] publicStaticMethods = resultList.toArray(new String[resultList.size()]);//???
        Arrays.sort(publicStaticMethods);
        // --end-->

        final String[] expected = {
            "compare", "doubleToLongBits", "doubleToRawLongBits", "hashCode",
            "isFinite", "isInfinite", "isNaN", "longBitsToDouble", "max",
            "min", "parseDouble", "sum", "toHexString", "toString", "valueOf",
            "valueOf"
        };

        assertArrayEquals(expected, publicStaticMethods);
    }

    @SuppressWarnings({"unused", "ConstantConditions", "RedundantThrows"})
    @Test
    void should_be_able_to_evaluate_object_field_values_at_runtime() throws Exception {
        Object employee = new Employee();

        // TODO: please get the value of `getTitle` method using reflection. No casting to Employee is allowed.
        // <--start
        //System.out.print(employee.getClass().toString());
        Object result = employee.getClass().getDeclaredMethod("getTitle", new Class[]{}).invoke(employee);
        // --end-->

        assertEquals("Employee", result);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_be_able_to_get_the_item_class_of_the_array() {
        Object employees = new Employee[0];

        // TODO: please get the class of array item `employees`
        // <--start
        Class<?> itemClass = employees.getClass().getComponentType();
        // --end-->

        assertEquals(Employee.class, itemClass);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_get_the_methods_who_contains_MyAnnotation_annotation() {
        Class<MethodWithAnnotation> theClass = MethodWithAnnotation.class;

        // TODO: please get the methods who contains MyAnnotation annotation.
        // <--start
        Method[] methods = theClass.getMethods();
        //Annotation[] annotations = theClass.getAnnotations();
        List<String> myAnnotations = new ArrayList<>();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == MyAnnotation.class) {
                    myAnnotations.add(method.getName());
                }
            }
        }
        String[] methodsContainsAnnotations = myAnnotations.toArray(new String[0]);
        // --end-->

        assertArrayEquals(new String[] {"theMethod"}, methodsContainsAnnotations);
    }
}

/*
 * - What is the class name of array type?
 * - How to compare 2 classes?
 * - What if the class does not contain a default constructor when you call `newInstance()`?
 * - What is source only annotation? Can we get source only annotations via reflection?
 */
