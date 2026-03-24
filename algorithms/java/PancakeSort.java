
public class PancakeSort {

    public static void flip(int[] arr, int k) {
        int temp = 0;

        while (true) {
            temp = arr[k];
            arr[k] = arr[k-1];
            arr[k-1] = temp;
            k--;

            if (k <= 0) {
                return;
            }
            // Check to see if we need to keep flipping
            if (arr[k-1] < arr[k]) {
                return;
            }
        }
    }

    public static void pancakeSort(int[] arr) {
        int lastIdx = arr.length-1;

        for (int i = 0; i <= lastIdx; i++) {
            // Check to see if the value at the current index is greater than
            // the value in the next index, checking that we don't step off
            // the end of the array
            if ( (i+1) <= lastIdx) {
                if (arr[i] > arr[i+1]) {
                    flip(arr, i+1);
                }
            }
        }
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[][] input = new int[4][];
        input[0] = new int[] { 52, 99, 16, 62, 3 };
        input[1] = new int[] { 5, 4, 3, 2, 1 };
        input[2] = new int[] { 5, 5, 99, 6, 3 };
        input[3] = new int[] { 100, 99, 97, 101, 3, 3, 1, 2, 1, 7 };
        for (int[] i : input) {
            pancakeSort(i);
            printArr(i);
        }
    }
}
