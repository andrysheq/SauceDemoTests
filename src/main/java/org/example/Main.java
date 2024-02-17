package org.example;

import com.codeborne.selenide.logevents.SelenideLogger;
import org.example.models.Product;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
    }

    @BeforeSuite
    public void initialize(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream("src/main/resources/products.txt")));
            List<String> productDetails = new ArrayList<>();
            String productDetailsFromTextFile = reader.readLine();
            while(productDetailsFromTextFile!=null){
                Collections.addAll(productDetails,productDetailsFromTextFile.split("/"));
                Product product = new Product(productDetails.get(0),productDetails.get(1),productDetails.get(2));
                Product.expectedProductsList.add(product);
                productDetailsFromTextFile = reader.readLine();
            }
            Product.expectedProductsList.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println("Некорректно введен путь файла");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}