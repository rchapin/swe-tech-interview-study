import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Subsets {

    public static List<List<Character>> subsets(List<Character> list) {
        List<List<Character>> retVal = new ArrayList<>();

        // This is our base case, the result of the empty list, is a List of
        // empty lists.
        if (list.size() == 0) {
            retVal.add(new ArrayList<>());
            return retVal;
        }

        // Remove the first element from the list and make the recursive call
        List<Character> subList = list.subList(1, list.size());
        List<List<Character>> resultWithoutElement = subsets(subList);
        List<List<Character>> resultWithElement = new ArrayList<>();
        // We make a copy of each of the lists from the results returned
        // WITHOUT the first element and then add the first element to it.
        // Then add them both to the return value.
        for (List<Character> l : resultWithoutElement) {
            List<Character> newList = new ArrayList<>();
            newList.add(list.get(0));
            newList.addAll(l);
            resultWithElement.add(newList);
            retVal.add(newList);
            retVal.add(l);
        }
        return retVal;
    }
    public static void main(String[] args) {
        List<List<Character>> input = new ArrayList<>();
        Character[] arr1 = { 'a', 'b', 'c'};
        input.add(Arrays.asList(arr1));
        input.add(new ArrayList<Character>());
        Character[] arr2 = { 'x' };
        input.add(Arrays.asList(arr2));
        Character[] arr3 = { 'q', 'r', 's', 't' };
        input.add(Arrays.asList(arr3));
        for (List<Character> l : input) {
            System.out.println(subsets(l));
        }
    }
}
