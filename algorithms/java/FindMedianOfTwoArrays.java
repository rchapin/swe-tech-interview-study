import java.util.Scanner;

public class FindMedianOfTwoArrays {

    public static int findMedian(int[] arr1, int[] arr2) {
        // Figure out which "index" is the median between these two arrays
        int totalLen = arr1.length + arr2.length;
        int median = totalLen / 2;
        if (totalLen % 2 != 0) {
            median++;
        }

        int curr = 1;
        int currVal = Integer.MIN_VALUE;
        int ptr1 = 0;
        int ptr2 = 0;

        while (curr <= median) {
            // Need to check to see if the current pointer for either or both
            // of the arrays is stepping off the array.  If so, we can just
            // index directly to one of the elements of the remaining array.
            if (ptr1 >= arr1.length) {
                currVal = arr2[ptr2 + (median - curr)];
                break;
            }
            if (ptr2 >= arr2.length) {
                currVal = arr1[ptr1 + (median - curr)];
                break;
            }

            // Compare the value between the two pointers to determine
            // which of the two values is less than the other.  The array in
            // which the current smallest value resides, and the one that we
            // are going to use, is the array's pointer that we want to advance
            if (arr1[ptr1] < arr2[ptr2]) {
                currVal = arr1[ptr1];
                ptr1++;
            } else {
                currVal = arr2[ptr2];
                ptr2++;
            }
            curr++;
        }

        return currVal;
    }

    public static int[] buildArr(String input) {
        String[] inputTokens = input.strip().split("\\s+");
        int[] retVal = new int[inputTokens.length];
        for (int i = 0; i < inputTokens.length; i++) {
            retVal[i] = Integer.parseInt(inputTokens[i].strip());
        }
        return retVal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CTRL+C to exit");
        while (true) {
            System.out.printf(
                "Enter two arrays of ints.  Each array a space separated list of " +
                "ints, each array separated by a comma. Example:%n" +
                "1 2 3, 4 5 6%n");
            // Parse the input
            String line = scanner.nextLine();
            String[] arrs = line.split(",");
            int[] arr1 = buildArr(arrs[0]);
            int[] arr2 = buildArr(arrs[1]);
            System.out.printf("median=%d%n", findMedian(arr1, arr2));
        }
    }
}
