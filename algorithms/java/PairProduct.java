import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PairProduct {

    public static List<Integer> pairProduct(int[] arr, int target) {
        List<Integer> retVal = new ArrayList<>();

        // A map where I will store the quotient for each division operation as
        // well as the index where the divisor resides from which I derived the
        // quotient.
        Map<Integer, Integer> quotients = new HashMap<>();
        int quotient;
        for (int i = 0; i < arr.length; i++) {
            // First check to see if dividing the target by the current number
            // will result in a remainder
            if (target % arr[i] > 0) {
                continue;
            }
            quotient = target / arr[i];

            // Is i, an existing quotient, already in the map?
            if (quotients.containsKey(arr[i])) {
                // If it is, we have found a pointer to a divisor that will
                // enable us to generate the target number by multiplying the
                // two values together.  Look up the index in the map, and add
                // the two indexes to our result.
                retVal.add(i);
                retVal.add(quotients.get(arr[i]));
                break;
            }
            quotients.put(quotient, i);
        }

        return retVal;
    }

    public static String arrToString(int[] arr) {
        List<String> l = new ArrayList<>();
        for (int i : arr) {
            l.add(Integer.toString(i));
        }
        StringBuffer buf = new StringBuffer();
        buf.append("[");
        buf.append(String.join(",", l));
        buf.append("]");
        return buf.toString();
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 2, 5, 4, 1}; // 10 -> {1, 2}
        int[] arr2 = new int[]{4, 7, 9, 2, 5, 1}; // 5 -> {4, 5}
        int[] arr3 = new int[]{4, 7, 9, 2, 5, 1}; // 35 -> {1, 4}
        int[] arr4 = new int[]{3, 2, 5, 4, 1}; //  10 -> {1, 2}
        int[] arr5 = new int[]{4, 6, 8, 2}; //, 16 -> {2, 3}

        System.out.printf("%s => %s%n", arrToString(arr1), pairProduct(arr1, 10).toString());
        System.out.printf("%s => %s%n", arrToString(arr2), pairProduct(arr2, 5).toString());
        System.out.printf("%s => %s%n", arrToString(arr3), pairProduct(arr3, 35).toString());
        System.out.printf("%s => %s%n", arrToString(arr4), pairProduct(arr4, 10).toString());
        System.out.printf("%s => %s%n", arrToString(arr5), pairProduct(arr5, 16).toString());
    }
}
