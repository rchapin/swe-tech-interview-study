
public class SelectionSort {

  public static void selectSort(int[] arr) {
    int temp = 0;
    int currentMin = Integer.MAX_VALUE;
    int currentMinPosition = 0;
    
    // Start at the beginning of the array and look for the smallest value
    // from our current position to the end.
    for (int i = 0; i < arr.length-1; i++) {
      // Reset the minimum
      currentMin = Integer.MAX_VALUE;

      // Look for the smallest value from i to the end
      for (int j = i; j < arr.length; j++) {
        if (arr[j] < currentMin) {
          currentMin         = arr[j];
          currentMinPosition = j;
        }
      }
      
      // Once we have found the current minimum position in the unsorted
      // segment of the array, swap it with the current ith position
      temp   = arr[i];
      arr[i] = arr[currentMinPosition];
      arr[currentMinPosition] = temp;
    }
  }

  public static void printArr(int[] arr) {
    for (int i : arr) {
      System.out.printf("%d ", i);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] arr1 = new int[]{6, 6, 2, 9, 7, 15, 1, 31};
    int[] arr2 = new int[]{-45, 8, -18234, 234, 6234, 1, 88, 7, 7, 7, 79};
    int[][] testData = new int[2][];
    testData[0] = arr1;
    testData[1] = arr2;

    for (int[] t : testData) {
      selectSort(t);
      printArr(t);
    }
  }
}
