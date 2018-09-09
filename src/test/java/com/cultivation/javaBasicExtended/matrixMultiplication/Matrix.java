package com.cultivation.javaBasicExtended.matrixMultiplication;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings({"WeakerAccess", "unused"})
class Matrix {
    private final int[][] storage;

    public int rows() {return storage.length;}

    public int columns() {return storage[0].length;}

    public Matrix(int[][] matrixArray) {
        // TODO: please implement the constructor of a matrix.
        // <--start
        storage = matrixArray;
        if (storage == null) {
            throw new IllegalArgumentException("Raw matrix is null");
        }
        if (this.rows() == 0) {
            throw new IllegalArgumentException("Raw matrix contains 0 row");
        }
        for (int rowNum = 0; rowNum < this.rows(); rowNum++) {
            if (storage[rowNum] == null) {
                throw new IllegalArgumentException("Raw matrix contains null row");
            }
            if (storage[rowNum].length == 0) {
                throw new IllegalArgumentException("At least one row of raw matrix contains 0 column");
            }
            if (storage[rowNum].length != this.columns()) {
                throw new IllegalArgumentException("Raw matrix is not rectangle");
            }
        }
        // --end-->
    }

    public static Matrix multiply(Matrix left, Matrix right) {//M1{3 * 5} M2{5 * 2}
        // TODO: please implement the method to pass the tests.
        // <--start
        if (left == null || right == null) {
            throw new IllegalArgumentException();
        }
        if (left.columns() != right.rows()) {
            throw new IllegalArgumentException();
        }
        Matrix resultMatrix = new Matrix(new int[left.rows()][right.columns()]);

        //try {
            for (int i = 0; i < left.rows(); i++) {
                for (int j = 0; j < right.columns(); j++) {
                    for (int k = 0; k < left.columns(); k++) {
                        resultMatrix.storage[i][j] += left.storage[i][k] * right.storage[k][j];
                    }
                }
            }
            return resultMatrix;
        //} catch (IllegalArgumentException error) {
          //  throw new IllegalArgumentException();
        //}
        // --end-->
        //throw new NotImplementedException();
    }

    // TODO: you can add some helper method if you like.
    // <--start

    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) { throw new IllegalArgumentException(); }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (this == obj) { return true; }
        if (Matrix.class != obj.getClass()) { return false; }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) { return false; }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
            .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}
