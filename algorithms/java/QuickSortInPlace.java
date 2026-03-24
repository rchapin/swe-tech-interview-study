import java.util.Scanner;

public class QuickSortInPlace {

    public static void sort(int[] arr) {
        // To start the sorting we will make the first call to quickSort
        // passing in the first index of the array as the low index, and the
        // last index as the high index.
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high || low < 0) {
            // There is no need to attempt to sort this partition
            return;
        }

        int partitionIdx = partition(arr, low, high);

        // Now we have two paritions, one on either side of the partitionIndex.
        // We recursively run quickSort on each partition, passing in one half
        // of the remaining unsorted array.
        // Partition the remainder BEFORE the parition index.
        quickSort(arr, low, (partitionIdx - 1));
        // Partition the remainder AFTER the partition index.
        quickSort(arr, (partitionIdx + 1), high);
    }

    public static int partition(int[] arr, int low, int high) {
        // We arbitrarily use the value in the high index as the pivot value.
        int pivotVal = arr[high];
        // Set the temporary pivot location to be one element lower than the
        // lowest element in this partition
        int tempPivot = low - 1;
        int temp = 0;
        System.out.printf("tempPivot=%d%n", tempPivot);

        for (int i = low; i < high; i++) {
            if (arr[i] <= pivotVal) {
                // Bump the index of the tempPivot
                tempPivot++;
                // Swap the current array value with the tempPivot index
                temp = arr[i];
                arr[i] = arr[tempPivot];
                arr[tempPivot] = temp;
            }
        }
        // Swap the value that is in the high index slot with the value that is
        // in the tempPivot slot so that the pivot value is now in the correct
        // spot in the array.  All values to the left (lower index) are lesser
        // value and all values to the right (higher index) are greater value.
        tempPivot++;
        temp = arr[tempPivot];
        arr[tempPivot] = arr[high];
        arr[high] = temp;
        System.out.println("After partitioning arr=" +  arrToString(arr, low, high));
        return tempPivot;
    }

    public static String arrToString(int[] arr) {
        return arrToString(arr, 0, arr.length - 1);
    }

    public static String arrToString(int[] arr, int low, int high) {
        String[] strings = new String[(high + 1) - low];
        for (int i = 0, j = low; j <= high; i++, j++) {
            strings[i] = Integer.toString(arr[j]);
        }
        return String.join(",", strings);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("CTRL+C to quit");
        while (true) {
            System.out.printf("Enter any number of ints separated by spaces to be sorted: ");
            String input = scanner.nextLine();
            String[] strings = input.split(" ");
            int[] arr = new int[strings.length];
            // Convert all Strings to ints
            for (int i = 0; i < strings.length; i++) {
                arr[i] = Integer.parseInt(strings[i]);
            }
            sort(arr);
            System.out.println("Sorted array = " + arrToString(arr));
        }
    }
}
