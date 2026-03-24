import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class KPairsSmallestSums {

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

    public static void bumpMaxIdx(int[] a, int[] b, int[] idxs) {
        if (a[idxs[0]] < b[idxs[1]]) {
            if ((idxs[1] + 1) > b.length - 1) {
                // Bump the index for the a array
                idxs[0] = idxs[0] +1;
            } else {
                idxs[1] = idxs[1] +1;
            }
        } else {

//                // Bump the index for the b array
//                idxs[1] = idxs[1] +1;
//            } else {
//                idxs[0] = idxs[0] +1;
//            }
        }
    }

    public static List<int[]> kPairsSmallestSums(int[] a, int[] b, int k) {
        List<int[]> retVal = new ArrayList<>();

        // Add our first value to our result, since we know that the first
        // smallest number is the sum of the two first elements in each
        // input array
        int curr = a[0] + b[0];
        // The 0 index of the idxs array points the the current position of
        // array a and the 1 index points to the current position of array b.
        int[] idxs = new int[2];
        // Set the current iteration we have completed to 1, since we already
        // generated an output
        retVal.add(new int[]{a[0], b[0]});
        int currItr = 1;

        while (currItr < k) {
            // Get two options for the next lowest sum, ensuring that we do
            // not step off the edge of either of our arrays
            int opt1 = (idxs[1] + 1) > (b.length - 1) ? Integer.MAX_VALUE : a[idxs[0]] + b[idxs[1] + 1];
            int opt2 = (idxs[0] + 1) > (a.length - 1) ? Integer.MAX_VALUE : a[idxs[0] + 1] + b[idxs[1]];
            if (opt1 == opt2) {
                if (opt1 == Integer.MAX_VALUE) {
                    // We have reached the end of the possible permutations
                    break;
                }
                System.out.printf("opt1=%d, opt2=%d%n", opt1, opt2);
                int[] opt1Arr = new int[]{a[idxs[0]], b[idxs[1] + 1]};
                int[] opt2Arr = new int[]{a[idxs[0] + 1], b[idxs[1]]};
                // Add both to our result, but only if that will keep up <= k.
                if ((currItr + 2) <= k) {
                    // Add both results
                    retVal.add(opt1Arr);
                    retVal.add(opt2Arr);
                } else {
                    // Just add one, it does not matter which.
                    retVal.add(opt1Arr);
                }
                currItr += 2;
                // It does not matter which of the resulting values we add to
                // our current value because they are the same value.
                curr = opt1;

            } else if(opt1 < opt2) {
                retVal.add(new int[]{a[idxs[0]], b[idxs[1] + 1]});
                curr = opt1;
            } else {
                retVal.add(new int[]{a[idxs[0] + 1], b[idxs[1]]});
                curr = opt2;
            }
            // Bump the index in the array that is currently pointing to the
            // smallest value
            bumpMaxIdx(a, b, idxs);
            currItr++;
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
