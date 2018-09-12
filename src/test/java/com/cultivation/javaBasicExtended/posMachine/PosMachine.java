package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {

    private Set<Product> productList = new HashSet<>();

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        StringBuilder sb = new StringBuilder();

        int data = reader.read();
        while (data != -1) {
            sb.append((char)data);
            data = reader.read();
        }
        String resultString = sb.toString();
        ObjectMapper mapper = new ObjectMapper();//create once, reuse
        productList = mapper.readValue(resultString, new TypeReference<Set<Product>>() {
        });
//        for (Product product : productList) {
//            System.out.print(product.getName());
//            System.out.println(product.getPrice());
//        }
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        String line = System.lineSeparator();
        String receiptHeader = "Receipts" + line;
        String receiptSeparateLine = "------------------------------------------------------------" + line;
        String receiptPricePart = "Price: %d" + line;
        String receiptBodyFormat = "%-32s%-11d%d" + line;
        if (barcodeContent == null || barcodeContent == "[]") {
            return receiptHeader + receiptSeparateLine + receiptSeparateLine + String.format(receiptPricePart, 0);
        }
        StringBuilder sb = new StringBuilder();
        List<String> barcodes = new ObjectMapper().readValue(barcodeContent, new TypeReference<List<String>>(){});
        Map<Product, Integer> map = new LinkedHashMap<>();
        int sum = 0;
        for (String barcode : barcodes) {
            int quantity = 1;
            for (Product product : productList) {
                if (product.getId().equals(barcode)) {
                    if (map.containsKey(product)) {
                        quantity = map.get(product) + 1;
                    }
                    map.put(product, quantity);
                }
            }
        }
        StringBuilder receiptBody = new StringBuilder();

//        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
//            receiptBody
//        }
//        String receiptStr = (sb.toString() + "Price:%d" + '\n', sum);
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