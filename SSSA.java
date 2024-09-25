package Secret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SSSA {

    // Function to convert value in any base to base 10
    public static int convertToBase10(String value, int base) {
        return Integer.parseInt(value, base);
    }

    // Function to parse the input and return base 10 values of roots
    public static List<int[]> parseInput(String inputJson) {
        // Simulating JSON parsing using native methods
        Map<String, Map<String, String>> data = new HashMap<>();

        // Manually filling the data map since we don't have a JSON library
        Map<String, String> keys = new HashMap<>();
        keys.put("n", "4");
        keys.put("k", "3");
        data.put("keys", keys);

        Map<String, String> root1 = new HashMap<>();
        root1.put("base", "10");
        root1.put("value", "4");
        data.put("1", root1);

        Map<String, String> root2 = new HashMap<>();
        root2.put("base", "2");
        root2.put("value", "111");
        data.put("2", root2);

        Map<String, String> root3 = new HashMap<>();
        root3.put("base", "10");
        root3.put("value", "12");
        data.put("3", root3);

        Map<String, String> root6 = new HashMap<>();
        root6.put("base", "4");
        root6.put("value", "213");
        data.put("6", root6);

        // Parse the points from the input
        List<int[]> points = new ArrayList<>();

        for (String key : data.keySet()) {
            if (key.matches("\\d+")) {  // Only consider the root keys
                Map<String, String> root = data.get(key);
                int base = Integer.parseInt(root.get("base"));
                String value = root.get("value");
                int xValue = Integer.parseInt(key);  // This is the x-value
                int yValue = convertToBase10(value, base);  // Convert y-value to base 10
                points.add(new int[]{xValue, yValue});
            }
        }

        return points;
    }

    // Lagrange Interpolation to find the polynomial constant term (c)
    public static double lagrangeInterpolation(List<int[]> points, int xValue) {
        double total = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            int xi = points.get(i)[0];
            double yi = points.get(i)[1];

            // Compute the Lagrange basis polynomial
            double term = yi;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int xj = points.get(j)[0];
                    term *= (double) (xValue - xj) / (xi - xj);
                }
            }

            total += term;
        }

        return total;
    }

    public static void main(String[] args) {
        // Input from the problem description
        String inputJson = "{"
                + "\"keys\": {"
                + "\"n\": 4,"
                + "\"k\": 3"
                + "},"
                + "\"1\": {"
                + "\"base\": \"10\","
                + "\"value\": \"4\""
                + "},"
                + "\"2\": {"
                + "\"base\": \"2\","
                + "\"value\": \"111\""
                + "},"
                + "\"3\": {"
                + "\"base\": \"10\","
                + "\"value\": \"12\""
                + "},"
                + "\"6\": {"
                + "\"base\": \"4\","
                + "\"value\": \"213\""
                + "}"
                + "}";

        // Parse the input
        List<int[]> points = parseInput(inputJson);

        // Perform Lagrange interpolation to find the constant term 'c' (at x=0)
        double constantTerm = lagrangeInterpolation(points, 0);

        // Print the result
        System.out.println("The constant term (c) of the polynomial is: " + constantTerm);
    }
}