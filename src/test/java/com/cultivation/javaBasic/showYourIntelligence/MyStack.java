package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class MyStack {
    private int[] storage;
    private int capacity;
    private int count;
    private static final int GROW_FACTOR = 2;

    public MyStack(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Capacity too small.");
        }

        capacity = initialCapacity;
        storage = new int[initialCapacity];
        count = 0;
    }

    public void push(int value) {
        if (count == capacity) {//capacity = 10
            ensureCapacity();
        }

        // TODO: Please push the value into the storage here.
        // <--start
        //storage = new int[10];
        storage[count++] = value;
        //throw new NotImplementedException();
        // --end-->
    }

    private void ensureCapacity() {
        int newCapacity = capacity * GROW_FACTOR;//2 * capacity
        capacity = newCapacity;
        // TODO: Please create a new array of size newCapacity. And update related fields
        // TODO: You SHOULD NOT USE COLLECTIONS OTHER THAN ARRAY.
        // <--start
        int[] newStorage = new int[capacity];
        for (int i = 0; i < storage.length; i++) {
            newStorage[i] = storage[i];
        }
        storage = newStorage;

        //count--;
        //throw new NotImplementedException();
        // --end-->
    }

    public int[] popToArray() {
        final int totalItemsCount = count;
        int[] array = new int[totalItemsCount];

        while (count > 0) {
            array[totalItemsCount - count] = pop();
        }

        return array;
    }

    private int pop() {
        // TODO: Please pop one element from the array.
        // <--start
        count--;
        int popVal = storage[count];
        return popVal;
        // --end-->

        //throw new UnsupportedOperationException("Stack is empty.");

    }
}
