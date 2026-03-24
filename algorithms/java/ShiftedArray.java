import java.util.List;
import java.util.ArrayList;

public class ShiftedArray {

  public static int partitionIndex(int[] arr) {
    int begin = 0;
    int end = arr.length - 1;
    int mid = 0;

    while (begin < end) {
      mid = (end + begin) / 2;
      if (arr[begin] > arr[mid]) {
        // The partition index is going to be in the "left" 1/2 of the array.
        end = mid - 1;
      } else {
        // The partition index is in the "right" 1/2
        begin = mid + 1;  
      }
    }
    return begin;
  }

  public static int binarySearch(int[] arr, int begin, int end, int num) {
    while (begin <= end) {
      int mid = (end + begin) / 2 ;
      if (arr[mid] == num) {
        return mid;
      }

      if (num < arr[mid]) {
        end = mid - 1;
      } else {
        begin = mid + 1;
      }
    }
    return -1;
  }

  public static int shiftedArray(int[] arr, int num) {
    // First figure out the partition index, the index with the smallest element.
    int partitionIdx = partitionIndex(arr);

    // Now that we know the partition index, or the first element in the sorted
    // array we can start our binary search for our value in one of the sorted
    // sub arrays
    if (num < arr[0]) {
      // The value has to be in the sorted array to the right of the partition  
      return binarySearch(arr, partitionIdx, arr.length - 1, num);
    } else {
      return binarySearch(arr, 0, partitionIdx, num);
    }
  }

  public static void main(String[] args) {
    TestData t1 = new TestData(new int[] { 8, 9, 10, 1, 2, 3, 4, 5, 6, 7 }, 5);
    TestData t2 = new TestData(new int[] { 2, 3, 4, 5, 6, 7, 1 }, 2);
    TestData t3 = new TestData(new int[] { 2 }, 2);
    TestData t4 = new TestData(new int[] { 8, 9, 10, 1, 2, 3, 4, 5, 6, 7 }, 9);
    TestData t5 = new TestData(new int[] { 8, 9, 10, 1, 2, 3, 4, 5, 6, 7 }, 12);

    List<TestData> testData = new ArrayList<>();
    testData.add(t1);
    testData.add(t2);
    testData.add(t3);
    testData.add(t4);
    testData.add(t5);
    for (TestData t : testData) {
      System.out.printf("-- %d%n", shiftedArray(t.arr, t.num));
    }
  }

  public static class TestData {
    int[] arr;
    int num;
    public TestData(int[] arr, int num) {
      this.arr = arr;
      this.num = num;
    }
  }
}
