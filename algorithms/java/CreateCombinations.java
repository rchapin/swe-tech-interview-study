import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CreateCombinations {

    public static List<List<String>> createCombinations(List<String> list, int k) {
        List<List<String>> retVal = new ArrayList<>();
        if (k == 0) {
            retVal.add(new ArrayList<>());
            return retVal;
        }
        if (k > list.size()) {
            return retVal;
        }

        String firstElement = list.get(0);
        List<List<String>> lhsResult = createCombinations(list.subList(1, list.size()), k - 1);
        List<List<String>> rhsResult = createCombinations(list.subList(1, list.size()), k);
        // For each sublist returned from the LHS call, add the first element
        // then add the list to the retVal.
        for (List<String> lhsSubList : lhsResult) {
            lhsSubList.add(firstElement);
            retVal.add(lhsSubList);
        }
        retVal.addAll(rhsResult);

        return retVal;
    }

    public static void printResult(List<List<String>> result) {
        System.out.println("[");
        for (List<String> l : result) {
            System.out.println(l);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        List<String> l1 = Arrays.asList(new String[]{"a", "b", "c"});
        List<String> l2 = Arrays.asList(new String[]{"8", "2", "1", "4"});
        List<String> l3 = Arrays.asList(new String[]{"q", "r", "s", "t", "u", "v", "w"});
        List<String> l4 = Arrays.asList(new String[]{"q", "r", "s", "t"});

        List<Input> input = new ArrayList<>();
        input.add(new Input(l1, 2));
        input.add(new Input(l2, 3));
        input.add(new Input(l3, 5));
        input.add(new Input(l4, 2));
        input.add(new Input(l4, 3));

        for (Input i : input) {
            printResult(createCombinations(i.data, i.k));
        }
    }

    public static class Input {
        List<String> data;
        int k;
        public Input(List<String> data, int k) {
            this.data = data;
            this.k = k;
        }
    }
}
