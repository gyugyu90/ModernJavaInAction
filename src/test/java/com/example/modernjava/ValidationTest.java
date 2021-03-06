package com.example.modernjava;

import com.example.modernjava.part3.strategy.IsAllLowerCase;
import com.example.modernjava.part3.strategy.IsNumeric;
import com.example.modernjava.part3.strategy.Validator;
import com.example.modernjava.part3.templatemethod.Customer;
import com.example.modernjava.part3.templatemethod.OnlineBankingLambda;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ValidationTest {

    @Test
    public void testValidation() {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        assertFalse(b1);

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        assertTrue(b2);

        Validator numbericValidator2 = new Validator(s -> s.matches("\\d+"));
        boolean b3 = numbericValidator2.validate("aaaa");
        assertFalse(b3);

        Validator lowerCaseValidator2 = new Validator(s -> s.matches("[a-z]+"));
        boolean b4 = lowerCaseValidator2.validate("aaaa");
        assertTrue(b4);
    }

    @Test
    public void testOnlineBanking() {
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> {
            System.out.println("Hello");
        });
    }
}
