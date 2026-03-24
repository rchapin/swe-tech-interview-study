import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class ArrayStepper {

    public static int[] convertArr(String[] arr) {
        int[] retVal = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            retVal[i] = Integer.parseInt(arr[i]);
        }
        return retVal;
    }

    public static boolean traverse(int[] nums, Set<Integer> memo, int curIdx) {
        // System.out.println(curIdx);
        if (memo.contains(curIdx)) {
            return false;
        }
        if (curIdx + nums[curIdx] == nums.length - 1) {
            return true;
        }
        if (nums[curIdx] == 0) {
            return false;
        }
        boolean result = false;
        for (int i = 1; i <= nums[curIdx]; i++) {
            if (curIdx + i > nums.length - 1) {
                memo.add(curIdx + i);
            }
            result = traverse(nums, memo, curIdx + i);
            if (result) {
                return true;
            } else {
                memo.add(curIdx + i); 
            } 
        }
        return false;
    }

    public static boolean arrayStepper(int[] nums) {
        Set<Integer> memo = new HashSet<>();
        return traverse(nums, memo, 0);
    }

    public static void main(String[] args) {
        System.out.println("Enter -1 to exit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter an array of positive ints each separated by a space");
            String input = scanner.nextLine();
            String[] tokens = input.split("\\s");
            int[] nums = convertArr(tokens);
            if ((nums.length == 1) && (nums[0] == -1)) {
                scanner.close();
                System.exit(0);
            }
            long start = System.nanoTime();
            System.out.println(arrayStepper(nums));
            System.out.println(System.nanoTime() - start);
        }
    }

    public static class Node {
        final int idx;
        final int n;
        public Node(int idx, int n) {
            this.idx = idx;
            this.n = n;
        }
    }
}
