class BestTimeoBuy {
 
    public static int maxP(int startIdx, int[] prices) {
        int retVal = 0;
        int endIdx = prices.length - 1;
        if (startIdx == endIdx) {
            return retVal;   
        }
        
        if (startIdx < endIdx) {
            // "Drop" any number that is larger than the number that it preceeds    
            while (startIdx > endIdx) {
                if (prices[startIdx+1] <= prices[startIdx]) {
                    startIdx++;
                } else {
                    break;
                }
            }
            
            // If we have not yet reached the end of the array, we will "split"
            // the problem.  We will take the first two numbers and figure out
            // the price difference between them, then "remove" those two numbers
            // from the array and recusively call the function.  For the other
            // half of the problem we will iterate through the remaining array
            // looking for the largest number/difference.  Then recurse through
            // the remainder of the array in the same fashion.
            if (startIdx == endIdx) {
                return retVal;
            }
            
            // Greedy, first potential value
            int greedyVal = prices[startIdx+1] - prices[startIdx];
            // System.out.printf("startIdx=%d, greedyVal=%d\n", startIdx, greedyVal);
            greedyVal = greedyVal >= 0 ? greedyVal : 0;
            // Recurse with the remaining values of the prices array that do not
            // include the two indexes from which we generated the greedyVal result.
            greedyVal += maxP(startIdx+1, prices);
            
            // Look for a possible larger value than the prices[startIdx+1]
            int curLargestIdx = startIdx+1;
            int curLargestVal = prices[curLargestIdx];
            int nextLargestIdx = -1;
            curLargestIdx++;
            while(curLargestIdx <= endIdx) {
                if (prices[curLargestIdx] < curLargestVal) {
                    curLargestIdx++;
                } else {
                    break;
                }
            }
            // If we have checked all of the rest of the elements in the array and
            // there is no larger value than the index from which we generated the
            // initial greedyVal, we simply return the current greedyVal
            if (curLargestIdx > endIdx) {
                return greedyVal;
            }
            // Otherwise, determine the difference between the existing start index
            // and this next larger value in the array and recurse with the remaining
            // elements of the array.
            int nextPossibleVal = prices[curLargestIdx] - prices[startIdx];
            nextPossibleVal += maxP(curLargestIdx+1, prices);
            
            return (greedyVal > nextPossibleVal) ? greedyVal : nextPossibleVal;
        }
        
        return retVal;
    }
    
    public static int maxProfit(int[] prices) {
        return maxP(0, prices);
    }

    public static void main(String[] args) {
        int[][] testData = new int[4][];
        testData[0] = new int[]{1, 2};
        testData[1] = new int[]{7,1,5,3,6,4};
        testData[2] = new int[]{1,2,3,4,5};
        testData[3] = new int[]{7,6,4,3,1};
        for (int[] arr : testData) {
            System.out.println(maxProfit(arr));
        }
    }
}
