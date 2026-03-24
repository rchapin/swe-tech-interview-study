import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Permutations {

    public static List<List<String>> permutations(List<String> list) {
        
        List<List<String>> retVal = new ArrayList<>();
        if (list.size() == 0) {
            // Return an empty result
            retVal.add(new ArrayList<>());
            return retVal;
        }

        
        for (String elem : list) {
            
        }

        for (int i = 0; i < list.size(); i++) {
            // For each element in the list, iterate over it, and at each
            // iteration, generate a sublist that does not include that
            // element.  
            String elem = list.get(i);
            List<String> subList = new ArrayList<>(); 
            for (int j = 0; j < list.size(); j++) {
                if (j == i) {
                    continue;
                }
                subList.add(list.get(j));
            }
            // System.out.printf("elem=%s, sublist=%s%n", elem, subList);
            // Call the method recursively with the newly generated sublist
            // and then add to each of the lists returned the element that
            // we removed for this iteration.
            List<List<String>> subResult = permutations(subList);
            // System.out.println("subResult=" + subResult);
            for (List<String> srl : subResult) {
                srl.add(elem);
                retVal.add(srl);
            }
        }

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
        List<String> l2 = Arrays.asList(new String[]{"red", "blue"});
        List<String> l3 = Arrays.asList(new String[]{"8", "2", "1", "4"});
        List<List<String>> input = new ArrayList<>();
        input.add(l1);
        input.add(l2);
        input.add(l3);
        for (List<String> l : input) {
            printResult(permutations(l));
        }
    }
}
