# 📌 String Calculator TDD Kata – Step-by-Step Implementation  

## 📖 Introduction  
This project implements a **String Calculator** using **Test-Driven Development (TDD)**.  

The goal is to write **clean, testable, and maintainable** code by following TDD principles.  

---

## 📜 Requirements & Rules  

🛠 Technologies Used
✅ Java – Core language.
✅ JUnit 5 – Unit testing framework.
✅ Maven – Dependency management (optional).

### **Basic Functionality**  

*   **Empty String:**  An empty string input returns 0.
- Implement a method:  
  ```java
    // Test
    @Test
    void testEmptyStringReturnsZero() {
        assertEquals(0, calculator.add(""));  // Expected result: 0
    }

    // Implementation
    public int add(String numbers) {
        return 0;  
    }
    ```

*   **Single Number:** A single number input returns the number itself.

    ```java
    // Test
    @Test
    void testSingleNumber() {
        assertEquals(1, calculator.add("1"));
    }

    // Implementation
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(numbers);  
    }
    ```

*   **Two Numbers:** Two comma-separated numbers return their sum.

    ```java
    // Test
    @Test
    void testTwoNumbers() {
        assertEquals(6, calculator.add("1,5"));
    }

    // Implementation
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] parts = numbers.split(",");
        int sum = 0;
        for (String num : parts) {
            sum += Integer.parseInt(num);
        }
        return sum;  
    }
    ```

*   **Any Amount of Numbers:** The calculator can handle any number of comma-separated inputs.

    ```java
    // Test
    @Test
    void testMultipleNumbers() {
        assertEquals(10, calculator.add("1,2,3,4"));
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] parts = numbers.split(",");
        int sum = 0;
        for (String num : parts) {
            sum += Integer.parseInt(num);
        }
        return sum;  
    }
    ```

*   **Newlines as Delimiters:** Newlines can be used as delimiters in addition to commas.

    ```java
    // Test
    @Test
    void testNewlineAsDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] parts = numbers.split("[,\n]");  // Split by comma OR newline
        int sum = 0;
        for (String num : parts) {
            sum += Integer.parseInt(num);
        }
        return sum;  
    }
    ```

*   **Custom Delimiters:** The input string can specify a custom delimiter using the format `//[delimiter]\n[numbers…]`.

    ```java
    // Test
    @Test
    void testCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2")); // Custom delimiter ';'
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";  // Default delimiters: comma and newline
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex); // Extract delimiter
            numbers = numbers.substring(delimiterIndex + 1);  // Remove delimiter line
        }
    
        String[] parts = numbers.split(delimiter);


        int sum = 0;
        for (String num : parts) {
            sum += Integer.parseInt(num);
        }
        return sum;  
    }
    ```

*   **Negative Single Numbers:** Calling `add` with a negative number throws an exception with a clear message indicating the negative number(s).

    ```java
    // Test
    @Test
    void testNegativeNumbersThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3");
        });
        assertEquals("negative numbers not allowed: -2", exception.getMessage());
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";  // Default delimiters: comma and newline
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex); // Extract delimiter
            numbers = numbers.substring(delimiterIndex + 1);  // Remove delimiter line
        }
    
        String[] parts = numbers.split(delimiter);


        int sum = 0;
        for (String num : parts) {
            int value = Integer.parseInt(num);
        if (value < 0) {
            throw new IllegalArgumentException("negative numbers not allowed: " + value);
        } else {
            sum += value;
        }
        }
        return sum;  
    }
    ```

*   **Negative Multiple Numbers:** Calling `add` with a negative number throws an exception with a clear message indicating the negative number(s).

    ```java
    // Test
    @Test
    void testMultipleNegativeNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,-3,4,-5");
        });
        assertEquals("negative numbers not allowed: -2, -3, -5", exception.getMessage());
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";  // Default delimiters: comma and newline
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex); // Extract delimiter
            numbers = numbers.substring(delimiterIndex + 1);  // Remove delimiter line
        }
    
        String[] parts = numbers.split(delimiter);
        List<Integer> negatives = new ArrayList<>();

        int sum = 0;
        for (String num : parts) {
            int value = Integer.parseInt(num);
            if (value < 0) {
                negatives.add(value);
            } else {
                sum += value;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " + negatives.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
        }

        return sum;  
    }
    ```

*   **Numbers > 1000:** Numbers greater than 1000 are ignored in the calculation.

    ```java
    // Test
    @Test
    void testIgnoreNumbersGreaterThan1000() {
        assertEquals(2, calculator.add("2,1001"));
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";  // Default delimiters: comma and newline
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex); // Extract delimiter
            numbers = numbers.substring(delimiterIndex + 1);  // Remove delimiter line
        }
    
        String[] parts = numbers.split(delimiter);
        List<Integer> negatives = new ArrayList<>();

        int sum = 0;
        for (String num : parts) {
            int value = Integer.parseInt(num);
            if (value < 0) {
                negatives.add(value);
            } else if (value <= 1000) {
                sum += value;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " + negatives.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
        }

        return sum;  
    }
    ```

*   **Custom Delimiter Any Length:** Delimiters can be of any length (e.g., `//[***]\n1***2***3`).

    ```java
    // Test
    @Test
    void testCustomDelimiterAnyLength() {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";  // Default delimiters: comma and newline
        if (numbers.startsWith("//")) {
            numbers = numbers.substring(2);
            if (numbers.startsWith("[")) {
                Matcher matcher = Pattern.compile("\\[(.+?)]").matcher(numbers);
                StringBuilder delimiters = new StringBuilder();
                while (matcher.find()) {
                    if (delimiters.length() > 0) delimiters.append("|");
                    delimiters.append(Pattern.quote(matcher.group(1))); // Multiple or long delimiters
                }
                delimiter = delimiters.toString();
            } else {
                delimiter = Pattern.quote(numbers.substring(0, 1)); // Single-character delimiter
            }
            numbers = numbers.substring(numbers.indexOf("\n") + 1);
        }
        String[] parts = numbers.split(delimiter);
        List<Integer> negatives = new ArrayList<>();

        int sum = 0;
        for (String num : parts) {
            if (!num.isEmpty()) {
                int value = Integer.parseInt(num);
                if (value < 0) {
                    negatives.add(value);
                } else if (value <= 1000) {
                    sum += value; // Ignore numbers > 1000
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " + negatives.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
        }

        return sum;  
    }
    ```

*   **Multiple Delimiters:** The input can specify multiple delimiters using the format `//[delim1][delim2]\n[numbers…]`.  This includes handling combinations of long and short delimiters.

    ```dart
    // Test
    @Test
    void testMultipleDelimiters() {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
        assertEquals(10, calculator.add("//[;][&]\n2;3&5"));
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";  // Default delimiters: comma and newline
        if (numbers.startsWith("//")) {
            numbers = numbers.substring(2);
            if (numbers.startsWith("[")) {
                Matcher matcher = Pattern.compile("\\[(.+?)]").matcher(numbers);
                StringBuilder delimiters = new StringBuilder();
                while (matcher.find()) {
                    if (delimiters.length() > 0) delimiters.append("|");
                    delimiters.append(Pattern.quote(matcher.group(1))); // Multiple or long delimiters
                }
                delimiter = delimiters.toString();
            } else {
                delimiter = Pattern.quote(numbers.substring(0, 1)); // Single-character delimiter
            }
            numbers = numbers.substring(numbers.indexOf("\n") + 1);
        }
        String[] parts = numbers.split(delimiter);
        List<Integer> negatives = new ArrayList<>();

        int sum = 0;
        for (String num : parts) {
            if (!num.isEmpty()) {
                int value = Integer.parseInt(num);
                if (value < 0) {
                    negatives.add(value);
                } else if (value <= 1000) {
                    sum += value; // Ignore numbers > 1000
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " + negatives.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
        }

        return sum;  
    }
    ```

*   **Long Multiple Delimiters:** The input can specify multiple delimiters using the format `//[delim1][delim2]\n[numbers…]`.  This includes handling combinations of long and short delimiters.

```java
    // Test
    @Test
    void testMultipleLongDelimiters() {
        assertEquals(15, calculator.add("//[***][###]\n4***5###6"));
        assertEquals(10, calculator.add("//[---][@@]\n2---3@@5"));
    }

    // Implementation (Refactored)
    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String delimiter = "[,\n]";  // Default delimiters: comma and newline
        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf("\n");
            String delimiterSection = numbers.substring(2, newlineIndex); // Extract delimiter part
            numbers = numbers.substring(newlineIndex + 1); // Extract actual numbers
        
            if (delimiterSection.startsWith("[")) {
                // Extract multiple delimiters enclosed in square brackets
                Matcher matcher = Pattern.compile("\\[(.+?)]").matcher(delimiterSection);
                List<String> delimiterList = new ArrayList<>();
                
                while (matcher.find()) {
                    delimiterList.add(Pattern.quote(matcher.group(1))); // Escape special characters
                }
                delimiter = String.join("|", delimiterList); // Combine multiple delimiters
            } else {
                delimiter = Pattern.quote(delimiterSection); // Single-character delimiter
            }
        }
        
    
        String[] parts = numbers.split(delimiter);
        List<Integer> negatives = new ArrayList<>();

        int sum = 0;
        for (String num : parts) {
            if (!num.isEmpty()) {
                int value = Integer.parseInt(num);
                if (value < 0) {
                    negatives.add(value);
                } else if (value <= 1000) {
                    sum += value; // Ignore numbers > 1000
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " + negatives.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
        }

        return sum;  
    }
    ```

## Code Structure

The project consists of the following files:

*   `StringCalculator.java`: Contains the implementation of the `add` method.
*   `StringCalculatorTest.java`: Contains the unit tests for the `add` method, using the `JUnit` package.

## 🚀 Setup & Running the Tests

1.  Ensure you have the java installed.
2.  Clone this repository.
3.  Navigate to the project directory in your terminal.
4.  Run the command `mvn test` or if using a direct Java approach: `javac StringCalculatorTest.java, java StringCalculatorTest`.

All tests should pass, indicating that the String Calculator is functioning correctly.



## Screenshots

I have included screenshots in the `resources` folder demonstrating both passing and failing test cases for each feature.  These screenshots provide visual evidence of the TDD process and the correctness of the implementation.  The filenames are descriptive, indicating the feature being tested.

## Challenges and Learnings

*   Handling multiple delimiters of varying lengths required careful consideration and refactoring.
*   Ensuring comprehensive test coverage for all edge cases was a key focus.

## Conclusion

This project demonstrates my ability to apply TDD principles effectively and produce well-tested, maintainable code. I am confident in my skills as a software craftsperson and eager to contribute to a team that values quality and best practices.

Thank you for considering my application.  I look forward to discussing this assessment further in the next stages of the interview process.


