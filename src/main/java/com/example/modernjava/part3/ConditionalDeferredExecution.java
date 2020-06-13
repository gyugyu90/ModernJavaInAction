package com.example.modernjava.part3;


import com.example.modernjava.part3.Logger.Level;

public class ConditionalDeferredExecution {

    private static Logger logger = Logger.getInstance(Level.FINER);

    public static void main(String[] args) {
        logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic());
    }

    private static String generateDiagnostic() {
        return "Something went wrong!!";
    }



}
