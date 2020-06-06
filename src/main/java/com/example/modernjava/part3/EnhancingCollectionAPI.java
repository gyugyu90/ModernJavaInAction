package com.example.modernjava.part3;

import com.example.modernjava.part2.Trader;
import com.example.modernjava.part2.Transaction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class EnhancingCollectionAPI {

    public static void main(String[] args) {
        List<String> friends1 = new ArrayList<>();
        friends1.add("Raphael");
        friends1.add("Olivia");
        friends1.add("Thibaut");

        List<String> friends2 = Arrays.asList("Raphael", "Olivia"); // fixed size
        friends2.set(0, "Richard");
//        friends.add("Thibaut"); // UnsupportedOperationException

        List<String> friends3 = List.of("Raphael", "Olivia", "Thibaut"); // immutable list
//        friends3.add("Chih-Chun"); // UnsupportedOperationException

        Set<String> friendsSet = Set.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friendsSet);

//        Set<String> friendsSet2 = Set.of("Raphael", "Olivia", "Olivia"); // 중복 요소로 인해 Illegal Argument Exception

        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        System.out.println(ageOfFriends);

        Map<String, Integer> ageOfFriends2 = Map.ofEntries(entry("Raphael", 30), entry("Olivia", 25), entry("Thibaut", 26));


        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new Trader("A", "a"), 100, 1000));
        transactions.add(new Transaction(new Trader("B", "b"), 200, 2000));
//        for (Transaction transaction: transactions) {
//            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
//                transactions.remove(transaction);
//            }
//        }
        Iterator<Transaction> iterator = transactions.iterator();
        while(iterator.hasNext()) {
            Transaction transaction = iterator.next();
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                iterator.remove();
            }
        }

        transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));

        List<String> referenceCodes = Arrays.asList("a7sj13d", "bidj3fd", "psk39kcd");
        referenceCodes.stream()
                .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
        System.out.println(referenceCodes);

        for (Map.Entry<String, Integer> entry: ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
        }

        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));

        Map<String, String> favoriteMoviews = Map.ofEntries(
                entry("Cristina", "Matrix"),
                entry("Raphael", "Star Wars"),
                entry("Olivia", "James Bond")
        );

        favoriteMoviews.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        System.out.println(favoriteMoviews.getOrDefault("Olivia", "Matrix"));
        System.out.println(favoriteMoviews.getOrDefault("Thibaut", "Matrix"));

        try {
            Map<String, byte[]> dataToHash = new HashMap<>();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        Map<String, String> family = Map.ofEntries(entry("Teo", "Star Wars"), entry("Cristina", "James Bond"));
        Map<String, String> friends = Map.ofEntries(entry("Raphael", "Star Wars"), entry("Cristina", "Matrix"));
        Map<String, String> everyone = new HashMap<>(family);
//        everyone.putAll(friends);

//        System.out.println(everyone);

        friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        System.out.println(everyone);

        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        long parallelismThreshold = 1;

//        Optional<Integer> maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
    }


}
