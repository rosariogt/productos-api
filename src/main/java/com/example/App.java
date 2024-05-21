package com.example;

import com.google.gson.Gson;
import spark.Spark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

public class App {
    public static void main(String[] args) {
        Spark.before((request, response)->{
            response.header("Access-Control-Allow-Origin", "*");
        });
        get("/hello", (req, res) -> "Hello, World!");

        List<Producto> productList = new ArrayList<>();
        productList.add(new Producto(1L, "Jugo de naranja", "PROD-1" , BigDecimal.ONE));
        productList.add(new Producto(2L, "Jugo de manzana", "PROD-2" , BigDecimal.TEN));
        productList.add(new Producto(3L, "Jugo de durazno", "PROD-3" , BigDecimal.valueOf(4)));
        productList.add(new Producto(4L, "Jugo de maracuya", "PROD-4" , BigDecimal.valueOf(5)));

        get("/productos", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(productList);
        });

        System.out.println("Iniciando server " + port() + " " + LocalDate.now());
    }
}
