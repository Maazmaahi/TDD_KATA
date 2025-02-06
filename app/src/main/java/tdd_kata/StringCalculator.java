package tdd_kata;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] parts = numbers.split(",");
        int sum = 0;
        if(parts.length == 1) {
            sum += Integer.parseInt(parts[0]);
        } else {
            sum += Integer.parseInt(parts[0]);
            sum += Integer.parseInt(parts[1]);
        }
        return sum;  
    }
}
