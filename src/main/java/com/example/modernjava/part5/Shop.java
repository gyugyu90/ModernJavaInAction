package com.example.modernjava.part5;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shop {

    private String name;

    private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("ButItAll"), new Shop("ShopEasy"));

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {

//        ex1();
//        ex2();
//        ex3();
        ex4();
    }


    private static void ex1() {
        Shop shop = new Shop("Best Shop");

        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " milliseconds");

        System.out.println("Do something else");

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " milliseconds");
    }

    private static void ex2() {

        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + invocationTime + "msecs");

    }

    private static void ex3() {
        long start = System.nanoTime();
        System.out.println(findPrices3("myPhone27S"));
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + invocationTime + "msecs");
    }

    private static void ex4() {
        long start = System.nanoTime();

        CompletableFuture[] futures = findPricesStream("myPhone275")
                .map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();

        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + invocationTime + "msecs");
    }

    private static List<String> findPrices(String product) {

        List<CompletableFuture<String>> priceFutures = shops.stream().map(
                shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(product)
                )
        ).collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static List<String> findPrices2(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    private static List<String> findPrices3(String product) {

        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });

        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private static Stream<CompletableFuture<String>> findPricesStream(String product) {

        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });

        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }


    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }


    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}
