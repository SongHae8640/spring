package com.spring.templateCallbackEx;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculatorTest {

    @Test
    public void getFileTest() throws IOException {
        Calculator calculator = new Calculator();
        File file = calculator.getFile("data/numbersEx.txt");
        System.out.println(file.toString());

        assertNotNull(file);

    }

    @Test
    public void sumTest() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum("data/numbersEx.txt");

        assertEquals(10, sum);
    }
}
