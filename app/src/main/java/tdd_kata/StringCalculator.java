package tdd_kata;

public class StringCalculator {
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
}
