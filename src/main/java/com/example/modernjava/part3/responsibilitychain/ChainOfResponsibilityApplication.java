package com.example.modernjava.part3.responsibilitychain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainOfResponsibilityApplication {

    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);

        String input = "Aren't labdas really sexy!?";
        String result = p1.handle(input);
        System.out.println(result);
        // prints "From Raoul, Mario and Alan: Aren't lambdas really sexy!?"

        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckProcessing);
        String result2 = pipeline.apply(input);
        System.out.println(result2);

    }

}
