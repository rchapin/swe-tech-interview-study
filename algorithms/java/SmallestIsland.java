import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

// Test data
// 
//  w w l, l w l, l l w
//
//  w  w  l
//  l  w  l
//  l  l  w
// smallest=2
// 
// w w w w l, w l w w w, l l w w w, w w l l w, w w l w w
//
//  w  w  w  w  l
//  w  l  w  w  w
//  l  l  w  w  w 
//  w  w  l  l  w
//  w  w  l  w  w
// smallest=1
// 
//  w l w w w, w l w w w, w w w l w, w w l l w, l w w l l , l l w w w
//
//  w  l  w  w  w
//  w  l  w  w  w
//  w  w  w  l  w
//  w  w  l  l  w
//  l  w  w  l  l
//  l  l  w  w  w
// smallest=2

public class SmallestIsland {

    public static int visitIsland(String[][] map, Cell cell, Set<Cell> visited) {
        // Since we have to explore all of the adjacent "land" cells it does
        // not matter if we use a breadth first or depth first search.  If we
        // go with a DFS we can use recursion and eliminate having to use a
        // separate stack to keep track of where we are and instead use the
        // call stack.
        // 
        // First we do bounds checking to make sure that the Cell we are trying
        // to explore is valid.
        if (visited.contains(cell)) {
            // We have already visited this Cell
            return 0;
        }
        if (cell.row < 0) {
            // We cannot go North
            return 0;
        }
        if (cell.row >= map.length) {
            // We cannot go South
            return 0;
        }
        if (cell.col < 0) {
            // We cannot go East
            return 0;
        }
        if (cell.col >= map[cell.row].length) {
            // We cannot go West
            return 0;
        }
        if (map[cell.row][cell.col].equals("w")) {
            return 0;
        }

        int retVal = 0;
        if (map[cell.row][cell.col].equals("l")) {
            retVal++;
        } else {
            return retVal;
        }
        
        // Visit the current cell and then explore any of its neighbors
        visited.add(cell);
        retVal += visitIsland(map, new Cell(cell.row - 1, cell.col), visited);
        retVal += visitIsland(map, new Cell(cell.row + 1, cell.col), visited);
        retVal += visitIsland(map, new Cell(cell.row, cell.col - 1), visited);
        retVal += visitIsland(map, new Cell(cell.row, cell.col + 1), visited);
        return retVal;
    }

    public static int smallestIsland(String[][] map) {
        int retVal = Integer.MAX_VALUE;
        // Keep track of all of the cells that we have visited
        Set<Cell> visited = new HashSet<>();

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Cell curCell = new Cell(row, col);
                if (map[row][col].equals("l") && !visited.contains(curCell)) {
                    // We found an island, execute a search starting with
                    // this island.
                    int islandSize = visitIsland(map, new Cell(row, col), visited); 
                    retVal = islandSize < retVal ? islandSize : retVal;
                }
            }
        }

        return retVal; 
    }

    public static String[][] buildMap(String input) {
        // Input is expected to be a line of text in the following format:
        // w w l, l w l, l l w
        // Which would be three rows, with three columns in each row.
        String[] sourceRows = input.split(",");
        String[][] retVal = new String[sourceRows.length][];

        for (int i = 0; i < sourceRows.length; i++) {
            String[] row = sourceRows[i].strip().split("\\s+");
            retVal[i] = row;
        }
        for (String[] row : retVal) {
            for (String cell : row) {
                System.out.printf(" %s ", cell);
            }
            System.out.println();
        }
        return retVal;
    }

    public static void main(String[] args) {
        System.out.println("Press CTRL+C to exit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter n number of arrays, each the same length to form a map with water and land: ");
            String[][] map = buildMap(scanner.nextLine());
            System.out.printf("smallest island=%d%n", smallestIsland(map));
        }
    }

    public static class Cell {
        int row;
        int col;
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return String.format("[%d,%d]", row, col);
        }

        @Override
        public int hashCode() {
            return String.format("%s,%s", row, col).hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Cell) {
                Cell otherCell = (Cell)other;
                if (this.row != otherCell.row) {
                    return false;
                }
                if (this.col != otherCell.col) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }
}
