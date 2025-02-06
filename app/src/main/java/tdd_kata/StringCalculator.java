package tdd_kata;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
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
}
