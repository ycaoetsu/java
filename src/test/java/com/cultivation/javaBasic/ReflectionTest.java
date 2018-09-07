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
        Employee instance = (Employee)theClass.newInstance(); //newInstance() -> Object type 强制转换
        // --end-->

        assertEquals("Employee", instance.getTitle());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_print_all_static_methods_of_double() {
        Class<Double> doubleClass = Double.class;

        // TODO: please get all public static declared methods of Double. Sorted in an ascending order
        // <--start
        Method[] methods = doubleClass.getDeclaredMethods();
        List<String> resultList = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
                resultList.add(method.getName());
            }
        }
        String[] publicStaticMethods = resultList.toArray(new String[0]);
        // --end-->
        Arrays.sort(publicStaticMethods);
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
        Object result = employee.getClass().getDeclaredMethod("getTitle").invoke(employee);
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
        // TODO: please get the methods who contains MyAnnotation annotation.
        Class<MethodWithAnnotation> findAnnotation = MethodWithAnnotation.class;

        Method[] methods = findAnnotation.getDeclaredMethods();
        List<String> resultList = new ArrayList<>();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == MyAnnotation.class) {
                    resultList.add(method.getName());
                }
            }
        }
        String[] actualResult = resultList.toArray(new String[0]);
        assertArrayEquals(new String[]{"theMethod"}, actualResult);
    }
    @Test
    void should_child_array_not_parent_array_subclass() {
        String[] s = new String[2];
        Object[] o = new Object[2];
        assertEquals(s.getClass().getSuperclass(), o.getClass());
    }
}


/*
 * - What is the class name of array type?
 * - How to compare 2 classes?
 * - What if the class does not contain a default constructor when you call `newInstance()`?
 * - What is source only annotation? Can we get source only annotations via reflection?
 */
