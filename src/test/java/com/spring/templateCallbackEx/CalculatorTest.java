package com.spring.templateCallbackEx;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp(){
        this.calculator = new Calculator();
    }

    @Test
    public void sumTest() {
        int sum = calculator.calcSum("data/numbersEx.txt");
        assertEquals(10, sum);
    }

    @Test
    public void multiplyTest(){
        assertEquals(24 ,calculator.calcMultiply("data/numbersEx.txt"));

    }
}
