package com.example.modernjava.part5;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int x = 1337;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> y = executorService.submit(() -> f(x));
        Future<Integer> z = executorService.submit(() -> g(x));

        System.out.println(y.get() + z.get());

        executorService.shutdown();

        execute();
    }

    private static int f(int x) {
        return x * x;
    }

    private static int g(int x) {
        return 2 * x;
    }

    private static void execute() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return (double) 0;
            }
        });
        System.out.println("hello world");

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (ExecutionException ee) {
            ee.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}
