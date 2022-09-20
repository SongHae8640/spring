package com.spring.templateCallbackEx;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void sumTest() {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum("data/numbersEx.txt");

        assertEquals(10, sum);
    }
}
