package com.example.modernjava.part2;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class PracticalExample {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        solution1(transactions);
        solution2(transactions);
        solution3(transactions);
        solution4(transactions);
        solution5(transactions);
        solution6(transactions);
        solution7(transactions);
        solution8(transactions);
    }

    private static void solution1(List<Transaction> transactions) {
        System.out.println("SOL1");
        List<Transaction> list = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println();
    }

    private static void solution2(List<Transaction> transactions) {
        System.out.println("SOL2");
        List<String> list = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println();
    }

    private static void solution3(List<Transaction> transactions) {
        System.out.println("SOL3");
        List<Trader> list = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println();
    }

    private static void solution4(List<Transaction> transactions) {
        System.out.println("SOL4");
        List<String> list = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println();
    }

    private static void solution5(List<Transaction> transactions) {
        System.out.println("SOL5");
        boolean flag = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .anyMatch("Milan"::equals);
        System.out.println(flag);
        System.out.println();
    }

    private static void solution6(List<Transaction> transactions) {
        System.out.println("SOL6");
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .forEach(transaction -> System.out.println(transaction.getValue()));
        System.out.println();
    }

    private static void solution7(List<Transaction> transactions) {
        System.out.println("SOL7");
        OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .reduce(Integer::max);
        max.ifPresentOrElse(System.out::println, () -> System.out.println("no values"));
        System.out.println();
    }

    private static void solution8(List<Transaction> transactions) {
        System.out.println("SOL8");
        OptionalInt max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .reduce(Integer::min);
        max.ifPresentOrElse(System.out::println, () -> System.out.println("no values"));
        System.out.println();
    }
}
