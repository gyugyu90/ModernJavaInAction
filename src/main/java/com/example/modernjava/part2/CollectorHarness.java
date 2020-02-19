package com.example.modernjava.part2;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class CollectorHarness {

    public static void main(String[] args) {
        int million = 1_000_000;
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(million); // 100만개의 숫자를 소수와 비소수로 분할
            long duration = (System.nanoTime() - start) / million;
            if (duration < fastest) fastest = duration;
        }

        System.out.println("Fastest execution done in " + fastest + " msecs");
    }


    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

}
