package com.cultivation.javaBasicExtended.posMachine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {

    Set<Product> productSet = new HashSet<>();

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        StringBuilder sb = new StringBuilder();

        int data = reader.read();
        while (data != -1) {
            sb.append((char)data);
            data = reader.read();
        }
        String[] products = sb.toString().split("\n");
        for (int i = 1; i < products.length - 1; i++) {
            String cur = products[i];
            Product product = new Product();
            for (int j = 0; j < cur.length(); j++) {

            }
        }
        System.out.print(products[1]);
        throw new NotImplementedException();
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if (barcodeContent == null || barcodeContent == "[]") {
            return "Receipts" + '\n' +
                    "------------------------------------------------------------" + '\n' +
                    "------------------------------------------------------------" + '\n' +
                    "Price: 0" + '\n';
        }
        throw new NotImplementedException();
        // --end-->
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}