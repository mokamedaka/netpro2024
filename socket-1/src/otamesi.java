import static org.junit.jupiter.api.Assertions.*;

public class otamesi {
  

    public static int sumOfIntegers(int p, int q) {
        int sum = 0;
        for (int i = p; i <= q; i++) {
            sum += i;
        }
        return sum;
    }
}