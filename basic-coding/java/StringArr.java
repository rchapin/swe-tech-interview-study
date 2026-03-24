import java.util.List;
import java.util.ArrayList;
import java.util.Map;
public class StringArr {

    public static void main(String[] args) {
        String[][] s0 = new String[1][0];
        s0[0] = null;
        System.out.println(s0[0] == null);
        String[][] s1 = new String[0][0];
        System.out.println(s1.length);
        String[][] s2 = new String[1][1];
        s2[0] = new String[]{"a"};

        String[][] s3 = new String[0][];
        System.out.println("s3.length=" + s3.length);
        for (String[] s : s3) {
            System.out.println(s.length);
        }

        String[][] s4 = new String[1][0];
        System.out.println("s4.length=" + s4.length);

        System.out.println(s2.length);
        System.out.println(s2[0][0]);

        Map<String, String[][]> m = null;
        List<int[]> result = new ArrayList<int[]>();
    }
}
