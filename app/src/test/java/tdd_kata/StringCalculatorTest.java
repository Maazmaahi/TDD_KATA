package tdd_kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();

    @Test
    void testEmptyStringReturnsZero() {
        assertEquals(0, calculator.add(""));  // Expected result: 0
    }

    @Test
    void testSingleNumber() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void testTwoNumbers() {
        assertEquals(6, calculator.add("1,5"));
    }

    @Test
    void testMultipleNumbers() {
        assertEquals(10, calculator.add("1,2,3,4"));
    }

    @Test
    void testNewlineAsDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void testCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2")); // Custom delimiter ';'
    }

    @Test
    void testNegativeNumbersThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3");
        });
        assertEquals("negative numbers not allowed: -2", exception.getMessage());
    }

    @Test
    void testMultipleNegativeNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,-3,4,-5");
        });
        assertEquals("negative numbers not allowed: -2, -3, -5", exception.getMessage());
    }

    @Test
    void testIgnoreNumbersGreaterThan1000() {
        assertEquals(2, calculator.add("2,1001"));
    }

    @Test
    void testCustomDelimiterAnyLength() {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    void testMultipleDelimiters() {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
        assertEquals(10, calculator.add("//[;][&]\n2;3&5"));
    }

    @Test
    void testMultipleLongDelimiters() {
        assertEquals(15, calculator.add("//[***][###]\n4***5###6"));
        assertEquals(10, calculator.add("//[---][@@]\n2---3@@5"));
    }
}
