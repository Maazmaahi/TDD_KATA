package tdd_kata;

public class StringCalculator {
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
}
