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
}
