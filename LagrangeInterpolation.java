package Secret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LagrangeInterpolation {

    // Function to convert value in any base to base 10
    public static long convertToBase10(String value, int base) {
        return Long.parseLong(value, base);
    }

    // Function to parse the input and return base 10 values of roots
    public static List<long[]> parseInput() {
        // Simulating JSON parsing using native methods
        Map<String, Map<String, String>> data = new HashMap<>();

        // Manually filling the data map since we don't have a JSON library
        Map<String, String> keys = new HashMap<>();
        keys.put("n", "9");
        keys.put("k", "6");
        data.put("keys", keys);

        Map<String, String> root1 = new HashMap<>();
        root1.put("base", "10");
        root1.put("value", "28735619723837");
        data.put("1", root1);

        Map<String, String> root2 = new HashMap<>();
        root2.put("base", "16");
        root2.put("value", "1A228867F0CA");
        data.put("2", root2);

        Map<String, String> root3 = new HashMap<>();
        root3.put("base", "12");
        root3.put("value", "32811A4AA0B7B");
        data.put("3", root3);

        Map<String, String> root4 = new HashMap<>();
        root4.put("base", "11");
        root4.put("value", "917978721331A");
        data.put("4", root4);

        Map<String, String> root5 = new HashMap<>();
        root5.put("base", "16");
        root5.put("value", "1A22886782E1");
        data.put("5", root5);

        Map<String, String> root6 = new HashMap<>();
        root6.put("base", "10");
        root6.put("value", "28735619654702");
        data.put("6", root6);

        Map<String, String> root7 = new HashMap<>();
        root7.put("base", "14");
        root7.put("value", "71AB5070CC4B");
        data.put("7", root7);

        Map<String, String> root8 = new HashMap<>();
        root8.put("base", "9");
        root8.put("value", "122662581541670");
        data.put("8", root8);

        Map<String, String> root9 = new HashMap<>();
        root9.put("base", "8");
        root9.put("value", "642121030037605");
        data.put("9", root9);

        // Parse the points from the input
        List<long[]> points = new ArrayList<>();

        for (String key : data.keySet()) {
            if (key.matches("\\d+")) {  // Only consider the root keys
                Map<String, String> root = data.get(key);
                int base = Integer.parseInt(root.get("base"));
                String value = root.get("value");
                int xValue = Integer.parseInt(key);  // This is the x-value
                long yValue = convertToBase10(value, base);  // Convert y-value to base 10
                points.add(new long[]{xValue, yValue});
            }
        }

        return points;
    }

    // Lagrange Interpolation to find the polynomial constant term (c)
    public static double lagrangeInterpolation(List<long[]> points, int xValue) {
        double total = 0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            long xi = points.get(i)[0];
            double yi = points.get(i)[1];

            // Compute the Lagrange basis polynomial
            double term = yi;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    long xj = points.get(j)[0];
                    term *= (double) (xValue - xj) / (xi - xj);
                }
            }

            total += term;
        }

        return total;
    }

    public static void main(String[] args) {
        // Parse the input
        List<long[]> points = parseInput();

        // Perform Lagrange interpolation to find the constant term 'c' (at x=0)
        double constantTerm = lagrangeInterpolation(points, 0);

        // Print the result
        System.out.println("The constant term (c) of the polynomial is: " + constantTerm);
    }
}
