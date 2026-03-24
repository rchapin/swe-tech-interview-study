import java.util.Scanner;

public class RotateArray {

    public static int[] buildArr(String input) {
        String[] inputTokens = input.split("\\s+");
        int[] retVal = new int[inputTokens.length];
        for (int i = 0; i < inputTokens.length; i++) {
            retVal[i] = Integer.parseInt(inputTokens[i]);
        }
        return retVal;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }

    public static int bumpPtr(int[] arr, int ptr) {
        return ptr + 1 < arr.length ? ptr + 1 : 0;
    }

    public static void rotateArray(int[] arr, int k) {
        if (k == arr.length) {
            // Nothing to do
            return;
        }
        int temp = 0;
        int curr = 0;
        int next = k;
        int prev = next + k < arr.length ? next + k : (next + k) - (arr.length);

        while (curr < k) {
            System.out.printf("-- curr=%d, next=%d, prev=%d%n", curr, next, prev);
            temp = arr[next];
            arr[next] = arr[curr];
            arr[curr] = arr[prev];
            arr[prev] = temp;

            curr = bumpPtr(arr, curr);
            next = bumpPtr(arr, next);
            prev = bumpPtr(arr, prev);
            printArray(arr);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter CTRL+C to exit");
        while (true) {
            System.out.printf(
                "Enter an array of ints separated by spaces, then a comma and " +
                "an int for the number of elements to rotate the array:%n");
            // Parse the input
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            int[] arr = buildArr(tokens[0].strip());
            int k = Integer.parseInt(tokens[1].strip());
            rotateArray(arr, k);
            printArray(arr);
        }
    }
}
