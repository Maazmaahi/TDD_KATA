package tdd_kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
