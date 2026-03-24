import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class KPairsSmallestSumsRedux {

    public static void printResult(int[] a, int[] b, int k, List<int[]> result) {
        List<String> pairs = new ArrayList<>(result.size());
        for (int[] i : result) {
            pairs.add(arrToString(i));
        }
        System.out.printf("%s, %s, k=%d, result=%s%n", arrToString(a), arrToString(b), k, pairs);
    }

    public static String arrToString(int[] arr) {
        List<Integer> l = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            l.add(arr[i]);
        }
        return l.toString();
    }

    public static int[] parseInputArr(String input) {
        String[] tokens = input.split("\\s+");
        int[] retVal = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            retVal[i] = Integer.parseInt(tokens[i]);
        }
        return retVal;
    }


    public static List<int[]> kPairsSmallestSums(int[] a, int[] b, int k) {
        // Adjust k to ensure that we aren't asking for more permutations than
        // are possible.
        int totalPerms = a.length * b.length;
        if (totalPerms > k) {
            k = totalPerms;
        }

        List<int[]> retVal = new ArrayList<>();
        while (k > 0) {


            k--;
        }


        return retVal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf(
                "Enter two sorted arrays and the number of k smallest pairs to find. " +
                "Each array should be a space delimited set of integers. " +
                "Each overal element of the input, the arrays and the k value " +
                "should be separated by a comma. Example: 1 2 3, 7 11, 2%n");
            String[] tokens = scanner.nextLine().split(",");
            int k = Integer.parseInt(tokens[2].strip());
            int[] a = parseInputArr(tokens[0].strip());
            int[] b = parseInputArr(tokens[1].strip());
            printResult(a, b, k, kPairsSmallestSums(a, b, k));
        }
    }
}
