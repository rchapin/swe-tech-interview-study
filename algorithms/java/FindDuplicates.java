import java.io.*;
import java.util.*;

class FindDuplicates {

  /*
  //https://www.linkedin.com/in/hassan-s-215726187/
  results = 3, 6, 7  
   .  .  .  .  .  . 
  [1, 2, 3, 5, 6, 7],
  
   .  .  . 
  [3, 6, 7, 8, 20]
  
  1 2 3 4 5 6 7 8 9 10 11 12 ->  10000000000 .... 200M
  
  1000000000
  
  
  m+n
  m * log(n)
  
  1000  10000000

  Set<Integer> arr1Elems
  int arr1Ptr = 0
  int arr2Ptr = 0
  
  while (arr1Ptr < arr1.len && arr2Ptr < arr2.len)
    int arr1val = arrPtr[arrPtr]
  */
  
  
  static int[] findDuplicates(int[] arr1, int[] arr2) {
    List<Integer> result = new ArrayList<>();
    int arr1Ptr = 0;
    int arr2Ptr = 0;
    int arr1Val = 0;
    int arr2Val = 0;
    while (arr1Ptr < arr1.length && arr2Ptr < arr2.length) {
      arr1Val = arr1[arr1Ptr];
      arr2Val = arr2[arr2Ptr];
      
      if (arr1Val == arr2Val) {
        result.add(arr1Val);
        arr1Ptr++;
        arr2Ptr++;
        continue;
      }
      
      if (arr1Val < arr2Val) {
        arr1Ptr++;
      } else {
        arr2Ptr++;
      }
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  public static void main(String[] args) {

  }

}
