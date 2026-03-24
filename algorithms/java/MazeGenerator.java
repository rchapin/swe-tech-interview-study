import java.util.Scanner;

public class MazeGenerator {

    public static Node[][] buildMap(int n, int m) {
        Node[][] retVal = new Node[n*2+1][m*2+1];
        // All even columns and rows are walls.  All odd columns and rows
        // are cells

        return retVal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press CTRL+C to exit");
        while (true) {
            System.out.printf("Enter a value for n and m, separated by a space, to generate a n*m maze: ");
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            Node[][] map = buildMap(n, m);
        }
    }

    public class Node {
        Type type;
        int x;
        int y;

        public Node(Type type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    public enum Type {
        CELL,
        WALL;
    }
}

